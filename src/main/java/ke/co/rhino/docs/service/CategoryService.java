package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.repo.CategoryRepo;
import ke.co.rhino.docs.repo.ClientRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by user on 22-Nov-16.
 */
@Service("categoryService")
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepo repo;
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public Result<Category> store(Long categoryId, Long clientId, String name, String description, String actionUsername) {

        if(clientId==null){
            return ResultFactory.getFailResult("Invalid client ID provided.");
        }

        Optional<Client> clientOpt = clientRepo.getOne(clientId);
        if(!clientOpt.isPresent()){
            return ResultFactory.getFailResult("Invalid client with ID ["+clientId+"] provided.");
        }
        if(name==null||name.trim()==""){
            return ResultFactory.getFailResult("Invalid category name provided");
        }
        Optional<Category> testCatByNameOpt = repo.findByName(name);

        Category.CategoryBuilder builder = new Category.CategoryBuilder(clientOpt.get());

        if(categoryId==null){//we're adding a new cat
            if(testCatByNameOpt.isPresent()){
                return ResultFactory.getFailResult("Another category named ["+name+"] already exists. Cannot save.");
            }
        } else {
            //check for already existing name
            if(testCatByNameOpt.isPresent()&&testCatByNameOpt.get().getCategoryId()!=categoryId){
                return ResultFactory.getFailResult("Another category named ["+name+"] already exists. Update failed.");
            }
            builder.categoryId(categoryId);
        }
        if(description!=null||description.trim().length()>0){
            builder.description(description);
        }
        builder.name(name);
        Category category = builder.build();
        repo.save(category);
        return ResultFactory.getSuccessResult(category);
    }

    @Override
    public Result<Category> remove(Long categoryId, String actionUsername) {
        return null;
    }

    @Override
    public Result<Page<Category>> findAll(Long clientId,int page,int size,String actionUsername) {

        if(clientId==null||clientId<1){
            return ResultFactory.getFailResult("Invalid client id provided.");
        }

        Optional<Client> clientOpt = clientRepo.getOne(clientId);

        if(!clientOpt.isPresent()){
            return ResultFactory.getFailResult("No client with ID ["+clientId+"] was found.");
        }

        PageRequest request = new PageRequest(page-1,size);

        Page<Category> categories = repo.findByClient(clientOpt.get(),request);

        return ResultFactory.getSuccessResult(categories);

    }

    @Override
    public Result<Page<Category>> searchByName(String searchStr,int page,int size, String actionUsername) {
        return null;
    }
}
