package com.katruk.dao.mySqlDoa;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.TeacherDAO;
import com.katruk.dao.sql.statment.TeacherPrepareStatement;
import com.katruk.dao.sql.table.TeacherTable;
import com.katruk.dao.utils.ConnectionPool;
import com.katruk.domain.entity.human.Human;
import com.katruk.domain.entity.human.Teacher;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMySql extends HumanMySql
    implements TeacherDAO, TeacherPrepareStatement, TeacherTable {

  private final String ERROR_SET_POSITION_FOR_TEACHER = "Can't set position for Teacher";
  private final String ERROR_CREATE_POSITION_FOR_TEACHER = "Can't create position for Teacher";
  private final String CREATE_POSITION_FOR_TEACHER = "Create new row position for Teacher";
  private final String ERROR_GET_POSITION_FOR_TEACHER = "Can't get position for Teacher";
  private final String HAVE_NOT_TEACHER = "Have not Teacher";
  private final String EMPTY_RESULTSET = "resultSet get nothing";


  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
  private static final Logger LOGGER = Logger.getLogger(TeacherMySql.class);

  public TeacherMySql() {

  }

  @Override
  public void setPositionForTeacher(Human teacher, Teacher.Position position) throws DaoException {
    if (isPositionForTeacher(teacher)) {
      try (Connection connection = CONNECTION_POOL.getConnection();
           PreparedStatement statement = connection.prepareStatement(SET_POSITION_FOR_TEACHER)) {
        statement.setString(1, position.name());
        statement.setString(2, teacher.getLogin());
        statement.executeUpdate();
      } catch (SQLException e) {
        LOGGER.error(ERROR_SET_POSITION_FOR_TEACHER, e);
        throw new DaoException(ERROR_SET_POSITION_FOR_TEACHER, e);
      }
    } else {
      LOGGER.info(CREATE_POSITION_FOR_TEACHER);
      try (Connection connection = CONNECTION_POOL.getConnection();
           PreparedStatement statement = connection.prepareStatement(INSERT_POSITION_FOR_TEACHER)) {
        statement.setString(1, position.name());
        statement.setString(2, teacher.getLogin());
        statement.execute();
      } catch (SQLException e) {
        LOGGER.error(ERROR_CREATE_POSITION_FOR_TEACHER, e);
        throw new DaoException(ERROR_CREATE_POSITION_FOR_TEACHER, e);
      }
    }
  }

  private boolean isPositionForTeacher(Human teacher) throws DaoException {
    boolean result;
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(IS_POSITION_FOR_TEACHER)) {
      statement.setString(1, teacher.getLogin());
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      LOGGER.error(HAVE_NOT_TEACHER, e);
      throw new DaoException(HAVE_NOT_TEACHER, e);
    }
    return result;
  }

  @Override
  public Teacher.Position getPositionForTeacher(Human teacher) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_POSITION_OF_TEACHER)) {
      statement.setString(1, teacher.getLogin());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return Teacher.Position.valueOf(resultSet.getString(POSITION));
      } else {
        throw new DaoException(EMPTY_RESULTSET);
      }
    } catch (SQLException e) {
      LOGGER.error(ERROR_GET_POSITION_FOR_TEACHER, e);
      throw new DaoException(ERROR_GET_POSITION_FOR_TEACHER, e);
    }
  }
}
