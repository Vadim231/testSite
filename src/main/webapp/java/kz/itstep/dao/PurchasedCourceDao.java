package kz.itstep.dao;
import kz.itstep.entity.Cource;
import kz.itstep.entity.PurchasedCource;
import kz.itstep.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchasedCourceDao extends AbstractDao<PurchasedCource> {
    private Logger logger = Logger.getLogger(PurchasedCourceDao.class);

    private static final String SQL_SELECT_ALL = "select * from public.purchased_courses";
    private static final String SQL_SELECT_BY_ID = "select * from public.purchased_courses where id=?";
    private static final String SQL_SELECT_BY_USER_ID = "select * from public.purchased_courses where user_id=?";
    private static final String INSERT = "insert into public.purchased_courses (user_id, cource_id) values(?, ?)";
    private static final String DELETE_ID = "delete from public.purchased_courses where id=?";
    private static final String DELETE_COURCE_ID = "delete from public.purchased_courses where cource_id=?";
    private static final String DELETE_ROLE = "delete from public.purchased_courses where";
    @Override
    public boolean delete(int courceId) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURCE_ID)) {
            preparedStatement.setInt(1, courceId);
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
    public boolean delete(PurchasedCource cource) {
        return false;
    }

    @Override
    public boolean insert(PurchasedCource cource) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setInt(1,cource.getUserId());
            preparedStatement.setInt(2,cource.getCourceId());
            preparedStatement.executeQuery();
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean update(PurchasedCource cource) {
        return false;
    }

    @Override
    public List<PurchasedCource> findAll() {
        List<PurchasedCource> purchasedCources = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)){
            while (resultSet.next()){
                PurchasedCource cource = new PurchasedCource();
                cource.setUserId(resultSet.getInt("user_id"));
                cource.setCourceId(resultSet.getInt("cource_id"));
                purchasedCources.add(cource);
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return purchasedCources;
    }

    @Override
    public PurchasedCource findById(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            PurchasedCource cource = new PurchasedCource();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cource = setPurchasedCourceParameters(resultSet);
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

    public List<PurchasedCource> findByUserId(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        List<PurchasedCource> purchasedCources = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER_ID)) {
            preparedStatement.setInt(1, id);
            PurchasedCource cource = new PurchasedCource();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cource = setPurchasedCourceParameters(resultSet);
                    purchasedCources.add(cource);
                }
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return purchasedCources;
    }

    private PurchasedCource setPurchasedCourceParameters(ResultSet resultSet){
        PurchasedCource cource = new PurchasedCource();
        try {
            cource.setUserId(resultSet.getInt("user_id"));
            cource.setCourceId(resultSet.getInt("cource_id"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return cource;
    }
}
