package com.katruk.dao.mySqlDoa;

import com.katruk.dao.EvaluationDAO;
import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.sql.statment.EvaluationPrepareStatement;
import com.katruk.dao.sql.table.EvaluationTable;
import com.katruk.dao.utils.ConnectionPool;
import com.katruk.domain.Message;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Evaluation;
import com.katruk.domain.entity.human.Human;
import com.katruk.domain.entity.human.Student;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvaluationMySql
    implements EvaluationDAO, EvaluationTable, EvaluationPrepareStatement {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

  private static final Logger LOGGER = Logger.getLogger(EvaluationMySql.class);

  private static final Message MESSAGE = Message.getInstance();

  public EvaluationMySql() {
  }

  @Override
  public List<Evaluation> getAll() throws DaoException {
    List<Evaluation> resultList = new ArrayList<>();
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
      ResultSet resultSet = statement.executeQuery();
      DisciplineMySql disciplineDAO = new DisciplineMySql();
      StudentMySql studentDataBaseDAO = new StudentMySql();
      while (resultSet.next()) {
        // todo move/dell new
        Evaluation evaluation = new Evaluation();
        evaluation.setId(resultSet.getInt(ID));
        evaluation.setDiscipline(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
        Student student = (Student) studentDataBaseDAO.get(resultSet.getInt(STUDENT_ID));
        student.setContract(studentDataBaseDAO.getContractForStudent(student));
        evaluation.setStudent(student);
        evaluation.setStatus(Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS)));
        evaluation.setMark(Evaluation.Mark.valueOf(resultSet.getString(MARK)));
        evaluation.setFeedback(resultSet.getString(FEEDBACK));
        resultList.add(evaluation);
      }
      return resultList;
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_ALL_EVALUATION), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_GET_ALL_EVALUATION), e);
    }
  }

  @Override
  public Evaluation getByDisciplineAndStudent(Discipline discipline, Human human)
      throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_DISCIPLINE_AND_STUDENT)) {
      statement.setInt(1, discipline.getId());
      statement.setInt(2, human.getId());
      ResultSet resultSet = statement.executeQuery();
      DisciplineMySql disciplineDAO = new DisciplineMySql();
      StudentMySql studentDataBaseDAO = new StudentMySql();
      Evaluation evaluation = null;
      if (resultSet.next()) {
        evaluation = new Evaluation();
        evaluation.setId(resultSet.getInt(ID));
        evaluation.setDiscipline(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
        Student student = (Student) studentDataBaseDAO.get(resultSet.getInt(STUDENT_ID));
        student.setContract(studentDataBaseDAO.getContractForStudent(student));
        evaluation.setStudent(student);
        evaluation.setStatus(Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS)));
        evaluation.setMark(Evaluation.Mark.valueOf(resultSet.getString(MARK)));
        evaluation.setFeedback(resultSet.getString(FEEDBACK));
      }
      return evaluation;
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_ALL_EVALUATION_BY_DISCIPLINE_AND_STUDENT)
          , e);
      throw new DaoException(MESSAGE.getMessage(Message
                                                    .ERROR_GET_ALL_EVALUATION_BY_DISCIPLINE_AND_STUDENT),
                             e);
    }
  }

  @Override
  public Evaluation get(int id) throws DaoException {
    Evaluation evaluation;
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      DisciplineMySql disciplineDAO = new DisciplineMySql();
      StudentMySql studentDataBaseDAO = new StudentMySql();
      evaluation = new Evaluation();
      if (resultSet.next()) {
        evaluation.setId(resultSet.getInt(ID));
        evaluation.setDiscipline(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
        Student student = (Student) studentDataBaseDAO.get(resultSet.getInt(STUDENT_ID));
        student.setContract(studentDataBaseDAO.getContractForStudent(student));
        evaluation.setStudent(student);
        evaluation.setStatus(Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS)));
        evaluation.setMark(Evaluation.Mark.valueOf(resultSet.getString(MARK)));
        evaluation.setFeedback(resultSet.getString(FEEDBACK));
      }
      return evaluation;
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_EVALUATION_BY_ID), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_GET_EVALUATION_BY_ID), e);
    }
  }

  @Override
  public void create(Evaluation value) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(CREATE)) {
      statement.setInt(1, value.getDiscipline().getId());
      statement.setInt(2, value.getStudent().getId());
      statement.setString(3, value.getStatus().name());
      statement.setString(4, (value.getMark() == null) ? null : value.getMark().name());
      statement.setString(5, value.getFeedback());
      statement.execute();
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_CREATE_EVALUATION), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_CREATE_EVALUATION), e);
    }
  }

  @Override
  public void remove(Evaluation value) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(REMOVE)) {
      statement.setInt(1, value.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_REMOVE_EVALUATION), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_REMOVE_EVALUATION), e);
    }
  }

  @Override
  public void update(Evaluation value) throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE);
    ) {
      statement.setString(1, value.getStatus().name());
      statement.setString(2, value.getMark().name());
      statement.setString(3, value.getFeedback());
      statement.setInt(4, value.getDiscipline().getId());
      statement.setInt(5, value.getStudent().getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_UPDATE_EVALUATION), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_UPDATE_EVALUATION), e);
    }
  }

  @Override
  public void setStatusForStudent(Human student, Discipline discipline,
                                  Evaluation.StatusInDiscipline status)
      throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection
             .prepareStatement(SET_STATUS_FOR_STUDENT_IN_DISCIPLINE)) {
      statement.setString(1, status.name());
      statement.setInt(2, discipline.getId());
      statement.setInt(3, student.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_SET_STATUS_OF_STUDENT), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_SET_STATUS_OF_STUDENT), e);
    }
  }

  @Override
  public Evaluation.StatusInDiscipline getStatusForStudent(Human student, Discipline discipline)
      throws DaoException {
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection
             .prepareStatement(GET_STATUS_OF_STUDENT_IN_DISCIPLINE)) {
      statement.setInt(1, discipline.getId());
      statement.setInt(2, student.getId());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS));
      } else {
        // TODO: messege
        LOGGER.info("resultSet get nothing");
        throw new DaoException("resultSet get nothing");
      }
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_STATUS_OF_STUDENT), e);
      throw new DaoException(MESSAGE.getMessage(Message.ERROR_GET_STATUS_OF_STUDENT), e);
    }
  }

  @Override
  public List<Discipline> getDisciplinesByStatus(Human student,
                                                 Evaluation.StatusInDiscipline status)
      throws DaoException {
    List<Discipline> resultList = new ArrayList<>();
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_DISCIPLINES_BY_STATUS)) {
      statement.setInt(1, student.getId());
      statement.setString(2, status.name());
      ResultSet resultSet = statement.executeQuery();
      DisciplineMySql disciplineDAO = new DisciplineMySql();
      while (resultSet.next()) {
        resultList.add(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
      }
      return resultList;
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_DISCIPLINE_OF_STUDENT_BY_STATUS), e);
      throw new DaoException(MESSAGE.getMessage(Message
                                                    .ERROR_GET_DISCIPLINE_OF_STUDENT_BY_STATUS), e);
    }
  }

  @Override
  public List<Evaluation> getByStudentAndStatus(Human human, Evaluation.StatusInDiscipline status)
      throws DaoException {
    List<Evaluation> resultList = new ArrayList<>();
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_STUDENT_AND_STATUS)) {
      statement.setInt(1, human.getId());
      statement.setString(2, status.name());
      ResultSet resultSet = statement.executeQuery();
      Evaluation evaluation;
      DisciplineMySql disciplineDAO = new DisciplineMySql();
      StudentMySql studentDataBaseDAO = new StudentMySql();
      while (resultSet.next()) {
        evaluation = new Evaluation();
        evaluation.setId(resultSet.getInt(ID));
        evaluation.setDiscipline(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
        Student student = (Student) studentDataBaseDAO.get(resultSet.getInt(STUDENT_ID));
        student.setContract(studentDataBaseDAO.getContractForStudent(student));
        evaluation.setStudent(student);
        evaluation.setStatus(Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS)));
        evaluation.setMark(resultSet.getString(MARK) == null ? null : Evaluation.Mark
            .valueOf(resultSet.getString(MARK)));
        evaluation.setFeedback(resultSet.getString(FEEDBACK));
        resultList.add(evaluation);
      }
      return resultList;
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_EVALUATION_OF_STUDENT_BY_STATUS), e);
      throw new DaoException(MESSAGE.getMessage(Message
                                                    .ERROR_GET_EVALUATION_OF_STUDENT_BY_STATUS), e);
    }
  }

  @Override
  public List<Evaluation> getByDisciplineAndStatus(
      Discipline discipline, Evaluation.StatusInDiscipline status) throws DaoException {
    List<Evaluation> resultList = new ArrayList<Evaluation>();
    try (Connection connection = CONNECTION_POOL.getConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_DISCIPLINE_AND_STATUS)) {
      statement.setInt(1, discipline.getId());
      statement.setString(2, status.name());
      ResultSet resultSet = statement.executeQuery();
      Evaluation evaluation;
      DisciplineMySql disciplineDAO = new DisciplineMySql();
      StudentMySql studentDataBaseDAO = new StudentMySql();
      while (resultSet.next()) {
        evaluation = new Evaluation();
        evaluation.setId(resultSet.getInt(ID));
        evaluation.setDiscipline(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
        Student student = (Student) studentDataBaseDAO.get(resultSet.getInt(STUDENT_ID));
        student.setContract(studentDataBaseDAO.getContractForStudent(student));
        evaluation.setStudent(student);
        evaluation.setStatus(Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS)));
        evaluation.setMark(resultSet.getString(MARK) == null ? null : Evaluation.Mark
            .valueOf(resultSet.getString(MARK)));
        evaluation.setFeedback(resultSet.getString(FEEDBACK));
        resultList.add(evaluation);
      }
      return resultList;
    } catch (SQLException e) {
      LOGGER.error(MESSAGE.getMessage(Message.ERROR_GET_EVALUATION_OF_DISCIPLINE_BY_STATUS), e);
      throw new DaoException(MESSAGE.getMessage(Message
                                                    .ERROR_GET_EVALUATION_OF_DISCIPLINE_BY_STATUS),
                             e);
    }
  }
}
