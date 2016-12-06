package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.CabinetRowId;
import ke.co.rhino.docs.repo.CabinetRepo;
import ke.co.rhino.docs.repo.CabinetRowRepo;
import ke.co.rhino.docs.repo.ClientRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.security.x509.RFC822Name;

import java.util.List;
import java.util.Optional;

/**
 * Created by akipkoech on 10/08/2016.
 */
@Service("cabinetRowService")
@Transactional
public class CabinetRowService implements ICabinetRowService {

    @Autowired
    private CabinetRowRepo repo;
    @Autowired
    private CabinetRepo cabinetRepo;
    @Autowired
    private ClientRepo clientRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CabinetRow> store(Long rowNumber, Long cabinetId, String actionUsername) {

        if(cabinetId == null || cabinetId < 1){
            return ResultFactory.getFailResult("Invalid cabinet ID specified. Kindly provide a valid, non-null ID.");
        }

        Optional<Cabinet> cabinetOpt = cabinetRepo.getOne(cabinetId);

        if(cabinetOpt.isPresent()){//we're working with an existent cabinet

            if(rowNumber == null){
                return ResultFactory.getFailResult("Please specify a valid row number on the cabinet you wish to reference.");
            } else{

                CabinetRow row;//identifying..
                CabinetRow.CabinetRowBuilder builder = new CabinetRow.CabinetRowBuilder(rowNumber)
                        .cabinet(cabinetOpt.get());
                row = builder.build();

                System.out.println("---- Row info ----");
                System.out.println("Row number: ".concat(row.getRowNumber().toString()));
                System.out.println("Cabinet number: ".concat(cabinetOpt.get().getId().toString()));

                repo.save(row);
                return ResultFactory.getSuccessResult(row);
            }
        } else { // we're referring to a non-existent cabinet
            return ResultFactory.getFailResult("No cabinet with ID [" + cabinetId + "] was found.");
        }

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Page<CabinetRow>> findAll(Long cabinetId,int page, int size, String actionUsername) {

        if(cabinetId==null||cabinetId<1){
            ResultFactory.getFailResult("Invalid cabinet ID supplied.");
        }
        Optional<Cabinet> cabinetOpt = cabinetRepo.getOne(cabinetId);
        if(!cabinetOpt.isPresent()){
            return ResultFactory.getFailResult("No cabinet with ID ["+cabinetId+"] was found.");
        }
        Cabinet cabinet = cabinetOpt.get();
        PageRequest request = new PageRequest(page-1,size);
        Page<CabinetRow> cabinetRowPage = repo.findByCabinet(cabinet,request);

        return ResultFactory.getSuccessResult(cabinetRowPage);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CabinetRow> remove(Long rowNumber, Long cabinetId,String actionUsername) {

        if(rowNumber==null||rowNumber<1){
            return ResultFactory.getFailResult("Invalid row number specified. Kindly provide a valid, non-null number you wish to remove.");
        }

        if(cabinetId==null||cabinetId<1){
            return ResultFactory.getFailResult("Invalid cabinet ID specified. Kindly provide a valid, non-null cabinet.");
        }

        Cabinet cab = cabinetRepo.findOne(cabinetId);

        CabinetRowId cabinetRowId = new CabinetRowId(rowNumber,cab);

        Optional<CabinetRow> rowOpt = repo.getOne(cabinetRowId);

        if(rowOpt.isPresent()){ // proceed to verify there are no child records
            long clients = clientRepo.countByCabinetRow(rowOpt.get());
            if(clients>0){
                ResultFactory.getFailResult("Cannot delete cabinet row. Clients do exist. Please migrate the clients to another row before removing this row.");
            }
            repo.delete(cabinetRowId);
            String msg = "Cabinet row was deleted by ".concat(actionUsername);
            return ResultFactory.getSuccessResult(msg);
        } else {
            //error and exit
            return ResultFactory.getFailResult("The row number you specified does not exist on the cabinet or shelf.");
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<CabinetRow>> findByCabinet(Cabinet cab) {

        List<CabinetRow> list = repo.findByCabinet(cab);

        return ResultFactory.getSuccessResult(list);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<CabinetRow> find(CabinetRowId rowId){

        CabinetRow row = repo.findOne(rowId);

        return ResultFactory.getSuccessResult(row);
    }

    @Override
    public Result<Page<CabinetRow>> findZote(int page, int size, String actionUsername) {

        PageRequest request = new PageRequest(page-1,size);
        Page<CabinetRow> cabinetRowPage = repo.findAll(request);

        return ResultFactory.getSuccessResult(cabinetRowPage);

    }

}
