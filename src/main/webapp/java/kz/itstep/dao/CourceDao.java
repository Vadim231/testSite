package kz.itstep.dao;
import kz.itstep.entity.Cource;
import kz.itstep.entity.Language;
import kz.itstep.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourceDao extends AbstractDao<Cource> {
    private Logger logger = Logger.getLogger(CourceDao.class);

    private static final String SQL_SELECT_ALL = "select * from public.cources";
    private static final String SQL_SELECT_BY_ID = "select * from public.cources where id=?";
    private static final String SQL_SELECT_BY_LANG = "select * from public.cources where language=?";
    private static final String SQL_SELECT_BY_FREE = "select * from public.cources where price=0";
    private static final String SQL_SELECT_BY_PAID = "select * from public.cources where price!=0";
    private static final String INSERT = "insert into public.cources (name) values(?)";
    private static final String DELETE_ID = "UPDATE public.cources set deleted=true where id=?";
    @Override
    public boolean delete(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ID)) {
            preparedStatement.setInt(1, id);
            Cource cource = new Cource();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            }
            return true;
        } catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }

    }

    @Override
    public boolean delete(Cource cource) {
        return false;
    }

    @Override
    public boolean insert(Cource cource) {
        return false;
    }

    @Override
    public boolean update(Cource cource) {
        return false;
    }

    @Override
    public List<Cource> findAll() {
        List<Cource> cources = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)){
            while (resultSet.next()){
                Cource cource = new Cource();
                cource.setId(resultSet.getInt("id"));
                cource.setDescription(resultSet.getString("description"));
                cource.setPrice(resultSet.getInt("price"));
                cource.setTitle(resultSet.getString("title"));
                cource.setDuration(resultSet.getInt("duration"));
                cource.setHtmlBlock(resultSet.getString("html_block"));
                cource.setDeleted(resultSet.getBoolean("deleted"));
                LanguageDao languageDao = new LanguageDao();
                Language language = languageDao.findById(resultSet.getInt("language"));

                cource.setLanguage(language);
                cources.add(cource);
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return cources;
    }

    @Override
    public Cource findById(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            Cource cource = new Cource();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cource = setCourceParameters(resultSet);
                }
            }
            return cource;
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }

    public List<Cource> findByLanguage(String language) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        List<Cource> cources = new ArrayList<>();
        LanguageDao languageDao = new LanguageDao();
        int languageId = languageDao.findByName(language).getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LANG)) {
            preparedStatement.setInt(1, languageId);
            Cource cource = new Cource();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cource = setCourceParameters(resultSet);
                    cources.add(cource);
                }
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
            return cources;
        }
    }

    public List<Cource> findByPricingType(String pricingType) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        List<Cource> cources = new ArrayList<>();
        String request = "";
        if(pricingType.equals("free")){
            request = SQL_SELECT_BY_FREE;
        } else if(pricingType.equals("paid")){
            request = SQL_SELECT_BY_PAID;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            Cource cource = new Cource();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cource = setCourceParameters(resultSet);
                    cources.add(cource);
                }
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
            return cources;
        }
    }

    private Cource setCourceParameters(ResultSet resultSet){
        Cource cource = new Cource();
        try {
            cource.setId(resultSet.getInt("id"));
            cource.setDescription(resultSet.getString("description"));
            cource.setPrice(resultSet.getInt("price"));
            cource.setTitle(resultSet.getString("title"));
            cource.setDuration(resultSet.getInt("duration"));
            cource.setHtmlBlock(resultSet.getString("html_block"));
            LanguageDao languageDao = new LanguageDao();
            Language language = languageDao.findById(resultSet.getInt("language"));
            cource.setDeleted(resultSet.getBoolean("deleted"));
            cource.setLanguage(language);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return cource;
    }
}