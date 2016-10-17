package com.katruk.dao.mySqlDoaImpl;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.interfase.StudentDAO;
import com.katruk.dao.sql.statment.StudentPrepareStatement;
import com.katruk.dao.sql.table.StudentTable;
import com.katruk.dao.utils.ConnectionPool;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.human.Human;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDataBaseDAO extends HumanDataBaseDAO
    implements StudentDAO, StudentPrepareStatement, StudentTable {

  private final String ERROR_GET_STUDENT_BY_DISCIPLINE = "Can't get students by discipline";
  private final String ERROR_SET_CONTRACT_FOR_STUDENT = "Can't set Contract for Student";
  private final String ERROR_CREATE_CONTRACT_FOR_STUDENT = "Can't create Contract for Student";
  private final String CREATE_CONTRACT_FOR_STUDENT = "Create new row Contract for Student";
  private final String ERROR_GET_CONTRACT_FOR_STUDENT = "Can't get Contract for Student";
  private final String HAVE_NOT_STUDENT = "Have not Student";
  private final String EMPTY_RESULTSET = "resultSet get nothing";


  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
  private static final Logger LOGGER = Logger.getLogger(StudentDataBaseDAO.class);

  public StudentDataBaseDAO() {
  }

  @Override
  public List<Human> getStudentsByDiscipline(Discipline discipline) throws DaoException {

    List<Human> resultList = new ArrayList<>();
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection
             .prepareStatement(GET_ALL_STUDENT_FOR_DISCIPLINE)) {
      statement.setInt(1, discipline.getId());
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        // TODO:  1 -> name
        resultList.add(get(resultSet.getInt(1)));
        //             resultList.add(get(resultSet.getInt(STUDENT_ID)));
        //             resultList.add(get(resultSet.getInt("student_human_id")));
      }
      return resultList;
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_STUDENT_BY_DISCIPLINE, e);
      throw new DaoException(ERROR_GET_STUDENT_BY_DISCIPLINE, e);
    }
  }

  @Override
  public void setContractForStudent(Human student, boolean contract) throws DaoException {
    if (isContractForStudent(student)) {
      try (Connection connection = CONNECTION_POOL.getConnection();
           PreparedStatement statement = connection.prepareStatement(SET_CONTRACT_FOR_STUDENT)) {
        statement.setBoolean(1, contract);
        statement.setString(2, student.getLogin());
        statement.executeUpdate();
      } catch (SQLException e) {
        LOGGER.error(ERROR_SET_CONTRACT_FOR_STUDENT, e);
        throw new DaoException(ERROR_SET_CONTRACT_FOR_STUDENT, e);
      }
    } else {
      LOGGER.info(CREATE_CONTRACT_FOR_STUDENT);
      try (Connection connection = CONNECTION_POOL.getConnection();
           PreparedStatement statement = connection.prepareStatement(INSERT_CONTRACT_FOR_STUDENT)) {
        statement.setBoolean(1, contract);
        statement.setString(2, student.getLogin());
        statement.execute();
      } catch (SQLException e) {
        LOGGER.error(ERROR_CREATE_CONTRACT_FOR_STUDENT, e);
        throw new DaoException(ERROR_CREATE_CONTRACT_FOR_STUDENT, e);
      }
    }
  }

  private boolean isContractForStudent(Human student) throws DaoException {
    boolean result = false;
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(IS_CONTRACT_FOR_STUDENT)) {
      statement.setString(1, student.getLogin());
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      LOGGER.error(HAVE_NOT_STUDENT, e);
      throw new DaoException(HAVE_NOT_STUDENT, e);
    }
    return result;
  }

  @Override
  public boolean getContractForStudent(Human student) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_CONTRACT_OF_STUDENT)) {
      statement.setInt(1, student.getId());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getBoolean(CONTRACT);
      } else {
        throw new DaoException(EMPTY_RESULTSET);
      }
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_CONTRACT_FOR_STUDENT, e);
      throw new DaoException(ERROR_GET_CONTRACT_FOR_STUDENT, e);
    }
  }
}
