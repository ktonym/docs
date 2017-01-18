package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.File;
import ke.co.rhino.docs.entity.FileStatus;
import ke.co.rhino.docs.service.IFileService;
import ke.co.rhino.docs.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by anthony.kipkoech on 1/9/2017.
 */
@Controller
@RequestMapping("/file")
public class FileHandler extends AbstractHandler {

    @Autowired
    private IFileService fileService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String create(@RequestParam(value = "data") String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long categoryId = ((JsonNumber) jsonObject.get("categoryId")).longValue();
        Long volumeId = ((JsonNumber) jsonObject.get("volumeId")).longValue();
        FileStatus status = FileStatus.valueOf(jsonObject.getString("status"));
        String description = jsonObject.getString("fileDesc");
        String code = jsonObject.getString("code") == null ? "" : jsonObject.getString("code");
        String dateStr = jsonObject.getString("date");
        LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT_yyyyMMdd);
        String location = jsonObject.getString("location") == null ? "" : jsonObject.getString("location");

        Result<File> ar = fileService.create(categoryId,volumeId,status,description,code,date,location,"akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String update(@RequestParam(value = "data") String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long fileId = ((JsonNumber) jsonObject.get("fileId")).longValue();
        Long categoryId = ((JsonNumber) jsonObject.get("categoryId")).longValue();
        Long volumeId = ((JsonNumber) jsonObject.get("volumeId")).longValue();
        FileStatus status = FileStatus.valueOf(jsonObject.getString("status"));
        String description = jsonObject.getString("fileDesc");
        String code = jsonObject.getString("code") == null ? "" : jsonObject.getString("code");
        String dateStr = jsonObject.getString("date");
        LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT_yyyyMMdd);
        String location = jsonObject.getString("location") == null ? "" : jsonObject.getString("location");

        Result<File> ar = fileService.update(fileId,categoryId,volumeId,status,description,code,date,location,"akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String delete(@RequestParam(value = "data") String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long fileId = ((JsonNumber) jsonObject.get("fileId")).longValue();

        Result<File> ar = fileService.remove(fileId,"akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){

        Result<List<File>> ar = fileService.findAll("akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }


}
