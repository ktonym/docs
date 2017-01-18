package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.CabinetRow;
import ke.co.rhino.docs.service.ICabinetRowService;
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
import java.util.List;
import java.util.Optional;

/**
 * Created by user on 20-Nov-16.
 */
@Controller()
@RequestMapping("/cabinetrow")
public class CabinetRowHandler extends AbstractHandler{

    @Autowired
    private ICabinetRowService cabinetRowService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String create(@RequestParam(value = "data",required = true) String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);

        Long rowNumber = ((JsonNumber) jsonObject.get("rowNumber")).longValue();
        Long cabinetId = ((JsonNumber) jsonObject.get("cabinetId")).longValue();
        Long rowId;
        Optional<Long> rowIdOpt = Optional.empty();


        Result<CabinetRow> ar = cabinetRowService.store(rowIdOpt,rowNumber,cabinetId,"AKipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String update(@RequestParam(value = "data",required = true) String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);

        Long rowNumber = ((JsonNumber) jsonObject.get("rowNumber")).longValue();
        Long cabinetId = ((JsonNumber) jsonObject.get("cabinetId")).longValue();
        Long rowId;
        Optional<Long> rowIdOpt;

        rowId = ((JsonNumber) jsonObject.get("rowId")).longValue();
        rowIdOpt = Optional.of(rowId);


        Result<CabinetRow> ar = cabinetRowService.store(rowIdOpt,rowNumber,cabinetId,"AKipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String remove(@RequestParam(value = "data") String jsonData){

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long rowId = ((JsonNumber) jsonObject.get("rowId")).longValue();
        //Long rowNumber = ((JsonNumber) jsonObject.get("rowNumber")).longValue();
        Result<CabinetRow> ar = cabinetRowService.remove(rowId, "akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAll(@RequestParam(value = "cabinetId") String cabinetIdStr, HttpServletRequest request){

        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        int size = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));
        Long cabinetId = Long.valueOf(cabinetIdStr);
        String actionUsername = "akipkoech";
        Result<Page<CabinetRow>> ar =  cabinetRowService.findAll(cabinetId,page,size,actionUsername);

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/findZote", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findZote(HttpServletRequest request){
//        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
//        int size = request.getParameter("limit") == null ? 1000 : Integer.valueOf(request.getParameter("limit"));
        String actionUsername = "akipkoech";
        Result<List<CabinetRow>> ar = cabinetRowService.findZote(actionUsername);

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

}
