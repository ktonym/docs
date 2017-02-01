package ke.co.rhino.docs.web;


import ke.co.rhino.docs.entity.Volume;
import ke.co.rhino.docs.service.IVolumeService;
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
import java.time.Year;
import java.util.List;

/**
 * Created by anthony.kipkoech on 1/8/2017.
 */
@Controller
@RequestMapping("/volume")
public class VolumeHandler extends AbstractHandler  {

    @Autowired
    private IVolumeService volumeService;

    @RequestMapping(value = "/create",method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String create(@RequestParam(value = "data")String jsonData, HttpServletRequest request) {

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long clientId = ((JsonNumber) jsonObject.get("clientId")).longValue();
        Integer volumeNo = ((JsonNumber) jsonObject.get("volumeNo")).intValue();
        Long rowId = ((JsonNumber) jsonObject.get("rowId")).longValue();
        /*logger.info("Peeking at value of year...");
        logger.info( jsonObject.get("year").toString());*/

        String yearStr = jsonObject.getString("year");

//        Year year = Year.of(((JsonNumber) jsonObject.get("year")).intValue());
        Year year = Year.parse(yearStr,YEAR_FORMAT_yyyy);

        Result<Volume> ar = volumeService.create(rowId,clientId,volumeNo,year,"akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String update(@RequestParam(value = "data")String jsonData, HttpServletRequest request) {

        JsonObject jsonObject = parseJsonObject(jsonData);
        Long volumeId = ((JsonNumber) jsonObject.get("volumeId")).longValue();
        Long clientId = ((JsonNumber) jsonObject.get("clientId")).longValue();
        Integer volumeNo = ((JsonNumber) jsonObject.get("volumeNo")).intValue();
        Long rowId = ((JsonNumber) jsonObject.get("rowId")).longValue();
        String yearStr = jsonObject.getString("year");
        Year year = Year.parse(yearStr,YEAR_FORMAT_yyyy);

        Result<Volume> ar = volumeService.update(rowId,volumeId,clientId,volumeNo,year,"akipkoech" );
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String delete(@RequestParam(value = "volumeId")String volumeIdStr, HttpServletRequest request) {

        Long volumeId = Long.getLong(volumeIdStr);
        String actionUsername = "akipkoech";
        Result<Volume> ar = volumeService.remove(volumeId,actionUsername);
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findByClient",method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findByClient(@RequestParam(value = "clientId")String clientIdStr,HttpServletRequest request) {

        Long clientId = Long.getLong(clientIdStr);
        Result<List<Volume>> ar = volumeService.findByClient(clientId,"akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request) {

        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        int size = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));

        Result<Page<Volume>> ar = volumeService.findAll(page,size,"akipkoech");
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

        Result<List<Volume>> ar = volumeService.findByRowId(rowId, "Akipkoech");
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }


}
