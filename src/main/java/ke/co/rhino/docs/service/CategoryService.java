package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.entity.CategoryRef;
import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.repo.CategoryRefRepo;
import ke.co.rhino.docs.repo.CategoryRepo;
import ke.co.rhino.docs.repo.ClientRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Autowired
    private CategoryRefRepo categoryRefRepo;

    @Override
    public Result<Category> create(Long categoryRefId, Long clientId, String description, String actionUsername) {

        if(categoryRefId==null||categoryRefId<1){
            return ResultFactory.getFailResult("Invalid CategoryRef ID provided. Save failed.");
        }
        if(clientId==null||clientId<1){
            return ResultFactory.getFailResult("Invalid Client ID provided. Save failed.");
        }
        CategoryRef ref = categoryRefRepo.findOne(categoryRefId);
        if(ref==null){
            return ResultFactory.getFailResult("No category ref with ID ["+categoryRefId+"] was found. Save failed.");
        }
        Client client = clientRepo.findOne(clientId);
        if(client==null){
            return ResultFactory.getFailResult("No client with ID ["+clientId+"] was found. Save failed.");
        }
        Category.CategoryBuilder builder = new Category.CategoryBuilder(client).categoryRef(ref);
        if(!description.trim().isEmpty()){
            builder.description(description);
        }
        Category category = builder.build();
        return ResultFactory.getSuccessResult(category);

    }

    @Override
    public Result<Category> update(Long categoryRefId, Long categoryId, Long clientId, String description, String actionUsername) {

        if(categoryRefId==null||categoryRefId<1){
            return ResultFactory.getFailResult("Invalid CategoryRef ID provided. Update failed.");
        }
        if(categoryId==null||categoryId<1){
            return ResultFactory.getFailResult("Invalid Category ID provided. Update failed.");
        }
        if(clientId==null||clientId<1){
            return ResultFactory.getFailResult("Invalid Client ID provided. Update failed.");
        }
        CategoryRef ref = categoryRefRepo.findOne(categoryRefId);
        if(ref==null){
            return ResultFactory.getFailResult("No category ref with ID ["+categoryRefId+"] was found. Update failed.");
        }
        Client client = clientRepo.findOne(clientId);
        if(client==null){
            return ResultFactory.getFailResult("No client with ID ["+clientId+"] was found. Update failed.");
        }
        List<Category> catsFromClient = repo.findByClient(client);

        Boolean found = catsFromClient.stream()
                .map( category -> {
                    return category.getId();})
                .anyMatch(catId -> {
                    return catId.equals(categoryId);
                });

        if(!found){
            return ResultFactory.getFailResult("Cannot change category's client. Update failed.");
        }

        Category.CategoryBuilder builder = new Category.CategoryBuilder(client).categoryRef(ref).categoryId(categoryId);
        if(!description.trim().isEmpty()){
            builder.description(description);
        }
        Category category = builder.build();
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
