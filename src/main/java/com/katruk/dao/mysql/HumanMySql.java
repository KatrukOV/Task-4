package com.katruk.dao.mysql;

import com.katruk.dao.HumanDAO;
import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.sql.Table;
import com.katruk.dao.sql.statment.HumanPrepareStatement;
import com.katruk.dao.utils.ConnectionPool;
import com.katruk.domain.entity.Human;
import com.katruk.domain.logic.HumanFactory;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HumanMySql implements HumanDAO, HumanPrepareStatement, Table {

  private final String ERROR_GET_ALL_HUMAN = "Can't get all Human";
  private final String ERROR_GET_HUMAN_BY_LOGIN = "Can't get Human by login";
  private final String ERROR_GET_HUMAN_BY_ID = "Can't get Human by id";
  private final String ERROR_CREATE_HUMAN = "Can't create Human";
  private final String ERROR_REMOVE_HUMAN = "Can't remove Human";
  private final String ERROR_UPDATE_HUMAN = "Can't update Human";
  private final String ERROR_GET_HUMAN_BY_STATEMENT = "Can't get Human by statement";
  private final String ERROR_SET_ROLE_FOR_HUMAN = "Can't set role for Human";
  private final String ERROR_GET_HUMAN_BY_ROLE = "Can't get all Human by role";


  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
  private static final Logger LOGGER = Logger.getLogger(HumanMySql.class);

  public HumanMySql() {

  }

  @Override
  public List<Human> getAll() throws DaoException {
    List<Human> resultList = new ArrayList<Human>();
    try (
        Connection connection = CONNECTION_POOL.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL);
        ResultSet resultSet = statement.executeQuery()) {
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
      LOGGER.error(ERROR_GET_ALL_HUMAN, e);
      throw new DaoException(ERROR_GET_ALL_HUMAN, e);
    }
  }

  @Override
  public Human get(String login) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN)) {
      statement.setString(1, login);
      return getBy(statement);
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_HUMAN_BY_LOGIN, e);
      throw new DaoException(ERROR_GET_HUMAN_BY_LOGIN, e);
    }
  }

  @Override
  public Human get(int id) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
      statement.setInt(1, id);
      return getBy(statement);
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_HUMAN_BY_ID, e);
      throw new DaoException(ERROR_GET_HUMAN_BY_ID, e);
    }
  }

  @Override
  public void create(Human value) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(CREATE)) {
      statement.setString(1, value.getLogin());
      statement.setString(2, value.getPassword());
      statement.setString(3, value.getName());
      statement.setString(4, value.getLastName());
      statement.setString(5, value.getPatronymic());
      statement.setString(6, value.getRole().name());
      statement.execute();
    } catch (SQLException e) {
      LOGGER.error(ERROR_CREATE_HUMAN, e);
      throw new DaoException(ERROR_CREATE_HUMAN, e);
    }
  }

  @Override
  public void remove(Human value) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(REMOVE)) {
      statement.setInt(1, value.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error(ERROR_REMOVE_HUMAN, e);
      throw new DaoException(ERROR_REMOVE_HUMAN, e);
    }
  }

  @Override
  public void update(Human value) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE)) {
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
      LOGGER.error(ERROR_UPDATE_HUMAN, e);
      throw new DaoException(ERROR_UPDATE_HUMAN, e);
    }
  }

  private Human getBy(PreparedStatement statement) throws DaoException {
    Human human = null;
    try (ResultSet resultSet = statement.executeQuery()) {
      if (resultSet.next()) {
        Human.Role role = Human.Role.valueOf(resultSet.getString(ROLE));
        human = HumanFactory.getInstance().create(role);
        human.setId(resultSet.getInt(ID));
        human.setLogin(resultSet.getString(LOGIN));
        human.setPassword(resultSet.getString(PASSWORD));
        human.setName(resultSet.getString(NAME));
        human.setLastName(resultSet.getString(LAST_NAME));
        human.setPatronymic(resultSet.getString(PATRONYMIC));
        human.setRole(role);
      }
      return human;
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_HUMAN_BY_STATEMENT, e);
      throw new DaoException(ERROR_GET_HUMAN_BY_STATEMENT, e);
    }
  }

  @Override
  public void setRole(Human human, Human.Role role) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(SET_ROLE)) {
      statement.setString(1, role.name());
      statement.setString(2, human.getLogin());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error(ERROR_SET_ROLE_FOR_HUMAN, e);
      throw new DaoException(ERROR_SET_ROLE_FOR_HUMAN, e);
    }
  }

  @Override
  public List<Human> getAllByRole(Human.Role role) throws DaoException {
    List<Human> resultList = new ArrayList<>();
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_ROLE)) {
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
      LOGGER.error(ERROR_GET_HUMAN_BY_ROLE, e);
      throw new DaoException(ERROR_GET_HUMAN_BY_ROLE, e);
    }
  }
}
