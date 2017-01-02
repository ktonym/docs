package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.PasswordHistory;
import ke.co.rhino.docs.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
public interface PasswordHistoryRepo extends PagingAndSortingRepository<PasswordHistory,Long> {

    List<PasswordHistory> findFirst5ByUser(User user, Sort sort);

}
