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
import java.util.List;

import static kz.itstep.AppConstant.*;

public class CourceAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courceId = request.getParameter("courceId");
        CourceDao courceDao = new CourceDao();
        Cource cource = courceDao.findById(Integer.parseInt(courceId));

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");

        PurchasedCourceDao purchasedCourceDao = new PurchasedCourceDao();
        List<PurchasedCource> purchasedCourceList = purchasedCourceDao.findByUserId(user.getId());
        PurchasedCource purchasedCource = new PurchasedCource(user.getId(), cource.getId());
        if(purchasedCourceList != null){
            boolean contains = false;
            for (int i = 0; i < purchasedCourceList.size(); i++) {
                if(purchasedCource.getCourceId() == purchasedCourceList.get(i).getCourceId() && purchasedCource.getUserId() == purchasedCourceList.get(i).getUserId()){
                    contains = true;
                }
            }
            request.setAttribute(IS_PURCHASED, contains);
        }

        request.setAttribute(COURCE, cource);
        request.getRequestDispatcher(COURCE_PAGE).forward(request, response);
    }
}
