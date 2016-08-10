package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface CategoryRepo extends PagingAndSortingRepository<Category,Long> {

    Page<Category> findByClient(Client client, Pageable pageable);

    Optional<Category> getOne(Long categoryId);

}
