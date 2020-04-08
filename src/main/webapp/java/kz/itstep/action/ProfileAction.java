package kz.itstep.action;

import kz.itstep.dao.CourceDao;
import kz.itstep.dao.PurchasedCourceDao;
import kz.itstep.entity.Cource;
import kz.itstep.entity.PurchasedCource;
import kz.itstep.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static kz.itstep.AppConstant.*;

public class ProfileAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        PurchasedCourceDao purchasedCourceDao = new PurchasedCourceDao();
        CourceDao courceDao = new CourceDao();

        List<PurchasedCource> purchasedCources = purchasedCourceDao.findByUserId(user.getId());
        List<Cource> cources = new ArrayList<>();

        for (int i = 0; i < purchasedCources.size(); i++) {
            cources.add(courceDao.findById(purchasedCources.get(i).getCourceId()));
        }

        request.setAttribute(USER_COURCES, cources);
        request.getRequestDispatcher(URL_PROFILE_PAGE).forward(request, response);
    }
}
