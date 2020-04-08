package kz.itstep.dao;

import kz.itstep.ConnectionPool;
import kz.itstep.entity.User;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    private Logger logger = Logger.getLogger(CourceDao.class);

    private static final String SQL_SELECT_USERS_ALL = "SELECT * FROM public.users";
    private static final String SQL_INSERT_USER = "insert into public.users (login, name, password, roleId, surname) values(?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM public.users where id=?";
    private static final String SQL_UPDATE_USER = "UPDATE public.users set login=?, name=?, password=?, roleId=?, surname=? where id=?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM public.users where id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM public.users where login=? and password=?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM public.users where login=?";

    public User findByLoginAndPassword(String login, String password){
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    public User findByLogin(String login){
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public boolean delete(User entity) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    @Override
    public User findById(int id) {
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public boolean update(User entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setInt(4, entity.getRole());
            preparedStatement.setString(5, entity.getSurname());
            preparedStatement.setInt(6, entity.getId());

            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    @Override
    public boolean insert(User entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getSurname());
            preparedStatement.setInt(5,entity.getRole());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_USERS_ALL)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role"));
                user.setSurname(resultSet.getString("surname"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return users;
    }

    private User setUserParameters(ResultSet resultSet){
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getInt("role"));
            user.setSurname(resultSet.getString("surname"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return user;
    }

    public static byte [] ImageToByte(File file) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }
}
