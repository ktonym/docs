package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.Client;
import ke.co.rhino.docs.service.IClientService;
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
import java.util.List;
import java.util.Optional;

/**
 * Created by user on 20-Nov-16.
 */
@Controller
@RequestMapping("/client")
public class ClientHandler extends AbstractHandler {

    @Autowired
    private IClientService clientService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String create(@RequestParam(value = "data")String jsonData,HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        //Long cabinetId = ((JsonNumber) jsonObject.get("cabinetId")).longValue();
       // Long clientId = ((JsonNumber) jsonObject.get("clientId")).longValue();
        String clientName = jsonObject.getString("clientName");
        String tel = jsonObject.getString("tel");
        String pin = jsonObject.getString("pin");
        String email = jsonObject.getString("email");
        Long rowId = ((JsonNumber) jsonObject.get("rowId")).longValue();

        Optional<Long> clientIdOpt = Optional.empty();

        Result<Client> ar = clientService.store(clientIdOpt,clientName,rowId,tel,email,pin,"AKipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String update(@RequestParam(value = "data")String jsonData,HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);
        //Long cabinetId = ((JsonNumber) jsonObject.get("cabinetId")).longValue();
        // Long clientId = ((JsonNumber) jsonObject.get("clientId")).longValue();
        String clientName = jsonObject.getString("clientName");
        String tel = jsonObject.getString("tel");
        String pin = jsonObject.getString("pin");
        String email = jsonObject.getString("email");
        Long rowId = ((JsonNumber) jsonObject.get("rowId")).longValue();

        Long clientId;
        Optional<Long> clientIdOpt;

        clientId = ((JsonNumber) jsonObject.get("clientId")).longValue();
        clientIdOpt = Optional.of(clientId);

        Result<Client> ar = clientService.store(clientIdOpt,clientName,rowId,tel,email,pin,"AKipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){
        Result<List<Client>> ar = clientService.findEverything("AKipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findByRow", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findByRow(HttpServletRequest request, @RequestParam(value = "rowId") String rowIdStr){

        Long rowId = Long.valueOf(rowIdStr);

        Result<List<Client>> ar = clientService.findByRowId(rowId, "Akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

}
