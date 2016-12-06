package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Cabinet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by akipkoech on 22/07/2016.
 */
public interface CabinetRepo extends PagingAndSortingRepository<Cabinet,Long> {

    //Page<Cabinet>
    Optional<Cabinet> getOne(Long cabinetId);

    @Query("SELECT c FROM Cabinet c")
    List<Cabinet> getAll();

}
