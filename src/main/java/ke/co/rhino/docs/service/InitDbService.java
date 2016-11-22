package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetType;
import ke.co.rhino.docs.repo.CabinetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by user on 21-Oct-16.
 */
@Service
@Transactional
public class InitDbService {

    @Autowired
    private CabinetRepo cabRepo;

    @PostConstruct
    public void init(){

        Long[] cabs = {1L,2L,3L,4L};

        Arrays.stream(cabs).map(c->{
                    CabinetType type = c%2 == 0 ? CabinetType.CLOSED : CabinetType.OPEN;
                    return new Cabinet.CabinetBuilder().cabinetId(c).cabinetType(type).build();
                })
                .forEach(cabinet ->{
                    cabRepo.save(cabinet);
                });

    }

}
