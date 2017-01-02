package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.vo.Result;

import java.util.List;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
public interface IGroupService {

    Result<Group> create(String name, String description, String actionUsername);
    Result<Group> update(Long id, String name, String description, String actionUsername);
    Result<Group> remove(Long id, String actionUsername);
    Result<List<Group>> findAll(String actionUsername);
    Result<Group> findByName(String name, String actionUsername);

}
