package com.katruk.dao.mySqlDoaImpl;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.interfase.DisciplineDAO;
import com.katruk.dao.sql.statment.DisciplinePrepareStatement;
import com.katruk.dao.sql.table.DisciplineTable;
import com.katruk.dao.utils.ConnectionPool;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.human.Human;
import com.katruk.domain.entity.human.Teacher;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDataBaseDAO
    implements DisciplineDAO, DisciplineTable, DisciplinePrepareStatement {

  private final String ERROR_GET_ALL_DISCIPLINE  = "Can't get all Discipline";
  private final String ERROR_GET_DISCIPLINE_BY_TITLE  = "Can't get Discipline by title";
  private final String ERROR_GET_DISCIPLINE_BY_ID  = "Can't get Discipline by id";
  private final String ERROR_CREATE_DISCIPLINE  = "Can't create Discipline";
  private final String ERROR_REMOVE_DISCIPLINE  = "Can't remove Discipline";
  private final String ERROR_UPDATE_DISCIPLINE  = "Can't update Discipline";
  private final String ERROR_GET_DISCIPLINE_OF_TEACHER  = "Can't get Disciplines Of Teacher";

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

  private static final Logger LOGGER = Logger.getLogger(DisciplineDataBaseDAO.class);

  public DisciplineDataBaseDAO() {
  }

  @Override
  public List<Discipline> getAll() throws DaoException {
    List<Discipline> resultList = new ArrayList<>();
    TeacherDataBaseDAO teacherDAO = new TeacherDataBaseDAO();

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
      LOGGER.error(ERROR_GET_ALL_DISCIPLINE, e);
      throw new DaoException(ERROR_GET_ALL_DISCIPLINE, e);
    }
  }

  @Override
  public Discipline get(String title) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_TITLE)) {
      statement.setString(1, title);
      return getBy(statement);
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_DISCIPLINE_BY_TITLE, e);
      throw new DaoException(ERROR_GET_DISCIPLINE_BY_TITLE, e);
    }
  }

  @Override
  public Discipline get(int id) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
      statement.setInt(1, id);
      return getBy(statement);
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_DISCIPLINE_BY_ID, e);
      throw new DaoException(ERROR_GET_DISCIPLINE_BY_ID, e);
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
      LOGGER.error(ERROR_CREATE_DISCIPLINE, e);
      throw new DaoException(ERROR_CREATE_DISCIPLINE, e);
    }
  }

  @Override
  public void remove(Discipline discipline) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(REMOVE)) {
      statement.setInt(1, discipline.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error(ERROR_REMOVE_DISCIPLINE, e);
      throw new DaoException(ERROR_REMOVE_DISCIPLINE, e);
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
      LOGGER.error(ERROR_UPDATE_DISCIPLINE, e);
      throw new DaoException(ERROR_UPDATE_DISCIPLINE, e);
    }
  }

  @Override
  public List<Discipline> getAllDisciplinesOfTeacher(Human teacher) throws DaoException {
    List<Discipline> resultList = new ArrayList<Discipline>();

    try (
        Connection connection = CONNECTION_POOL.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_DISCIPLINE_TEACHER)) {
      statement.setString(1, teacher.getLogin());
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        resultList.add(get(resultSet.getInt(ID)));
      }
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_DISCIPLINE_OF_TEACHER, e);
      throw new DaoException(ERROR_GET_DISCIPLINE_OF_TEACHER, e);
    }
    return resultList;
  }

  private Discipline getBy(PreparedStatement statement) throws DaoException {
    TeacherDataBaseDAO teacherDAO = new TeacherDataBaseDAO();
    try (ResultSet resultSet = statement.executeQuery()) {
      Discipline discipline = new Discipline();
      if (resultSet.next()) {
        discipline.setId(resultSet.getInt(ID));
        discipline.setTitle(resultSet.getString(TITLE));
        discipline.setTeacher((Teacher) teacherDAO.get(resultSet.getInt(TEACHER_ID)));
        return discipline;
      } else {
        throw new DaoException("resultSet get nothing");
      }
    } catch (SQLException e) {
      LOGGER.error("Can't get Discipline by statement", e);
      throw new DaoException("Can't get Discipline by statement", e);
    }
  }
}

