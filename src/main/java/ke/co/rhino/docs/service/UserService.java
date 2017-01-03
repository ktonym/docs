package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.entity.User;
import ke.co.rhino.docs.repo.GroupRepo;
import ke.co.rhino.docs.repo.UserRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
@Service("userService")
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private GroupRepo groupRepo;

    @Override
    public Result<User> create(Long groupId, String username,
                               String password, String firstName,
                               String surname, Boolean locked,
                               Boolean expired, String actionUsername) {

        if(groupId==null||groupId<1){
            return ResultFactory.getFailResult("Invalid group ID. Cannot save user");
        }

        if(username==null||username.trim().isEmpty()){
            return ResultFactory.getFailResult("Provide a valid non-empty username.");
        }

        if(firstName==null||firstName.trim().isEmpty()||surname==null||surname.trim().isEmpty()){
            return ResultFactory.getFailResult("First name and surname are mandatory.");
        }

        /*if(locked==null){
            locked = 'N';
        }

        if(expired==null){
            expired = 'Y';
        }*/

        if(password==null||password.isEmpty()){
            password = "Pass123";
        }

        Optional<Group> groupOpt = groupRepo.getOne(groupId);

        if(!groupOpt.isPresent()){
            return ResultFactory.getFailResult("No group with ID ["+groupId+"] was found. Cannot save user");
        }

        Optional<User> userFromUsernameOpt = repo.findByUsername(username);

        if(userFromUsernameOpt.isPresent()){
            return ResultFactory.getFailResult("Another user with that username already exists. Please choose a different username.");
        }

        User.UserBuilder builder = new User.UserBuilder(username)
                .expired(expired).locked(locked).firstName(firstName)
                .surname(surname).group(groupOpt.get()).password(password);

        User user = builder.build();
        repo.save(user);
        return ResultFactory.getSuccessResult(user);
    }

    @Override
    public Result<User> update(Long userId, Long groupId,
                               String username, String password,
                               String firstName, String surname,
                               Boolean locked, Boolean expired, String actionUsername) {

        if(userId==null||userId<1){
            return ResultFactory.getFailResult("Invalid user ID. Update failed.");
        }

        if(username == null || username.trim().isEmpty()){
            return ResultFactory.getFailResult("Username cannot be empty. Update failed.");
        }

        Optional<User> userByUsernameOpt = repo.findByUsername(username);

        if(!userByUsernameOpt.isPresent()){
            return ResultFactory.getFailResult("User does not exist. Update failed.");
        }

        if(userByUsernameOpt.get().getId() != userId){
            return ResultFactory.getFailResult("Username is in use by another user in the system. Update failed.");
        }

        User.UserBuilder builder = new User.UserBuilder(username).userId(userId)
                .password(password==null?"Pass123":password);

        if(firstName != null && !firstName.trim().isEmpty()){
            builder.firstName(firstName);
        }

        if(surname != null && !surname.trim().isEmpty()){
            builder.surname(surname);
        }

        /*if(locked==null){
            locked = 'N';
        }

        if(expired==null){
            expired = 'Y';
        }*/

        builder.expired(expired).locked(locked);

        User user = builder.build();

        repo.save(user);

        return ResultFactory.getSuccessResult(user);
    }

    @Override
    public Result<User> remove(Long id, String actionUsername) {

        if(id == null || id<1){
            return ResultFactory.getFailResult("Invalid user ID. Cannot remove user");
        }

        return null;
    }

    @Override
    public Result<List<User>> findAll(String actionUsername) {

        List<User> users = repo.findAll();

        return ResultFactory.getSuccessResult(users);
    }

    @Override
    public Result<User> findByUsernameAndPassword(String username, String password) {

        if(username==null||password==null||username.trim().isEmpty()||password.trim().isEmpty()){
            return ResultFactory.getFailResult("Invalid username or password");
        }

        Optional<User> userOpt = repo.findByUsernameAndPassword(username,password);

        if(userOpt.isPresent()){

            User user = userOpt.get();

            if(user.getLocked()){
                return ResultFactory.getFailResult("Account is locked out. Contact system administrator.");
            }

            if(user.getExpired()){
                return ResultFactory.getFailResult("Account is expired. Contact system administrator.");
            }

            return ResultFactory.getSuccessResult(user);

        } else {
            Optional<User> failedUserOpt = repo.findByUsername(username);
            if(failedUserOpt.isPresent()){
                User failedUser = failedUserOpt.get();
                int failedAttempts = failedUser.getFailedLogins();
                User.UserBuilder fakeUserBuilder = new User.UserBuilder(username).failedLogins(failedAttempts++);
                if(failedAttempts>3){
                    fakeUserBuilder.locked(true);
                }
                repo.save(fakeUserBuilder.build());
            }
            return ResultFactory.getFailResult("Invalid username or password");
        }

    }
}
