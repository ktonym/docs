package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.entity.CabinetRowId;
import ke.co.rhino.docs.vo.Result;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface ICabinetRowService {

    Result<CabinetRow> store(Optional<Long> cabinetRowId, Long rowNumber, Long cabinetId, String actionUsername);

    Result<Page<CabinetRow>> findAll(Long cabinetId,int page,int size,String actionUsername);

    Result<CabinetRow> remove(Long cabinetRowId,String actionUsername);

    Result<List<CabinetRow>> findByCabinet(Cabinet cab);

    Result<CabinetRow> find(Long cabinetRowId);

    Result<List<CabinetRow>> findZote(String actionUsername);
}
