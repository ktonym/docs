package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.repo.CabinetRepo;
import ke.co.rhino.docs.repo.CabinetRowRepo;
import ke.co.rhino.docs.repo.ClientRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.security.x509.RFC822Name;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 19-Nov-16.
 */
@Transactional
@Service("clientService")
public class ClientService implements IClientService {

    @Autowired
    private ClientRepo repo;

    @Autowired
    private CabinetRepo cabinetRepo;

    @Autowired
    private CabinetRowRepo cabinetRowRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Client> store(Optional<Long> clientIdOpt, String clientName, Long rowId, String tel, String email, String pin, String actionUsername) {

        /*if (cabinetId==null||cabinetId<1){
            return ResultFactory.getFailResult("Invalid cabinet ID provided.");
        }

        Optional<Cabinet> cabinetOptional = cabinetRepo.getOne(cabinetId);
        if (!cabinetOptional.isPresent()) {
            return ResultFactory.getFailResult("No cabinet with ID ["+cabinetId+"] was found.");
        }

        Cabinet cabinet = cabinetOptional.get();

        if(rowNo==null||rowNo<1){
            return ResultFactory.getFailResult("Invalid row number provided. Cannot ADD/MODIFY a client.");
        }*/
        if(rowId==null||rowId<1){
            return ResultFactory.getFailResult("Invalid row ID provided. Cannot ADD/MODIFY a client.");
        }

        Optional<CabinetRow> rowOpt = cabinetRowRepo.getOne(rowId);

        if(!rowOpt.isPresent()){
            return ResultFactory.getFailResult("No row with provided ID exists in the system. Cannot ADD/MODIFY a client.");
        }

        Client.ClientBuilder builder = new Client.ClientBuilder(clientName);

        Optional<Client> clientNameOpt = repo.findByClientName(clientName); // query by name
        Optional<Client> clientByPinOpt = repo.findByPin(pin); // query by PIN
        if(!clientIdOpt.isPresent()){ //we're creating a new client
            //builder.cabinetRow(row);

            if(clientNameOpt.isPresent()){
                return ResultFactory.getFailResult("A client with a similar name exists in the system.");
            }

            if(clientByPinOpt.isPresent()){
                return ResultFactory.getFailResult("A client with a similar PIN exists in the system.");
            }
        } else { // we're updating a client
            Long clientId = clientIdOpt.get();
            Optional<Client> clientOpt = repo.getOne(clientId);


            if(!clientOpt.isPresent()){
                return ResultFactory.getFailResult("No client with ID ["+clientId+"] was found. Update failed.");
            }

            // check that we're not assigning an existing name
            // or assigning an existing pin
            if(clientNameOpt.isPresent()&&clientByPinOpt.get().getClientId()!=clientId){
                return ResultFactory.getFailResult("A different client with a similar name exists.");
            }

            if(clientByPinOpt.isPresent()&&clientByPinOpt.get().getClientId()!=clientId){
                return ResultFactory.getFailResult("A different client with a similar PIN exists.");
            }

            builder.clientId(clientId);

        }

        if(pin!=null) builder.pin(pin);
        if(tel!=null) builder.tel(tel);
        if(email!=null) builder.email(email);

        Client client = builder.cabinetRow(rowOpt.get()).build();

        repo.save(client);

        return ResultFactory.getSuccessResult(client);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Client> remove(Long clientId, String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Page<Client>> findAll(int page, int size, String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Client>> findEverything(String actionUsername) {
        List<Client> clients = repo.getAll();
        return ResultFactory.getSuccessResult(clients);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Page<Client>> findByCabinet(Long cabinetId, int page, int size, String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Client>> findByRow(CabinetRow row){

        List<Client> clients = repo.findByCabinetRow(row);

        return ResultFactory.getSuccessResult(clients);
    }

    @Override
    public Result<List<Client>> findByRowId(Long rowId, String actionUsername) {


        if(rowId==null||rowId<1){
            return ResultFactory.getFailResult("Invalid row ID");
        }
        Optional<CabinetRow> rowOpt = cabinetRowRepo.getOne(rowId);

//        rowOpt.ifPresent(cabinetRow -> {
//
//        });
        if(rowOpt.isPresent()){
            List<Client> clients = repo.findByCabinetRow(rowOpt.get());
            return ResultFactory.getSuccessResult(clients);
        }

        return ResultFactory.getFailResult("No such row was found.");
    }

}
