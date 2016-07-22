package ke.co.rhino.docs.entity;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by user on 7/21/2016.
 */
public interface JsonItem {

    public JsonObject toJson();
    public void addJson(JsonObjectBuilder builder);

}
