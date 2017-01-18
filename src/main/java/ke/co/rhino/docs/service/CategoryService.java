package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.repo.CategoryRepo;
import ke.co.rhino.docs.repo.ClientRepo;
import ke.co.rhino.docs.repo.FileRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    private FileRepo fileRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Category> create(String name, String description, String actionUsername) {

        if(name==null||name.trim().isEmpty()){
            return ResultFactory.getFailResult("Invalid category name provided. Cannot save");
        }
        Category catByName = repo.findByName(name);
        if(catByName==null){
            Category.CategoryBuilder builder = new Category.CategoryBuilder()
                    .name(name);

            if(!description.trim().isEmpty()){
                builder.description(description);
            }

            Category cat = builder.build();

            repo.save(cat);
            return ResultFactory.getSuccessResult(cat);
        } else {
            return ResultFactory.getFailResult("Name is already in use. Cannot save.");
        }

    }


    @Override
    public Result<Category> update(Long categoryId, String name, String description, String actionUsername) {


        if(categoryId==null||categoryId<1){
            return ResultFactory.getFailResult("Invalid Category ID provided. Update failed.");
        }

        if(name==null||name.trim().isEmpty()){
            return ResultFactory.getFailResult("Invalid category name provided. Cannot update.");
        }

        /*List<Category> catsFromClient = repo.findByClient(client);

        Boolean found = catsFromClient.stream()
                .map( category -> {
                    return category.getId();})
                .anyMatch(catId -> {
                    return catId.equals(categoryId);
                });

        if(!found){
            return ResultFactory.getFailResult("Cannot change category's client. Update failed.");
        }*/

        Category.CategoryBuilder builder = new Category.CategoryBuilder()
                .name(name)
                .categoryId(categoryId);
        if(!description.trim().isEmpty()){
            builder.description(description);
        }
        Category category = builder.build();
        repo.save(category);
        return ResultFactory.getSuccessResult(category);
    }

    @Override
    public Result<Category> remove(Long categoryId, String actionUsername) {
        return null;
    }

    @Override
    public Result<Page<Category>> findAll(int page,int size,String actionUsername) {

        PageRequest request = new PageRequest(page-1,size);

        Page<Category> categories = repo.findAll(request);

        return ResultFactory.getSuccessResult(categories);

    }

    @Override
    public Result<Page<Category>> searchByName(String searchStr,int page,int size, String actionUsername) {
        return null;
    }
}
