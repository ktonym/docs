package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.CategoryRef;
import ke.co.rhino.docs.vo.Result;

import java.util.List;

/**
 * Created by anthony.kipkoech on 1/3/2017.
 */
public interface ICategoryRefService {

    Result<CategoryRef> create(String name,String actionUsername);
    Result<CategoryRef> update(Long categoryRefId, String name, String actionUsername);
    Result<List<CategoryRef>> findAll(String actionUsername);
    Result<CategoryRef> remove(Long categoryRefId, String actionUsername);
}
