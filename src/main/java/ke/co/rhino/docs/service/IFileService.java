package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.File;
import ke.co.rhino.docs.entity.FileStatus;
import ke.co.rhino.docs.vo.Result;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

/**
 * Created by anthony.kipkoech on 1/7/2017.
 */
public interface IFileService {

    Result<File> create(Long categoryId,Long volumeId,
                        FileStatus status, String description,
                        String code, LocalDate date,
                        String location, String actionUsername);
    Result<File> update(Long fileId, Long categoryId,
                        Long volumeId,FileStatus status, String description,
                        String code, LocalDate date,
                        String location, String actionUsername);
    Result<List<File>> findAll(String actionUsername);
    Result<File> remove(Long fileId, String actionUsername);

}
