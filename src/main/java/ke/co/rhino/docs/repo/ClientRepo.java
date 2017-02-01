package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface ClientRepo extends PagingAndSortingRepository<Client,Long> {


    Optional<Client> getOne(Long clientId);
    Optional<Client> findByPin(String pin);
    Optional<Client> findByClientName(String clientName);
    Optional<Page<Client>> findByClientNameLike(String searchStr, Pageable pageable);
    @Query("SELECT c FROM Client c")
    List<Client> getAll();

    /*@Query("SELECT c FROM Client c WHERE c.rowId = :rowId")
    List<Client> findByCabinetRowId(@Param("rowId") Long rowId);*/
}
