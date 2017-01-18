package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.Circulation;
import ke.co.rhino.docs.service.ICirculationService;
import ke.co.rhino.docs.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * Created by anthony.kipkoech on 1/9/2017.
 */
@Controller
@RequestMapping("/circulation")
public class CirculationHandler extends AbstractHandler {

    @Autowired
    private ICirculationService circulationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String create(@RequestParam(value = "data") String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        String borrowedDateVal = jsonObject.getString("borrowed");
        LocalDate borrowed = LocalDate.parse(borrowedDateVal,DATE_FORMAT_yyyyMMdd);
        Integer days = ((JsonNumber) jsonObject.get("days")).intValue();
        Long userId = ((JsonNumber) jsonObject.get("userId")).longValue();
        Long fileId = ((JsonNumber) jsonObject.get("fileId")).longValue();
        Result<Circulation> ar = circulationService.create(borrowed,days,userId,fileId,"akipkoech");

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
        Long circulationId = ((JsonNumber) jsonObject.get("circulationId")).longValue();
        String borrowedDateVal = jsonObject.getString("returned");
        LocalDate returned = LocalDate.parse(borrowedDateVal,DATE_FORMAT_yyyyMMdd);
        Integer days = ((JsonNumber) jsonObject.get("days")).intValue();

        Result<Circulation> ar = circulationService.update(circulationId,days,returned,"akipkoech");

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
        Long circulationId = ((JsonNumber) jsonObject.get("circulationId")).longValue();

        Result<Circulation> ar = circulationService.delete(circulationId,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){

        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        int size = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));


        Result<Page<Circulation>> ar = circulationService.findAll(page,size,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findByBorrowedBtn", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findByBorrowedBtn(@RequestParam(value = "start") String startStr,@RequestParam(value = "end") String endStr, HttpServletRequest request){

        LocalDate start = LocalDate.parse(startStr,DATE_FORMAT_yyyyMMdd);
        LocalDate end = LocalDate.parse(endStr,DATE_FORMAT_yyyyMMdd);

        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        int size = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));

        Result<Page<Circulation>> ar = circulationService.findByBorrowedDateBtn(start,end,page,size,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findByReturnDateBtn", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findByReturnDateBtn(@RequestParam(value = "start") String startStr,@RequestParam(value = "end") String endStr, HttpServletRequest request){

        LocalDate start = LocalDate.parse(startStr,DATE_FORMAT_yyyyMMdd);
        LocalDate end = LocalDate.parse(endStr,DATE_FORMAT_yyyyMMdd);

        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        int size = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));

        Result<Page<Circulation>> ar = circulationService.findByReturnDateBtn(start,end,page,size,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findByUser", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findByUser(@RequestParam(value = "userId") String userIdStr, HttpServletRequest request){

        Long userId = Long.valueOf(userIdStr);

        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        int size = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));

        Result<Page<Circulation>> ar = circulationService.findByUser(userId,page,size,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findByFile", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findByFile(@RequestParam(value = "fileId") String fileIdStr, HttpServletRequest request){

        Long fileId = Long.valueOf(fileIdStr);

        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        int size = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));

        Result<Page<Circulation>> ar = circulationService.findByFile(fileId,page,size,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }


}
