package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.User;
import ke.co.rhino.docs.vo.Result;

import java.util.List;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
public interface IUserService {

    Result<User> create(Long groupId,
                        String username,
                        String password,
                        String firstName,
                        String surname,
                        Boolean locked,
                        Boolean expired, String actionUsername );

    Result<User> update(Long userId,
                        Long groupId,
                        String username,
                        String password,
                        String firstName,
                        String surname,
                        Boolean locked,
                        Boolean expired, String actionUsername);

    Result<User> remove(Long id, String actionUsername);

    Result<List<User>> findAll(String actionUsername);

    Result<User> findByUsernameAndPassword(String username,String password);

}
