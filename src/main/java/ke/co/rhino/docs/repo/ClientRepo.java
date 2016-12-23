package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.vo.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface ClientRepo extends PagingAndSortingRepository<Client,Long> {

    long countByCabinetRow(CabinetRow row);
    Optional<Client> getOne(Long clientId);
    Optional<Client> findByPin(String pin);
    Optional<Client> findByClientName(String clientName);
    Optional<Page<Client>> findByClientNameLike(String searchStr, Pageable pageable);
    List<Client> findByCabinetRow(CabinetRow row);

    @Query("SELECT c FROM Client c")
    List<Client> getAll();
}
