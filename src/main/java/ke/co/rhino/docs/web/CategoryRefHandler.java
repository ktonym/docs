package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.CategoryRef;
import ke.co.rhino.docs.service.ICategoryRefService;
import ke.co.rhino.docs.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by anthony.kipkoech on 1/3/2017.
 */
@Controller
@RequestMapping("/categoryref")
public class CategoryRefHandler extends AbstractHandler {

    @Autowired
    private ICategoryRefService service;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String create(@RequestParam(value = "data") String jsonData, HttpServletRequest request){
        JsonObject jsonObject = parseJsonObject(jsonData);
        String name = jsonObject.getString("name");
        Result<CategoryRef> ar = service.create(name,"akipkoech");
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
        String name = jsonObject.getString("name");
        Long id = ((JsonNumber) jsonObject.get("categoryRefId")).longValue();
        Result<CategoryRef> ar = service.update(id,name,"akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){

        Result<List<CategoryRef>> ar = service.findAll("akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

}
