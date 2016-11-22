package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetType;
import ke.co.rhino.docs.vo.Result;
import org.springframework.data.domain.Page;

/**
 * Created by akipkoech on 22/07/2016.
 */
public interface ICabinetService {

    Result<Cabinet> store(Long cabinetId,
                          CabinetType cabinetType,
                          //Integer shelfNumber,
                          String actionUsername);

    Result<Cabinet> remove(Long cabinetId,String actionUsername);

    Result<Page<Cabinet>> findAll(int page,int size,String actionUsername);
}
