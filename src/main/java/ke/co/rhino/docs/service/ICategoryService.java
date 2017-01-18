package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.vo.Result;
import org.springframework.data.domain.Page;

/**
 * Created by user on 22-Nov-16.
 */
public interface ICategoryService {

    Result<Category> create(String name,String description,String actionUsername);
    Result<Category> update(Long categoryId, String name, String desc, String actionUsername);
    Result<Category> remove(Long categoryId,String actionUsername);

    Result<Page<Category>> findAll(int page,int size,String actionUsername);

    Result<Page<Category>> searchByName(String searchStr,int page,int size,String actionUsername);
}
