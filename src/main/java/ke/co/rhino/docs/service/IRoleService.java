package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.entity.Role;
import ke.co.rhino.docs.entity.User;
import ke.co.rhino.docs.vo.Result;

import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 12/31/2016.
 */
public interface IRoleService {

    Result<Role> create(String name, Optional<Long> idGroup, String actionUsername);
    Result<Role> update(Long id, String name, Optional<Long> idGroup, String actionUsername);
    Result<List<Role>> findAll();
    Result<List<Role>> findByUser(Long idUser, String actionUsername);
    Result<List<Role>> findByUser(String username, String actionUsername);
    Result<List<Role>> findByGroup(Long idGroup, String actionUsername);

}
