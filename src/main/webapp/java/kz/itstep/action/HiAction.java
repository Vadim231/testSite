package kz.itstep.action;

import kz.itstep.dao.CourceDao;
import kz.itstep.dao.LanguageDao;
import kz.itstep.entity.Cource;
import kz.itstep.entity.Language;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static kz.itstep.AppConstant.*;


public class HiAction implements Action{

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourceDao courceDao = new CourceDao();
        LanguageDao languageDao = new LanguageDao();

        List<Language> languages = languageDao.findAll();
        List<Cource> cources = courceDao.findAll();

        String pricing_type_eq = null;
        String language_eq = null;

        try{
            pricing_type_eq = request.getParameter("pricing_type_eq");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            language_eq = request.getParameter("language_eq");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(pricing_type_eq != null){
            cources = courceDao.findByPricingType(pricing_type_eq);
            request.setAttribute(PRICING_TYPE, pricing_type_eq);
        } else if(language_eq != null){
            cources = courceDao.findByLanguage(language_eq);
            request.setAttribute(LANGUAGE, language_eq);
        }

        request.setAttribute(COURCES, cources);
        request.setAttribute(LANGUAGES, languages);
        request.getRequestDispatcher(URL_HI_PAGE).forward(request, response);
    }
}
