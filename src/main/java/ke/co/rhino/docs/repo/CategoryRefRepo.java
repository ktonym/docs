package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.CategoryRef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by anthony.kipkoech on 1/3/2017.
 */
public interface CategoryRefRepo extends PagingAndSortingRepository<CategoryRef,Long> {

    List<CategoryRef> findAll();
    CategoryRef findByName(String name);
    Page<CategoryRef> findAll(Pageable pageable);
}
