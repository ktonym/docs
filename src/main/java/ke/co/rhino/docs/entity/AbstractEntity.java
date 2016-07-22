package ke.co.rhino.docs.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * Created by user on 7/21/2016.
 */
public abstract class AbstractEntity implements JsonItem,Serializable{

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

    @Override
    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addJson(builder);
        return builder.build();
    }
}
