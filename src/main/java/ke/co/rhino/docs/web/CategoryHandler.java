package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.Category;
import ke.co.rhino.docs.service.ICategoryService;
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
 * Created by user on 22-Nov-16.
 */
@Controller
@RequestMapping("/category")
public class CategoryHandler extends AbstractHandler{

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/create",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String create(@RequestParam(value = "data") String jsonData){

        JsonObject jsonObject = parseJsonObject(jsonData);

        //Long categoryId = ((JsonNumber) jsonObject.get("categoryId")).longValue();
        Long clientId = ((JsonNumber) jsonObject.get("clientId")).longValue();
        Long categoryRefId = ((JsonNumber) jsonObject.get("categoryRefId")).longValue();
        String description = jsonObject.getString("description");

        Result<Category> ar = categoryService.create(categoryRefId,clientId,description,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String update(@RequestParam(value = "data") String jsonData){

        JsonObject jsonObject = parseJsonObject(jsonData);

        Long categoryId = ((JsonNumber) jsonObject.get("categoryId")).longValue();
        Long clientId = ((JsonNumber) jsonObject.get("clientId")).longValue();
        Long categoryRefId = ((JsonNumber) jsonObject.get("categoryRefId")).longValue();
        String description = jsonObject.getString("description");

        Result<Category> ar = categoryService.update(categoryRefId,categoryId,clientId,description,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }



    @RequestMapping(value = "/findAll",method = RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public String findAll(@RequestParam(value = "clientId") String clientIdStr, HttpServletRequest request){

        Long clientId = Long.valueOf(clientIdStr);

        int page = Integer.valueOf(request.getParameter("page"));
        int size = Integer.valueOf(request.getParameter("limit"));
        String actionUsername = "akipkoech";

        Result<Page<Category>> ar = categoryService.findAll(clientId,page,size,actionUsername);

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

}
