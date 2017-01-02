package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.repo.GroupRepo;
import ke.co.rhino.docs.repo.RoleRepo;
import ke.co.rhino.docs.repo.UserRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
@Service("groupService")
@Transactional
public class GroupService implements IGroupService {

    @Autowired
    private GroupRepo repo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Group> create(String name, String description, String actionUsername) {

        if(name.isEmpty() || name == null){
            return ResultFactory.getFailResult("Group name cannot be empty. Creation failed.");
        }

        /*if(description.isEmpty()||description==null){
            return ResultFactory.getFailResult("Description cannot be empty. Creation failed.");
        }*/

        Group.GroupBuilder builder = new Group.GroupBuilder(name);
        if(!description.trim().isEmpty()){
            builder.description(description);
        }

        Group group = builder.build();
        repo.save(group);
        return ResultFactory.getSuccessResult(group);

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Group> update(Long id, String name, String description, String actionUsername) {

        if(id==null || id<1){
            return ResultFactory.getFailResult("Invalid group ID provided. Update failed.");
        }

        if(name == null || name.trim().isEmpty()){
            return ResultFactory.getFailResult("Group name cannot be empty. Update failed.");
        }
        Optional<Group> groupByIdOpt = repo.getOne(id);
        if(groupByIdOpt.isPresent()){
            Optional<Group> testGroupOpt = repo.findByName(name);
            if(testGroupOpt.isPresent() && testGroupOpt.get().getId() != id){
                return ResultFactory.getFailResult("The name given belongs to another group. Update failed.");
            }
            Group.GroupBuilder builder = new Group.GroupBuilder(name).id(id);
            if(!description.trim().isEmpty() && description!=null){
                builder.description(description);
            }
            Group group = builder.build();
            return ResultFactory.getSuccessResult(group);

        } else {
            return ResultFactory.getFailResult("No group with ID ["+id+"] was found in the system. Update failed");
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Group> remove(Long id, String actionUsername) {

        if (id==null||id<1){
            return ResultFactory.getFailResult("Invalid group ID provided. Cannot delete");
        }

        Optional<Group> groupOpt = repo.getOne(id);
        if(!groupOpt.isPresent()){
            return ResultFactory.getFailResult("No group with ID["+id+"]was found. Cannot delete.");
        }
        Group group = groupOpt.get();
        if(userRepo.countByGroup(group)>0 || roleRepo.countByGroup(group)>0){
            return ResultFactory.getFailResult("Group has roles and/or users assigned to it. Cannot delete");
        }

        repo.delete(group);
        String msg = "Group ["+ group.getName()+"] was deleted by ".concat(actionUsername);
        return ResultFactory.getSuccessResult(msg);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Group>> findAll(String actionUsername) {
        List<Group> groups = repo.findAll();
        return ResultFactory.getSuccessResult(groups);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Group> findByName(String name, String actionUsername) {
        if(name==null||name.trim().isEmpty()){
            return ResultFactory.getFailResult("Search name cannot be empty.");
        }
        Optional<Group> groupOpt = repo.findByName(name);

        if(groupOpt.isPresent()){
            return ResultFactory.getSuccessResult(groupOpt.get());
        } else {
            return ResultFactory.getFailResult("No group with such a name");
        }

    }
}
