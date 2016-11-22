package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetType;
import ke.co.rhino.docs.repo.CabinetRepo;
import ke.co.rhino.docs.repo.CabinetRowRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by akipkoech on 22/07/2016.
 */
@Service("cabinetService")
@Transactional
public class CabinetService implements ICabinetService {

    @Autowired
    private CabinetRepo repo;
    @Autowired
    private CabinetRowRepo cabinetRowRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Cabinet> store(Long cabinetId, CabinetType cabinetType, /*Integer shelfNumber,*/ String actionUsername) {

        Cabinet.CabinetBuilder builder = new Cabinet.CabinetBuilder();

        if(cabinetType==null){
            return ResultFactory.getFailResult("Please specify a valid cabinet type. Creation/update failed.");
        }

//        if(shelfNumber==null){
//            return ResultFactory.getFailResult("Please specify a valid non-empty shelf number. Creation/update failed.");
//        }

        if(cabinetId==null){


        } else {

            Optional<Cabinet> cabinetOpt=repo.getOne(cabinetId);
            if(cabinetOpt.isPresent()) {
                builder.cabinetId(cabinetId);
            } else return ResultFactory.getFailResult("Invalid cabinet ID provided. Cannot update.");

        }

        Cabinet cabinet = builder.cabinetType(cabinetType).build();

        repo.save(cabinet);

        return ResultFactory.getSuccessResult(cabinet);
    }

    @Override
    public Result<Cabinet> remove(Long cabinetId, String actionUsername) {

        if(cabinetId==null){
            return ResultFactory.getFailResult("No valid cabinet ID was provided. Cannot remove");
        }
        Optional<Cabinet> cabinetOpt=repo.getOne(cabinetId);
        if(cabinetOpt.isPresent()){
            repo.delete(cabinetOpt.get());
            String msg = cabinetOpt.get().toString().concat(" was deleted by ").concat(actionUsername);
            return ResultFactory.getSuccessResult(msg);
        }

        return ResultFactory.getFailResult("No cabinet with ID ["+cabinetId+"] was found. Cannot remove");
    }

    @Override
    public Result<Page<Cabinet>> findAll(int page,int size,String actionUsername) {

        PageRequest request = new PageRequest(page-1,size);
        Page<Cabinet> cabinetPage = repo.findAll(request);

        return ResultFactory.getSuccessResult(cabinetPage);
    }
}
