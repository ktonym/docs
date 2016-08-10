package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.CabinetRowId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

/**
 * Created by akipkoech on 22/07/2016.
 */
public interface CabinetRowRepo extends PagingAndSortingRepository<CabinetRow, CabinetRowId> {

    Optional<CabinetRow> getOne(CabinetRowId id);

    Set<CabinetRow> findByCabinet(Cabinet cabinet);

    long countByCabinet(Cabinet cabinet);

}
