package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface CategoryRepo extends PagingAndSortingRepository<Category,Long> {

    Category findByName(String name);
    List<Category> findAll();
    Optional<Category> getOne(Long categoryId);
    //Page<Category> findByNameLike(String searchStr, Pageable pageable);
}
