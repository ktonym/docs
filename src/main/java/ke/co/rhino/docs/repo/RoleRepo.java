package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.entity.Role;
import ke.co.rhino.docs.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
public interface RoleRepo extends PagingAndSortingRepository<Role,Long> {

    long countByGroup(Group group);

    List<Role> findAll();

    Optional<Role> findByName(String name);

    List<Role> findByUserRoles_User(User user);

    List<Role> findByGroup(Group group);
}
