package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.entity.Volume;
import ke.co.rhino.docs.repo.CabinetRowRepo;
import ke.co.rhino.docs.repo.ClientRepo;
import ke.co.rhino.docs.repo.FileRepo;
import ke.co.rhino.docs.repo.VolumeRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 1/7/2017.
 */
@Service("volumeService")
@Transactional
public class VolumeService implements IVolumeService {

    @Autowired
    private VolumeRepo repo;
    @Autowired
    private CabinetRowRepo rowRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private FileRepo fileRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Volume> create(Long rowId,Long clientId,Integer volumeNo, Year year, String actionUsername) {

        if(rowId == null || rowId<1){
            return ResultFactory.getFailResult("Invalid row ID supplied. Cannot save volume.");
        }

        if (clientId == null||clientId<1) {
            return ResultFactory.getFailResult("Invalid client ID supplied. Cannot save volume.");
        }

        if (volumeNo == null || (volumeNo < 1))
            return ResultFactory.getFailResult("Invalid volume no. Kindly provide a valid volume number.");

        if (year==null) year = Year.now();

        Optional<CabinetRow> rowOpt = rowRepo.getOne(rowId);
        if(rowOpt.isPresent()){
            CabinetRow row = rowOpt.get();

            Optional<Client> clientOpt = clientRepo.getOne(clientId);
            if (clientOpt.isPresent()) {
                Client client = clientOpt.get();
                if(repo.findByClientAndVolumeNo(client,volumeNo).isPresent()){
                    return ResultFactory.getFailResult("Volume number already exists. Kindly provide a different volume number.");
                }
                Volume vol = new Volume.VolumeBuilder(volumeNo).cabinetRow(row).client(client).year(year).build();
                repo.save(vol);
                return ResultFactory.getSuccessResult(vol);
            } else {
                return ResultFactory.getFailResult("No client with ID [" + clientId + "] was found. Cannot save volume.");
            }


        } else {
            return ResultFactory.getFailResult("No row with ID ["+rowId+"] was found. Cannot save volume.");
        }


    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Volume> update(Long rowId,Long volumeId, Long clientId, Integer volumeNo, Year year, String actionUsername) {

        if(rowId == null || rowId<1){
            return ResultFactory.getFailResult("Invalid row ID supplied. Cannot update volume.");
        }

        if(volumeId==null||volumeId<1){
            return ResultFactory.getFailResult("Invalid volume ID. Cannot update volume.");
        }

        if (clientId == null||clientId<1) {
            return ResultFactory.getFailResult("Invalid client ID supplied. Cannot update volume.");
        }

        if (volumeNo == null || volumeNo <1){
            return ResultFactory.getFailResult("Invalid volume no. Kindly provide a valid volume number.");
        }

        if (year==null){
            year = Year.now();
        }

        Optional<Client> clientOpt = clientRepo.getOne(clientId);

        if(!clientOpt.isPresent()){
            return ResultFactory.getFailResult("No client with ID ["+clientId+"] was found. Cannot update volume.");
        }

        Optional<CabinetRow> rowOpt = rowRepo.getOne(rowId);
        if(!rowOpt.isPresent()){
            return ResultFactory.getFailResult("No row with ID ["+rowId+"] was found. Cannot update volume.");
        }

        Volume vol = new Volume.VolumeBuilder(volumeNo).cabinetRow(rowOpt.get()).client(clientOpt.get()).year(year).volumeId(volumeId).build();

        return ResultFactory.getSuccessResult(vol);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Result<Page<Volume>> findAll(int page, int size, String actionUsername) {

        PageRequest request = new PageRequest(page-1,size);
        Page<Volume> volumeList = repo.findAll(request);

        return ResultFactory.getSuccessResult(volumeList);
    }

    @Override
    public Result<List<Volume>> findByClient(Long clientId, String actionUsername) {

        if(clientId==null||clientId<1){
            return ResultFactory.getFailResult("Invalid client ID.");
        }

        Optional<Client> clientOpt = clientRepo.getOne(clientId);
        if(!clientOpt.isPresent()){
            return ResultFactory.getFailResult("No client with ID ["+clientId+"] was found.");
        }
        Client client = clientOpt.get();
        Optional<List<Volume>> volumeListOpt = repo.findByClient(client);
        return ResultFactory.getSuccessResult(volumeListOpt.get());
    }

    @Override
    public Result<Volume> remove(Long volumeId, String actionUsername) {

        if(volumeId==null||volumeId<1){
            return ResultFactory.getFailResult("Invalid volume ID supplied. Cannot remove");
        }

        Optional<Volume> volumeOpt = repo.getOne(volumeId);
        if(!volumeOpt.isPresent()){
            return ResultFactory.getFailResult("No volume with ID ["+volumeId+"] was found. Cannot remove.");
        }
        Volume vol = volumeOpt.get();
        long files = fileRepo.countByVolume(vol);
        if(files>0){
            return ResultFactory.getFailResult("Volume has files. Cannot remove");
        }

        repo.delete(volumeId);
        String msg = "Volume with ID ".concat(volumeId.toString()).concat(" deleted by ").concat(actionUsername);
        return ResultFactory.getSuccessResult(msg);
    }

    @Override
    public Result<List<Volume>> findByRowId(Long rowId, String actionUsername) {


        if(rowId==null||rowId<1){
            return ResultFactory.getFailResult("Invalid row ID");
        }
        Optional<CabinetRow> rowOpt = rowRepo.getOne(rowId);

        //        rowOpt.ifPresent(cabinetRow -> {
        //
        //        });
        if(rowOpt.isPresent()){
            Optional<List<Volume>> volumesOpt = repo.findByCabinetRow(rowOpt.get());
            return ResultFactory.getSuccessResult(volumesOpt.get());
        }

        return ResultFactory.getFailResult("No such row was found.");
    }

}
