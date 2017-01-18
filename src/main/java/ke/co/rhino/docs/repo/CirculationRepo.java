package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Circulation;
import ke.co.rhino.docs.entity.File;
import ke.co.rhino.docs.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface CirculationRepo extends PagingAndSortingRepository<Circulation,Long> {

    Page<Circulation> findByUser(User user, Pageable pageable);

    Page<Circulation> findByFile(File file, Pageable pageable);

    Optional<Circulation> getOne(Long circulationId);

    Page<Circulation> findByBorrowedBetween(LocalDate start, LocalDate end, Pageable pageable);

    Page<Circulation> findByReturnedBetween(LocalDate start, LocalDate end, Pageable pageable);

    long countByFile(File file);

    List<Circulation> findAll();

    Page<Circulation> findAll(Pageable pageable);
}
