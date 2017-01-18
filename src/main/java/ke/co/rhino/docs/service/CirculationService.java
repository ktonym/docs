package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Circulation;
import ke.co.rhino.docs.entity.File;
import ke.co.rhino.docs.entity.User;
import ke.co.rhino.docs.repo.CirculationRepo;
import ke.co.rhino.docs.repo.FileRepo;
import ke.co.rhino.docs.repo.UserRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 1/8/2017.
 */
@Service("circulationService")
@Transactional
public class CirculationService implements ICirculationService {

    @Autowired
    private CirculationRepo repo;
    @Autowired
    private FileRepo fileRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Circulation> create(LocalDate borrowed, Integer days,
                                      /*LocalDate returned,*/ Long userId,
                                      Long fileId, String actionUsername) {

        if(borrowed==null) {
            return ResultFactory.getFailResult("Borrowed date cannot be null.");
        }

        Period duration = Period.ofDays(days);

        if(userId==null) return ResultFactory.getFailResult("Please provide a valid user.");
        if(fileId==null||fileId<1) return ResultFactory.getFailResult("Please provide a valid file.");

        Optional<User> userOpt = userRepo.getOne(userId);
        if(!userOpt.isPresent()){
            return ResultFactory.getFailResult("No user with ID ["+userId+"] was found. Cannot circulate file.");
        }
        Optional<File> fileOpt = fileRepo.getOne(fileId);
        if(!fileOpt.isPresent()) return ResultFactory.getFailResult("No file with ID ["+fileId+"] was found. Cannot circulate file.");

        Circulation circulation = new Circulation.CirculationBuilder().user(userOpt.get())
                .duration(duration).borrowed(borrowed).file(fileOpt.get()).build();

        repo.save(circulation);

        return ResultFactory.getSuccessResult(circulation);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Circulation> update(Long circulationId, /*LocalDate borrowed,*/
                                      Integer days, LocalDate returned,
                                      /*Long userId, Long fileId,*/ String actionUsername) {

        if(circulationId==null||circulationId<1){
            return ResultFactory.getFailResult("Circulation ID cannot be null. Update failed.");
        }
        Optional<Circulation> circulationOpt = repo.getOne(circulationId);
        if(circulationOpt.isPresent()){
            Circulation circulation = circulationOpt.get();

            Circulation.CirculationBuilder builder = new Circulation.CirculationBuilder().circulationId(circulationId);

            if(days!=null) {
                if (days > circulation.getDuration().getDays() && circulation.getBorrowed().plus(circulation.getDuration()).isBefore(LocalDate.now())) {
                    return ResultFactory.getFailResult("Cannot add duration of circulation after due date.");
                }
                builder.duration(Period.ofDays(days));
            }

            if(returned!=null){
                if(circulation.getBorrowed().plus(circulation.getDuration()).isBefore(returned)){
                    return ResultFactory.getFailResult("Cannot extend duration of circulation.");
                }
                builder.returned(returned);
            }
            Circulation circ = builder.build();
            repo.save(circ);
            return ResultFactory.getSuccessResult(circ);

        } else {
            return ResultFactory.getFailResult("No circulation with ID ["+circulationId+"] was found.");
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Circulation> delete(Long circulationId, String actionUsername) {
        return ResultFactory.getFailResult("Deletion of circulations not yet allowed.");
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Page<Circulation>> findAll(int page, int size, String actionUsername) {

        PageRequest request = new PageRequest(page-1,size);
        Page<Circulation> circulations = repo.findAll(request);
        return ResultFactory.getSuccessResult(circulations);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Page<Circulation>> findByUser(Long userId, int page, int size, String actionUsername) {

        if(userId == null || userId <1 ){
            return ResultFactory.getFailResult("Invalid user ID provided.");
        }

        Optional<User> userOpt = userRepo.getOne(userId);
        if(!userOpt.isPresent()){
            return ResultFactory.getFailResult("No user with ID ["+userId+"] was found.");
        }
        User user = userOpt.get();

        PageRequest request = new PageRequest(page-1,size);
        Page<Circulation> circulations = repo.findByUser(user,request);
        return ResultFactory.getSuccessResult(circulations);

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Page<Circulation>> findByFile(Long fileId, int page, int size, String actionUsername) {

        if(fileId == null || fileId <1 ){
            return ResultFactory.getFailResult("Invalid file ID provided.");
        }

        Optional<File> fileOpt = fileRepo.getOne(fileId);
        if(!fileOpt.isPresent()){
            return ResultFactory.getFailResult("No file with ID ["+fileId+"] was found.");
        }
        File file = fileOpt.get();

        PageRequest request = new PageRequest(page-1,size);
        Page<Circulation> circulations = repo.findByFile(file,request);
        return ResultFactory.getSuccessResult(circulations);

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Page<Circulation>> findByBorrowedDateBtn(LocalDate start,LocalDate end, int page, int size, String actionUsername) {

        if(start==null){
            return ResultFactory.getFailResult("Invalid start[borrowed] date provided.");
        }
        if(end==null){
            return ResultFactory.getFailResult("Invalid end[borrowed] date provided.");
        }

        PageRequest request = new PageRequest(page-1,size);
        Page<Circulation> circulations = repo.findByBorrowedBetween(start,end,request);
        return ResultFactory.getSuccessResult(circulations);

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Page<Circulation>> findByReturnDateBtn(LocalDate start, LocalDate end, int page, int size, String actionUsername) {

        if(start==null){
            return ResultFactory.getFailResult("Invalid start[return] date provided.");
        }
        if(end==null){
            return ResultFactory.getFailResult("Invalid end[return] date provided.");
        }

        PageRequest request = new PageRequest(page-1,size);
        Page<Circulation> circulations = repo.findByReturnedBetween(start,end,request);
        return ResultFactory.getSuccessResult(circulations);

    }
}
