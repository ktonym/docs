package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
public interface GroupRepo extends PagingAndSortingRepository<Group,Long> {

    Optional<Group> findByName(String name);

    @Query("SELECT g FROM Group g WHERE g.users IN (SELECT u FROM User u WHERE u.username =:username)")
    Group searchByUsername(@Param("username") String username);

    @Query("SELECT g FROM Group g WHERE g.roles IN (SELECT r FROM Role r WHERE r.name =:name)")
    Group searchByRole(@Param("name") String role);

    List<Group> findAll();

    Optional<Group> getOne(Long id);
}
