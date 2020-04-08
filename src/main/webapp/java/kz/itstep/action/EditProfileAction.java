package kz.itstep.action;

import kz.itstep.dao.UserDao;
import kz.itstep.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

import static kz.itstep.AppConstant.*;
@MultipartConfig
public class EditProfileAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getMethod().equals("GET")){
            request.getRequestDispatcher(URL_PROFILE_EDIT_PAGE).forward(request, response);
        } else if(request.getMethod().equals("POST")){
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("currentUser");
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            int roleId = Integer.parseInt(request.getParameter("roleId"));
            String surname = request.getParameter("surname");



            user.setLogin(login);
            user.setName(name);
            user.setPassword(password);
            user.setRole(roleId);
            user.setSurname(surname);
            UserDao userDao = new UserDao();
            userDao.update(user);

            request.setAttribute(PROFILE_EDITED, true);
            request.getRequestDispatcher(URL_PROFILE_EDIT_PAGE).forward(request, response);
        }
    }
}
