package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Cabinet;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by akipkoech on 22/07/2016.
 */
public interface CabinetRepo extends PagingAndSortingRepository<Cabinet,Long> {

    //Page<Cabinet>
    Optional<Cabinet> getOne(Long cabinetId);

}
