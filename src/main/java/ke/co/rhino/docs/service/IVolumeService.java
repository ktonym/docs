package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Volume;
import ke.co.rhino.docs.vo.Result;
import org.springframework.data.domain.Page;

import java.time.Year;
import java.util.List;

/**
 * Created by anthony.kipkoech on 1/7/2017.
 */
public interface IVolumeService  {

    Result<Volume> create(Long rowId, Long clientId,Integer volumeNo, Year year, String actionUsername);
    Result<Volume> update(Long rowId, Long volumeId, Long clientId, Integer volumeNo, Year year, String actionUsername);
    Result<Page<Volume>> findAll(int page, int size, String actionUsername);
    Result<List<Volume>> findByClient(Long clientId, String actionUsername);
    Result<Volume> remove(Long volumeId, String actionUsername);

    Result<List<Volume>> findByRowId(Long rowId, String akipkoech);
}
