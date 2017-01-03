package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.CategoryRef;
import ke.co.rhino.docs.repo.CategoryRefRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by anthony.kipkoech on 1/3/2017.
 */
@Service("categoryRefService")
@Transactional
public class CategoryRefService implements ICategoryRefService {

    @Autowired
    private CategoryRefRepo refRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CategoryRef> create(String name, String actionUsername) {

        if(name==null||name.trim().isEmpty()){
            return ResultFactory.getFailResult("Invalid category name provided. Cannot save");
        }
        CategoryRef refByName = refRepo.findByName(name);
        if(refByName==null){
            CategoryRef ref = new CategoryRef.CategoryRefBuilder(name).build();
            refRepo.save(ref);
            return ResultFactory.getSuccessResult(ref);
        } else {
            return ResultFactory.getFailResult("Name is already in use. Cannot save.");
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CategoryRef> update(Long categoryRefId, String name, String actionUsername) {

        if(categoryRefId==null||categoryRefId<1){
            return ResultFactory.getFailResult("Invalid CategoryRef ID provided. Cannot update.");
        }
        if(name==null||name.trim().isEmpty()){
            return ResultFactory.getFailResult("Invalid category name provided. Cannot update.");
        }
        CategoryRef refByName = refRepo.findByName(name);
        if(refByName!=null && refByName.getId()!=categoryRefId){
            return ResultFactory.getFailResult("Name is in use by a different category. Cannot update.");
        }

        CategoryRef ref = new CategoryRef.CategoryRefBuilder(name).id(categoryRefId).build();
        refRepo.save(ref);
        return ResultFactory.getSuccessResult(ref);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<CategoryRef>> findAll(String actionUsername) {
        List<CategoryRef> list = refRepo.findAll();
        return ResultFactory.getSuccessResult(list);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CategoryRef> remove(Long categoryRefId, String actionUsername) {
        return null;
    }
}
