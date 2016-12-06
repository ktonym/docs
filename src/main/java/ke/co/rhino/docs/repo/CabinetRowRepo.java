package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.CabinetRowId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by akipkoech on 22/07/2016.
 */
public interface CabinetRowRepo extends PagingAndSortingRepository<CabinetRow, CabinetRowId> {

    Optional<CabinetRow> getOne(CabinetRowId id);

    CabinetRow findOne(CabinetRowId id);

    Page<CabinetRow> findByCabinet(Cabinet cabinet, Pageable pageable);

    List<CabinetRow> findByCabinet(Cabinet cabinet);

    long countByCabinet(Cabinet cabinet);

    CabinetRow findByCabinetAndRowNumber(Cabinet cabinet, Long rowNo);

}
