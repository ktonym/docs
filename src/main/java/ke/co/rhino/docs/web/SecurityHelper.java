package ke.co.rhino.docs.web;

import ke.co.rhino.docs.entity.Role;
import ke.co.rhino.docs.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthony.kipkoech on 12/31/2016.
 */
public class SecurityHelper {

    static final String SESSION_ATTRIB_USER = "userSession";
    static final String SESSION_ATTRIB_ROLES = "Role";

    public static User getSessionUser(HttpServletRequest request){
        User user = null;
        HttpSession session = request.getSession(true);
        Object obj = session.getAttribute(SESSION_ATTRIB_USER);
        if(obj != null && obj instanceof User){
            user = (User) obj;
        }
        return user;
    }

    public static List<Role> getSessionRoles(HttpServletRequest request){
        List<Role> roles = new ArrayList<>();
        HttpSession session = request.getSession(true);
        Object obj = session.getAttribute(SESSION_ATTRIB_ROLES);
        if(obj != null && obj instanceof List){
            roles = (List<Role>) obj;
        }
        return roles;
    }

}
