package kz.itstep.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.itstep.AppConstant.*;


public class ActionFactory {
    private static final Map<String, Action> PAGES = new HashMap<>();

    public ActionFactory() {
        init();
    }

    private void init(){
        PAGES.put("GET/info", new DefaultAction(URL_INFO_PAGE));
        PAGES.put("GET/cources", new HiAction());
        PAGES.put("GET/login", new AuthorizationAction());
        PAGES.put("GET/", new AuthorizationAction());
        PAGES.put("GET/registration", new RegistrationAction());
        PAGES.put("GET/cource", new CourceAction());
        PAGES.put("GET/admin", new DefaultAction(URL_ADMIN_PAGE));
        PAGES.put("GET/profile", new ProfileAction());
        PAGES.put("GET/profile/edit", new EditProfileAction());
        PAGES.put("GET/logout", new LogoutAction());

        PAGES.put("POST/profile/edit", new EditProfileAction());
        PAGES.put("POST/delete", new DeleteAction());
        PAGES.put("POST/buy", new BuyAction());
        PAGES.put("POST/registration", new RegistrationAction());
        PAGES.put("POST/authorization", new LoginAction());

    }

    public Action getAction(HttpServletRequest request, HttpServletResponse response){
        return PAGES.get(request.getMethod() + request.getPathInfo());
    }
}
