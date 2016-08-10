package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface FileRepo extends PagingAndSortingRepository<File,Long> {

    Optional<File> getOne(Long fileId);

    Page<File> findByCategory(Category category,Pageable pageable);

    Page<File> findByCategoryClient(Client client,Pageable pageable);

}
