package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.entity.Role;
import ke.co.rhino.docs.entity.User;
import ke.co.rhino.docs.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
public interface UserRepo extends PagingAndSortingRepository<User,Long> {

    long countByGroup(Group group);

    //long countByUserRole(UserRole userRole);

    Optional<User> findByUsername(String username);

    List<User> findByUserRoles_Role(Role role);

    Page<User> findByLocked(Character locked, Pageable pageable);

    Page<User> findByExpired(Character expired,Pageable pageable);

    Page<User> findAll(Pageable pageable);

    List<User> findAll();

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> getOne(Long idUser);
}
