package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetType;
import ke.co.rhino.docs.service.ICabinetService;
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
 * Created by akipkoech on 22/07/2016.
 */
@Controller
@RequestMapping("/cabinet")
public class CabinetHandler extends AbstractHandler {

    @Autowired
    private ICabinetService service;

    @RequestMapping(value = "/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String store(@RequestParam(value = "data")String jsonData,HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long cabinetId = ((JsonNumber) jsonObject.get("cabinetId")).longValue();
        CabinetType cabinetType = CabinetType.valueOf(jsonObject.getString("cabinetType"));
        Integer shelfNumber = getIntegerValue(jsonObject.get("shelfNumber"));
        String actionUsername = "akipkoech";

        Result<Cabinet> ar = service.store(cabinetId,cabinetType,shelfNumber,actionUsername);
        if(ar.isSuccess()){
           return getJsonSuccessData(ar.getData());
        } else {
           return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String remove(@RequestParam(value = "cabinetId")String cabinetIdStr,HttpServletRequest request){

        Long cabinetId = Long.getLong(cabinetIdStr);
        String actionUsername = "akipkoech";

        Result<Cabinet> ar = service.remove(cabinetId, actionUsername);
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
        Result<Page<Cabinet>> ar = service.findAll(page,size,actionUsername);
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }


}
