package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.entity.Volume;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 1/7/2017.
 */
public interface VolumeRepo extends PagingAndSortingRepository<Volume,Long> {

    Optional<Volume> getOne(Long volumeId);
    Optional<List<Volume>> findByCabinetRow(CabinetRow row);
    Optional<List<Volume>> findByClient(Client client);
    Optional<Volume> findByClientAndVolumeNo(Client client,Integer volNo);
    List<Volume> findAll();
    long countByClient(Client client);
    long countByCabinetRow(CabinetRow row);

}
