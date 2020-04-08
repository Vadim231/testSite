package kz.itstep.dao;
import kz.itstep.entity.Language;
import kz.itstep.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageDao extends AbstractDao<Language> {
    private Logger logger = Logger.getLogger(CourceDao.class);

    private static final String SQL_SELECT_ALL = "select * from public.languages";
    private static final String SQL_SELECT_BY_ID = "select * from public.languages where id=?";
    private static final String SQL_SELECT_BY_NAME = "select * from public.languages where name=?";
    private static final String INSERT = "insert into public.languages (name, img_url) values(?, ?)";
    private static final String DELETE_ID = "delete from public.languages where id=";
    private static final String DELETE_LANGUAGE = "delete from public.languages where";
    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Language language) {
        return false;
    }

    @Override
    public boolean insert(Language language) {
        return false;
    }

    @Override
    public boolean update(Language language) {
        return false;
    }

    @Override
    public List<Language> findAll() {
        List<Language> languages = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)){
            while (resultSet.next()){
                Language language = new Language();
                language.setId(resultSet.getInt("id"));
                language.setName(resultSet.getString("name"));
                language.setImgUrl(resultSet.getString("img_url"));
                languages.add(language);
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return languages;
    }

    @Override
    public Language findById(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            Language language = new Language();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    language = setLanguageParameters(resultSet);
                }
            }
            return language;
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }

    public Language findByName(String name) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME)) {
            preparedStatement.setString(1, name);
            Language language = new Language();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    language = setLanguageParameters(resultSet);
                }
            }
            return language;
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }

    private Language setLanguageParameters(ResultSet resultSet){
        Language cource = new Language();
        try {
            cource.setId(resultSet.getInt("id"));
            cource.setName(resultSet.getString("name"));
            cource.setImgUrl(resultSet.getString("img_url"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return cource;
    }
}
