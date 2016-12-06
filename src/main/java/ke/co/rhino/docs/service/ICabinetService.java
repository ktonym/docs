package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetType;
import ke.co.rhino.docs.vo.Result;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Created by akipkoech on 22/07/2016.
 */
public interface ICabinetService {

    Result<Cabinet> store(Optional<Long> cabinetId,
                          CabinetType cabinetType,
                          //Integer shelfNumber,
                          String actionUsername);

    Result<Cabinet> remove(Long cabinetId,String actionUsername);

    Result<Page<Cabinet>> findAll(int page,int size,String actionUsername);

    Result<List<Cabinet>> findAll();

    Result<Cabinet> find(Long cabinetId);
}
