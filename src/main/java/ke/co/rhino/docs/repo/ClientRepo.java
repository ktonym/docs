package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface ClientRepo extends PagingAndSortingRepository<Client,Long> {

    long countByCabinetRow(CabinetRow row);

}
