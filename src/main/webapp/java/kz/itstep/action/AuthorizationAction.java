package kz.itstep.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.AppConstant.*;

public class AuthorizationAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(request, response);
//        response.sendRedirect("/fs/login");
    }
}
