package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.AbstractEntity;
import ke.co.rhino.docs.entity.Group;
import ke.co.rhino.docs.entity.Role;
import ke.co.rhino.docs.entity.User;
import ke.co.rhino.docs.service.IGroupService;
import ke.co.rhino.docs.service.IRoleService;
import ke.co.rhino.docs.service.IUserService;
import ke.co.rhino.docs.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.servlet.GenericServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ke.co.rhino.docs.web.SecurityHelper.SESSION_ATTRIB_ROLES;
import static ke.co.rhino.docs.web.SecurityHelper.SESSION_ATTRIB_USER;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
@Controller
//@RequestMapping("/security")
public class SecurityHandler extends AbstractHandler{

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/group/create",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String createGroup(@RequestParam(value = "data") String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        String name = jsonObject.getString("name");
        String desc = jsonObject.getString("description");

        Result<Group> ar = groupService.create(name,desc,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/group/update",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String updateGroup(@RequestParam(value = "data") String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long id = ((JsonNumber)jsonObject.get("id")).longValue();
        String name = jsonObject.getString("name");
        String desc = jsonObject.getString("description");

        Result<Group> ar = groupService.update(id,name,desc,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/group/findAll",method = RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public String findAllGroups(HttpServletRequest request){

        Result<List<Group>> ar = groupService.findAll("akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/group/delete",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String deleteGroup(HttpServletRequest request, @RequestParam(value = "data") String jsonData){
        JsonObject jsonObject = parseJsonObject(jsonData);
        Long id = ((JsonNumber) jsonObject.get("id")).longValue();
        Result<Group> ar = groupService.remove(id, "akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/user/create",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String createUser(@RequestParam(value = "data") String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long groupId = ((JsonNumber) jsonObject.get("groupId")).longValue();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String firstName = jsonObject.getString("firstName");
        String surname = jsonObject.getString("surname");
//        Character locked;
        Boolean locked = jsonObject.getBoolean("locked");
//        Character expired;
        Boolean expired = jsonObject.getBoolean("expired");

        Result<User> ar = userService.create(groupId,username,password,firstName,surname,locked,expired,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/user/update",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String updateUser(@RequestParam(value = "data") String jsonData, HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long id = ((JsonNumber) jsonObject.get("userId")).longValue();
        Long groupId = ((JsonNumber) jsonObject.get("groupId")).longValue();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String firstName = jsonObject.getString("firstName");
        String surname = jsonObject.getString("surname");

        //Character locked;
        Boolean locked = jsonObject.getBoolean("locked");
        //Character expired;
        Boolean expired = jsonObject.getBoolean("expired");


        Result<User> ar = userService.update(id,groupId,username,password,firstName,surname,locked,expired,"akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/user/findAll",method = RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public String findAllUsers(HttpServletRequest request){
        Result<List<User>> ar = userService.findAll("akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/user/delete",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String deleteUser(HttpServletRequest request, @RequestParam(value = "data") String jsonData){
        JsonObject jsonObject = parseJsonObject(jsonData);
        Long id = ((JsonNumber) jsonObject.get("id")).longValue();
        Result<User> ar = userService.remove(id, "akipkoech");

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String login(HttpServletRequest request, @RequestParam(value = "data") String jsonData){
        JsonObject jsonObject = parseJsonObject(jsonData);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        Result<User> ar = userService.findByUsernameAndPassword(username,password);
        //Get roles and build a json structure with user,roles

        if(ar.isSuccess()){
            User user = ar.getData();
            Result<List<Role>> listResult = roleService.findByUser(username,"akipkoech");
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_ATTRIB_USER,user);
            session.setAttribute(SESSION_ATTRIB_ROLES,listResult);
            return getJsonSuccessData(user);
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }


}
