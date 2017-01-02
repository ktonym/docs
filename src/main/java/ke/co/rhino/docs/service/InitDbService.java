package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetType;
import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.repo.CabinetRepo;
import ke.co.rhino.docs.repo.GroupRepo;
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

    @Autowired
    private GroupRepo groupRepo;

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

        String[][] names = {{"ADMIN","Overall administrator"},{"USER","Normal user"},{"GUEST","Guest user of the system"}};

        Arrays.stream(names).map(n->{
            return new Group.GroupBuilder(n[0]).description(n[1]).build();
        }).forEach(group -> {
            groupRepo.save(group);
        });
    }

}
