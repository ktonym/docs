package ke.co.rhino.docs.service;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.entity.File;
import ke.co.rhino.docs.entity.FileStatus;
import ke.co.rhino.docs.entity.Volume;
import ke.co.rhino.docs.repo.CategoryRepo;
import ke.co.rhino.docs.repo.CirculationRepo;
import ke.co.rhino.docs.repo.FileRepo;
import ke.co.rhino.docs.repo.VolumeRepo;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by anthony.kipkoech on 1/7/2017.
 */
@Service
@Transactional
public class FileService implements IFileService {

    @Autowired
    private FileRepo repo;
    @Autowired
    private VolumeRepo volumeRepo;
    @Autowired
    private CirculationRepo circulationRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<File> create(Long categoryId, Long volumeId, FileStatus status, String description, String code, LocalDate date, String location, String actionUsername) {

        if(categoryId==null||categoryId<1){
            return ResultFactory.getFailResult("Invalid category ID provided. Cannot save file.");
        }
        if(volumeId==null||volumeId<1){
            return ResultFactory.getFailResult("Invalid volume ID provided. Cannot save file.");
        }

        Optional<Category> categoryOpt = categoryRepo.getOne(categoryId);

        if(!categoryOpt.isPresent()){
            return ResultFactory.getFailResult("No category with ID ["+categoryId+"] was found. Cannot save file.");
        }

        Optional<Volume> volumeOpt = volumeRepo.getOne(volumeId);
        if(!volumeOpt.isPresent()){
            return ResultFactory.getFailResult("No volume with ID ["+volumeId+"] was found. Cannot save file.");
        }

        if(status==null){
            status = FileStatus.OPEN;
        }

        if(code==null){
            code = new String("");
        } else {
            File fileByCode = repo.findByCode(code);
            if(fileByCode!=null){
                return ResultFactory.getFailResult("A file with a similar code already exists. Cannot save");
            }
        }

        File.FileBuilder builder = new File.FileBuilder().category(categoryOpt.get())
                .code(code).date(date).status(status).volume(volumeOpt.get());

        if (!description.trim().isEmpty()) builder.description(description);
        if (!location.trim().isEmpty()) builder.location(location);

        File file = builder.build();
        repo.save(file);

        return ResultFactory.getSuccessResult(file);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<File> update(Long fileId, Long categoryId, Long volumeId, FileStatus status, String description, String code, LocalDate date, String location, String actionUsername) {

        if(fileId==null||fileId<1){
            return ResultFactory.getFailResult("Invalid file ID provided. Cannot update.");
        }

        File.FileBuilder builder = new File.FileBuilder().fileId(fileId);
        if (code != null) {
            File fileByCode = repo.findByCode(code);
            if(fileByCode!=null && fileByCode.getId()!=fileId){
                return ResultFactory.getFailResult("A different file with a similar code already exists. Cannot save");
            }
            builder.code(code);
        }

        if(categoryId!=null) {
            categoryRepo.getOne(categoryId).ifPresent(category -> {
                builder.category(category);
            });
        }

        if(volumeId!=null){
            volumeRepo.getOne(volumeId).ifPresent(volume -> {builder.volume(volume);});
        }

        if(status!=null) builder.status(status);
        if(date!=null) builder.date(date);
        if(description!=null) builder.description(description);
        if(location!=null) builder.location(location);
        File file = builder.build();
        repo.save(file);
        return ResultFactory.getSuccessResult(file);
    }

    @Override
    public Result<List<File>> findAll(String actionUsername) {
        List<File> files = repo.findAll();
        return ResultFactory.getSuccessResult(files);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<File> remove(Long fileId, String actionUsername) {

        if(fileId==null||fileId<1){
            return ResultFactory.getFailResult("Invalid file ID provided. Cannot delete.");
        }

        Optional<File> fileOpt = repo.getOne(fileId);
        if(fileOpt.isPresent()){

            long cntCirculation = circulationRepo.countByFile(fileOpt.get());
            if(cntCirculation>0){
                return ResultFactory.getFailResult("File has child records associated with it. Cannot delete.");
            }
            String msg = fileOpt.get().toString().concat(" deleted by ").concat(actionUsername);
            repo.delete(fileId);
            return ResultFactory.getSuccessResult(msg);
        }

        return ResultFactory.getFailResult("No file with ID["+fileId+"] was found in the system. Cannot delete.");
    }

    @Override
    public Result<List<File>> findByVolume(Long volumeId, String actionUsername) {

        if(volumeId==null||volumeId<1){
            return ResultFactory.getFailResult("Invalid volume ID provided.");
        }

        Optional<Volume> volumeOpt = volumeRepo.getOne(volumeId);

        //new ArrayList<>();

        if(volumeOpt.isPresent()){
            List<File> fileList = repo.findByVolume(volumeOpt.get());
            return ResultFactory.getSuccessResult(fileList);
        }
        return ResultFactory.getFailResult("Invalid volume ID provided.");
    }
}
