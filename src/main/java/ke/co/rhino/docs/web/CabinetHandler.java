package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.*;
import ke.co.rhino.docs.service.ICabinetRowService;
import ke.co.rhino.docs.service.ICabinetService;
import ke.co.rhino.docs.service.IClientService;
import ke.co.rhino.docs.vo.Result;
import ke.co.rhino.docs.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by akipkoech on 22/07/2016.
 */
@Controller
@RequestMapping("/cabinet")
public class CabinetHandler extends AbstractHandler {

    @Autowired
    private ICabinetService service;

    @Autowired
    private ICabinetRowService cabinetRowService;

    @Autowired
    private IClientService clientService;

    /*@RequestMapping(value = "/tree", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String getTree(){
        String actionUsername = "akipkoech";

        Result<List<Cabinet>> ar = service.findAll();

        if(ar.isSuccess()){

            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("success",true);
            JsonArrayBuilder cabArrBuilder = Json.createArrayBuilder();

            for (Cabinet cab : ar.getData()) {

                List<CabinetRow> cabinetRows = cabinetRowService.findByCabinet(cab).getData();
                JsonArrayBuilder rowsArrBuilder = Json.createArrayBuilder();

                if(!cabinetRows.isEmpty()){

                    for (CabinetRow row : cabinetRows) {

                        List<Client> clients = clientService.findByRow(row).getData();
                        JsonArrayBuilder clientArrBuilder = Json.createArrayBuilder();

                        if (!clients.isEmpty()) {

                            for (Client client : clients) {
                                clientArrBuilder.add(
                                        Json.createObjectBuilder()
                                                .add("id", getTreeNodeId(client))
                                                .add("text", client.getClientName())
                                                .add("cabinetId", cab.getId())
                                                .add("rowNum", row.getRowNumber())
                                                .add("leaf", true)
                                );
                            }

                        }

                        rowsArrBuilder.add(
                                Json.createObjectBuilder()
                                        .add("id", getTreeNodeId(row))
                                        .add("text", "Row ".concat(row.getRowNumber().toString()))
                                        .add("leaf", clients.isEmpty())
                                        .add("expanded", clients.size() > 0)
                                        .add("cabinetId", cab.getId())
                                        .add("children", clientArrBuilder)
                        );

                    }
                }
                   cabArrBuilder.add(
                           Json.createObjectBuilder()
                                    .add("id", getTreeNodeId(cab))
                                    .add("text", "Shelf ".concat(cab.getId().toString()))
                                    .add("leaf", cabinetRows.isEmpty())
                                    .add("expanded", cabinetRows.size()>0)
                                    .add("children", rowsArrBuilder)
                   );

                //}

            }

            builder.add("children", cabArrBuilder);

            return toJsonString(builder.build());

        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    private String getTreeNodeId(EntityItem obj){

        String id = null;

        if(obj instanceof Cabinet){
            id = "S_" + obj.getId();
        }else if (obj instanceof CabinetRow){
//            StringBuilder builder = new StringBuilder("R_").append(((CabinetRow) obj).getCabinet().getCabinetId())
//                    .append(":").append(((CabinetRow) obj).getRowNumber().toString()); // + obj.getId();
//            id = builder.toString();
            id = "R_" + obj.getId();
        }else if (obj instanceof Client){
            id = "C_" + obj.getId();
        }

        return id;
    }*/

   /* @RequestMapping(value="/treenode", method=RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String getCompanyTreeNode(
            @RequestParam(value = "node", required = true) String node,
            HttpServletRequest request) {

        //User sessionUser = getSessionUser(request);

        logger.info(node);

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", true);
        JsonArrayBuilder childrenArrayBuilder = Json.createArrayBuilder();

        if (node.equals("root")) {

            Result<List<Cabinet>> ar = service.findAll();

            if(ar.isSuccess()){

                for (Cabinet cab: ar.getData()) {

                    List<CabinetRow> rows = cabinetRowService.findByCabinet(cab).getData();

                    childrenArrayBuilder.add(
                            Json.createObjectBuilder()
                                .add("id", getTreeNodeId(cab))
                                .add("text", "Shelf ".concat(cab.getId().toString()))
                                .add("leaf", rows.isEmpty())
                    );

                }

            } else {
                return getJsonErrorMsg(ar.getMsg());
            }

        } else if(node.startsWith("S")){
            String[] idSplit = node.split("_");
            Long cabinetId = Long.parseLong(idSplit[1]);
            Result<Cabinet> ar = service.find(cabinetId);

            List<CabinetRow> rows = cabinetRowService.findByCabinet(ar.getData()).getData();

            for (CabinetRow row: rows) {

                List<Client> clients = clientService.findByRow(row).getData();

                childrenArrayBuilder.add(
                        Json.createObjectBuilder()
                                .add("id", getTreeNodeId(row))
                                .add("text", "Row ".concat(row.getId().toString()))
                                .add("leaf", clients.isEmpty())
                );
            }

        } else if(node.startsWith("R")){
            String[] idSplit = node.split("_");
            //String[] rowIdSplit = idSplit[1].split(":");
            //Long cabinetId = Long.valueOf(rowIdSplit[0].toString());
            //Long rowNum = Long.valueOf(rowIdSplit[1].toString());
            //Cabinet cab = service.find(cabinetId).getData();
            Long rowId = Long.parseLong(idSplit[1]);
            CabinetRow row = cabinetRowService.find(rowId).getData();
            List<Client> clients = clientService.findByRow(row).getData();

            for (Client client: clients){

                childrenArrayBuilder.add(
                        Json.createObjectBuilder()
                                .add("id", getTreeNodeId(client))
                                .add("text", client.getClientName())
                                .add("leaf", true)
                );

            }

            //Result<CabinetRow> ar = cabinetRowService.find(rowId);
        }

        builder.add("children", childrenArrayBuilder);

        return toJsonString(builder.build());

    }*/

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String create(@RequestParam(value = "data")String jsonData,HttpServletRequest request){

        JsonObject jsonObject = parseJsonObject(jsonData);

        Optional<Long> cabinetIdOpt = Optional.empty();

        CabinetType cabinetType = CabinetType.valueOf(jsonObject.getString("cabinetType"));
//        Integer shelfNumber = getIntegerValue(jsonObject.get("shelfNumber"));
        String actionUsername = "akipkoech";

        Result<Cabinet> ar = service.store(cabinetIdOpt,cabinetType,/*shelfNumber,*/actionUsername);
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

        Long cabinetId;
        Optional<Long> cabinetIdOpt;

        cabinetId = ((JsonNumber) jsonObject.get("id")).longValue();
        cabinetIdOpt = Optional.of(cabinetId);


//        Optional<Long> cabinetIdOpt = Optional.of(((JsonNumber) jsonObject.get("cabinetId")).longValue());
        CabinetType cabinetType = CabinetType.valueOf(jsonObject.getString("cabinetType"));
//        Integer shelfNumber = getIntegerValue(jsonObject.get("shelfNumber"));
        String actionUsername = "akipkoech";

        Result<Cabinet> ar = service.store(cabinetIdOpt,cabinetType,/*shelfNumber,*/actionUsername);
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

        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        int size = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));
        String actionUsername = "akipkoech";
        Result<Page<Cabinet>> ar = service.findAll(page,size,actionUsername);
        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }


}
