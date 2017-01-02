package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.UserRole;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
public interface UserRoleRepo extends PagingAndSortingRepository<UserRole,Long> {
}
