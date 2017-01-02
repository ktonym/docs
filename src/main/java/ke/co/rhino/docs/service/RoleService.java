package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.entity.Role;
import ke.co.rhino.docs.entity.User;
import ke.co.rhino.docs.repo.GroupRepo;
import ke.co.rhino.docs.repo.RoleRepo;
import ke.co.rhino.docs.repo.UserRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 12/31/2016.
 */
@Service("roleService")
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GroupRepo groupRepo;

    @Override
    public Result<Role> create(String name, Optional<Long> idGroupOpt, String actionUsername) {

        if(name==null||name.trim().isEmpty()){
            return ResultFactory.getFailResult("Invalid role name.");
        }
        Optional<Role> testRoleOpt = roleRepo.findByName(name);
        if(testRoleOpt.isPresent()){
            return ResultFactory.getFailResult("Role already exists.");
        }
        Role.RoleBuilder builder = new Role.RoleBuilder().name(name);
        if(idGroupOpt.isPresent()){
            Optional<Group> groupOpt = groupRepo.getOne(idGroupOpt.get());
            if(groupOpt.isPresent()){
                Group group = groupOpt.get();
                builder.group(group);
            }
        }
        Role role = builder.build();
        roleRepo.save(role);
        return ResultFactory.getSuccessResult(role);
    }

    @Override
    public Result<Role> update(Long id, String name, Optional<Long> idGroupOpt, String actionUsername) {

        if(id==null||id<1){
            return ResultFactory.getFailResult("Invalid role ID provided.");
        }
        if(name==null||name.trim().isEmpty()){
            return ResultFactory.getFailResult("Role name cannot be empty. Update failed.");
        }
        Optional<Role> testRoleOpt = roleRepo.findByName(name);
        if(testRoleOpt.isPresent()&&testRoleOpt.get().getId()!=id){
                return ResultFactory.getFailResult("Name of role belongs to another role in the system. Update failed.");
        }
        Role.RoleBuilder builder = new Role.RoleBuilder().name(name);
        if(idGroupOpt.isPresent()){
            Optional<Group> groupOpt = groupRepo.getOne(idGroupOpt.get());
            if(!groupOpt.isPresent()){
                return ResultFactory.getFailResult("Invalid group ID supplied. Update failed.");
            }
            builder.group(groupOpt.get());
        }
        Role role = builder.build();
        roleRepo.save(role);
        return ResultFactory.getSuccessResult(role);
    }

    @Override
    public Result<List<Role>> findAll() {
        List<Role> roles = roleRepo.findAll();
        return ResultFactory.getSuccessResult(roles);
    }

    @Override
    public Result<List<Role>> findByUser(Long idUser, String actionUsername) {

        if(idUser==null||idUser<1){
            return ResultFactory.getFailResult("Invalid user ID provided.");
        }

        Optional<User> userOpt = userRepo.getOne(idUser);

        if(userOpt.isPresent()){
            List<Role> roles = roleRepo.findByUserRoles_User(userOpt.get());
            return ResultFactory.getSuccessResult(roles);
        } else {
            return ResultFactory.getFailResult("Invalid user ID provided.");
        }

    }

    @Override
    public Result<List<Role>> findByUser(String username, String actionUsername) {
        if(username.trim().isEmpty()||username==null){
            return ResultFactory.getFailResult("Invalid username supplied. Roles cannot be loaded.");
        }
        Optional<User> userOpt = userRepo.findByUsername(username);
        if(userOpt.isPresent()){
            List<Role> roles = roleRepo.findByUserRoles_User(userOpt.get());
            return ResultFactory.getSuccessResult(roles);
        } else {
            return ResultFactory.getFailResult("Invalid username provided.");
        }
    }

    @Override
    public Result<List<Role>> findByGroup(Long idGroup, String actionUsername) {

        if(idGroup==null||idGroup<1){
            return ResultFactory.getFailResult("Invalid group ID provided.");
        }

        Optional<Group> groupOpt = groupRepo.getOne(idGroup);

        if(groupOpt.isPresent()){
            List<Role> roles = roleRepo.findByGroup(groupOpt.get());
            return ResultFactory.getSuccessResult(roles);
        } else {
            return ResultFactory.getFailResult("Invalid group ID provided.");
        }
    }
}
