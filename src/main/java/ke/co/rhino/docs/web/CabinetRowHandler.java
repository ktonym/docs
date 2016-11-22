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

/**
 * Created by user on 20-Nov-16.
 */
@Controller()
@RequestMapping("/cabinetrow")
public class CabinetRowHandler extends AbstractHandler{

    @Autowired
    private ICabinetRowService cabinetRowService;

    @RequestMapping(value = "/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String store(@RequestParam(value = "data",required = true) String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);

        Long rowNumber = ((JsonNumber) jsonObject.get("rowNumber")).longValue();
        Long cabinetId = ((JsonNumber) jsonObject.get("cabinetId")).longValue();

        Result<CabinetRow> ar = cabinetRowService.store(rowNumber,cabinetId,"AKipkoech");

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
        Long cabinetId = ((JsonNumber) jsonObject.get("cabinetRowId")).longValue();
        Long rowNumber = ((JsonNumber) jsonObject.get("rowNumber")).longValue();
        Result<CabinetRow> ar = cabinetRowService.remove(rowNumber, cabinetId, "akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){

        int page = Integer.valueOf(request.getParameter("page"));
        int size = Integer.valueOf(request.getParameter("limit"));
        String actionUsername = "akipkoech";
        Result<Page<CabinetRow>> ar =  cabinetRowService.findAll(page,size,actionUsername);

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

}
