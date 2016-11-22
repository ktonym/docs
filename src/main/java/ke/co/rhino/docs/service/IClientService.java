package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.vo.Result;
import org.springframework.data.domain.Page;

/**
 * Created by user on 19-Nov-16.
 */
public interface IClientService {

    Result<Client> store(Long clientId,String clientName, Long cabinetId, Long rowNo,
            String tel,String pin,String actionUsername);

    Result<Client> remove(Long clientId,String actionUsername);

    Result<Page<Client>> findAll(int page,int size,String actionUsername);

    Result<Page<Client>> findByCabinet(Long cabinetId,int page,int size,String actionUsername);

}
