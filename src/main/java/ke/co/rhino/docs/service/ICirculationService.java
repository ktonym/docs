package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Circulation;
import ke.co.rhino.docs.vo.Result;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Created by anthony.kipkoech on 1/8/2017.
 */
public interface ICirculationService {

    Result<Circulation> create(LocalDate borrowed,
                               Integer days,
                               //LocalDate returned,
                               Long userId,
                               Long fileId, String actionUsername);
    Result<Circulation> update(Long circulationId,
                               //LocalDate borrowed,
                               Integer days,
                               LocalDate returned,
                               /*Long userId,
                               Long fileId,*/
                               String actionUsername);
    Result<Circulation> delete(Long circulationId,String actionUsername);
    Result<Page<Circulation>> findAll(int page, int size, String actionUsername);
    Result<Page<Circulation>> findByUser(Long userId, int page, int size, String actionUsername);
    Result<Page<Circulation>> findByFile(Long fileId, int page, int size, String actionUsername);
    Result<Page<Circulation>> findByBorrowedDateBtn(LocalDate start, LocalDate end, int page, int size, String actionUsername);
    Result<Page<Circulation>> findByReturnDateBtn(LocalDate start, LocalDate end, int page, int size, String actionUsername);

}
