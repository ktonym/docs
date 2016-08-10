package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.vo.Result;
import org.springframework.data.domain.Page;

/**
 * Created by akipkoech on 10/08/2016.
 */
public interface ICabinetRowService {

    Result<CabinetRow> store(Long rowNumber,Long cabinetId,String actionUsername);

    Result<Page<CabinetRow>> findAll(int page,int size,String actionUsername);

    Result<CabinetRow> remove(Long rowNumber,Long cabinetId,String actionUsername);

}
