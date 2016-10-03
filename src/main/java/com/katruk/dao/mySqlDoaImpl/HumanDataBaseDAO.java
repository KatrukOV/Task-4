package com.katruk.dao.mySqlDoaImpl;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.interfase.HumanDAO;
import com.katruk.dao.sql.statment.HumanPrepareStatement;
import com.katruk.dao.sql.table.HumanTable;
import com.katruk.dao.utils.ConnectionPool;
import com.katruk.domen.entity.human.Human;
import com.katruk.domen.logic.HumanFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HumanDataBaseDAO implements HumanDAO, HumanPrepareStatement, HumanTable {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(HumanDataBaseDAO.class);

    public HumanDataBaseDAO() {
        System.out.println("human dao");
    }

    @Override
    public List<Human> getAll() throws DaoException {
        List<Human> resultList = new ArrayList<Human>();
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                Human.Role role = Human.Role.valueOf(resultSet.getString(ROLE));
                Human human = HumanFactory.getInstance().create(role);
                human.setId(resultSet.getInt(ID));
                human.setLogin(resultSet.getString(LOGIN));
                human.setPassword(resultSet.getString(PASSWORD));
                human.setName(resultSet.getString(NAME));
                human.setLastName(resultSet.getString(LAST_NAME));
                human.setPatronymic(resultSet.getString(PATRONYMIC));
                human.setRole(role);
                resultList.add(human);
            }
            return resultList;
        } catch (SQLException e) {
            logger.error("Can't get all Human", e);
            throw new DaoException("Can't get all Human", e);
        }
    }

    @Override
    public Human get(String login) throws DaoException {
        System.out.println(">> try get human");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN);
        ) {
            statement.setString(1, login);
            System.out.println(">>>> stat=" + statement);
            return getBy(statement);
        } catch (SQLException e) {
            logger.error("Can't get Human by login", e);
            throw new DaoException("Can't get Human by login", e);
        }
    }

    @Override
    public Human get(int id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
        ) {
            statement.setInt(1, id);
            return getBy(statement);
        } catch (SQLException e) {
            logger.error("Can't get Human by id", e);
            throw new DaoException("Can't get Human by id", e);
        }
    }

    @Override
    public void create(Human value) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
//            System.out.println(">> get conn in create human");
             PreparedStatement statement = connection.prepareStatement(CREATE);
        ) {
            statement.setString(1, value.getLogin());
            statement.setString(2, value.getPassword());
            statement.setString(3, value.getName());
            statement.setString(4, value.getLastName());
            statement.setString(5, value.getPatronymic());
            statement.setString(6, value.getRole().name());
            System.out.println(">> stat=" + statement);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Can't create Human", e);
            throw new DaoException("Can't create Human", e);
        }
    }

    @Override
    public void remove(Human value) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE);
        ) {
            statement.setInt(1, value.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't remove Human", e);
            throw new DaoException("Can't remove Human", e);
        }
    }

    @Override
    public void update(Human value) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE);
        ) {
            statement.setInt(1, value.getId());
            statement.setString(2, value.getLogin());
            statement.setString(3, value.getPassword());
            statement.setString(4, value.getName());
            statement.setString(5, value.getLastName());
            statement.setString(6, value.getPatronymic());
            statement.setString(7, value.getRole().name());
            statement.setInt(8, value.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update Human", e);
            throw new DaoException("Can't update Human", e);
        }
    }

    private Human getBy(PreparedStatement statement) throws DaoException {
        Human human = null;
        try (ResultSet resultSet = statement.executeQuery();

        ) {
            System.out.println(">>>getBy stat=" + statement);

            if (resultSet.next()) {
//                System.out.println(">>>getBy rez=" + resultSet);
                Human.Role role = Human.Role.valueOf(resultSet.getString(ROLE));
//                System.out.println(">>> ---- role=" + resultSet.getString(ROLE));
//                System.out.println(">>> ---- id=" + resultSet.getInt(ID));
                human = HumanFactory.getInstance().create(role);
//				System.out.println(">>> ---- human="+ human.);
                human.setId(resultSet.getInt(ID));
//                System.out.println(">>> ++++ id=" + resultSet.getInt(ID));
                human.setLogin(resultSet.getString(LOGIN));
//                System.out.println(">>> ---- LOGIN=" + resultSet.getString(LOGIN));
                human.setPassword(resultSet.getString(PASSWORD));
                human.setName(resultSet.getString(NAME));
                human.setLastName(resultSet.getString(LAST_NAME));
                human.setPatronymic(resultSet.getString(PATRONYMIC));
                human.setRole(role);
            }
            System.out.println(">> get human=" + human);
            return human;
        } catch (SQLException e) {
            logger.error("Can't get Human by statement", e);
            throw new DaoException("Can't get Human by statement", e);
        }
    }

    @Override
    public void setRole(Human human, Human.Role role) throws DaoException {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_ROLE);
        ) {
            statement.setString(1, role.name());
            statement.setString(2, human.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't set role for Human", e);
            throw new DaoException("Can't set role for Human", e);
        }
    }

    @Override
    public List<Human> getAllByRole(Human.Role role) throws DaoException {
        List<Human> resultList = new ArrayList<Human>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_ROLE);
        ) {
            statement.setString(1, role.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Human human = HumanFactory.getInstance().create(role);
                human.setId(resultSet.getInt(ID));
                human.setLogin(resultSet.getString(LOGIN));
                human.setPassword(resultSet.getString(PASSWORD));
                human.setName(resultSet.getString(NAME));
                human.setLastName(resultSet.getString(LAST_NAME));
                human.setPatronymic(resultSet.getString(PATRONYMIC));
                human.setRole(role);
                resultList.add(human);
            }
            return resultList;
        } catch (SQLException e) {
            logger.error("Can't get all Human", e);
            throw new DaoException("Can't get all Human", e);
        }
    }
}
