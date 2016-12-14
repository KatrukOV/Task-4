package com.katruk.dao.mysql;

import com.katruk.dao.DisciplineDAO;
import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.sql.statment.DisciplinePrepareStatement;
import com.katruk.dao.sql.Table;
import com.katruk.dao.utils.ConnectionPool;
import com.katruk.domain.Message;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Human;
import com.katruk.domain.entity.Teacher;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineMySql
    implements DisciplineDAO, DisciplinePrepareStatement, Table {
  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

  private static final Logger LOGGER = Logger.getLogger(DisciplineMySql.class);

  private static final Message MESSAGE = Message.getInstance();

  public DisciplineMySql() {

  }

  @Override
  public List<Discipline> getAll() throws DaoException {
    List<Discipline> resultList = new ArrayList<>();
    Message message = Message.getInstance();

    TeacherMySql teacherDAO = new TeacherMySql();
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_ALL);
         ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        Discipline discipline = new Discipline();
        discipline.setId(resultSet.getInt(ID));
        discipline.setTitle(resultSet.getString(TITLE));
        discipline.setTeacher((Teacher) teacherDAO.get(resultSet.getInt(TEACHER_ID)));
        resultList.add(discipline);
      }
      return resultList;
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_ALL_DISCIPLINE), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_GET_ALL_DISCIPLINE), e);
    }
  }

  @Override
  public Discipline get(String title) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_TITLE)) {
      statement.setString(1, title);
      return getBy(statement);
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_DISCIPLINE_BY_TITLE), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_GET_DISCIPLINE_BY_TITLE), e);
    }
  }

  @Override
  public Discipline get(int id) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
      statement.setInt(1, id);
      return getBy(statement);
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_DISCIPLINE_BY_ID), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_GET_DISCIPLINE_BY_ID), e);
    }
  }

  @Override
  public void create(Discipline discipline) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(CREATE)) {
      statement.setString(1, discipline.getTitle());
      statement.setInt(2, discipline.getTeacher().getId());
      statement.execute();
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_CREATE_DISCIPLINE), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_CREATE_DISCIPLINE), e);
    }
  }

  @Override
  public void remove(Discipline discipline) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(REMOVE)) {
      statement.setInt(1, discipline.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_REMOVE_DISCIPLINE), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_REMOVE_DISCIPLINE), e);
    }
  }

  @Override
  public void update(Discipline discipline) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE)) {
      statement.setInt(1, discipline.getId());
      statement.setString(2, discipline.getTitle());
      statement.setInt(3, discipline.getTeacher().getId());
      statement.setInt(4, discipline.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_UPDATE_DISCIPLINE), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_UPDATE_DISCIPLINE), e);
    }
  }

  @Override
  public List<Discipline> getAllDisciplinesOfTeacher(Human teacher) throws DaoException {
    List<Discipline> resultList = new ArrayList<>();

    try (
        Connection connection = CONNECTION_POOL.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_DISCIPLINE_TEACHER)) {
      statement.setString(1, teacher.getLogin());
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        resultList.add(get(resultSet.getInt(ID)));
      }
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_DISCIPLINE_OF_TEACHER), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_GET_DISCIPLINE_OF_TEACHER), e);
    }
    return resultList;
  }

  private Discipline getBy(PreparedStatement statement) throws DaoException {
    TeacherMySql teacherDAO = new TeacherMySql();
    try (ResultSet resultSet = statement.executeQuery()) {
      Discipline discipline = new Discipline();
      if (resultSet.next()) {
        discipline.setId(resultSet.getInt(ID));
        discipline.setTitle(resultSet.getString(TITLE));
        discipline.setTeacher((Teacher) teacherDAO.get(resultSet.getInt(TEACHER_ID)));
        return discipline;
      } else {
        // TODO: MESSAGE
        throw new DaoException("resultSet get nothing");
      }
    } catch (SQLException e) {
      LOGGER.error("Can't get Discipline by statement", e);
      throw new DaoException("Can't get Discipline by statement", e);
    }
  }
}

