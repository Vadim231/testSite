package kz.itstep.action;

import kz.itstep.dao.CourceDao;
import kz.itstep.dao.PurchasedCourceDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courceIdStr = request.getParameter("courceId");
        int courceId = Integer.valueOf(courceIdStr);

        CourceDao courceDao = new CourceDao();
        courceDao.delete(courceId);



        response.sendRedirect("/fs/cources");
    }
}
