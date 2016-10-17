package com.katruk.dao.mySqlDoaImpl;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.interfase.EvaluationDAO;
import com.katruk.dao.sql.statment.EvaluationPrepareStatement;
import com.katruk.dao.sql.table.EvaluationTable;
import com.katruk.dao.utils.ConnectionPool;
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

public class EvaluationDataBaseDAO implements EvaluationDAO, EvaluationTable, EvaluationPrepareStatement {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(EvaluationDataBaseDAO.class);

    public EvaluationDataBaseDAO() {
    }

    @Override
    public List<Evaluation> getAll() throws DaoException {
        List<Evaluation> resultList = new ArrayList<Evaluation>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL);
        ) {
            ResultSet resultSet = statement.executeQuery();
            DisciplineDataBaseDAO disciplineDAO = new DisciplineDataBaseDAO();
            StudentDataBaseDAO studentDataBaseDAO = new StudentDataBaseDAO();
            while (resultSet.next()) {
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
            logger.error("Can't get all Evaluation", e);
            throw new DaoException("Can't get all Evaluation", e);
        }
    }

    @Override
    public Evaluation getByDisciplineAndStudent(Discipline discipline, Human human) throws DaoException{
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_DISCIPLINE_AND_STUDENT);
        ) {
            statement.setInt(1, discipline.getId());
            statement.setInt(2, human.getId());
            ResultSet resultSet = statement.executeQuery();
            DisciplineDataBaseDAO disciplineDAO = new DisciplineDataBaseDAO();
            StudentDataBaseDAO studentDataBaseDAO = new StudentDataBaseDAO();
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
            logger.error("Can't get all Evaluation", e);
            throw new DaoException("Can't get all Evaluation", e);
        }
    }

    @Override
    public Evaluation get(int id) throws DaoException {
        Evaluation evaluation;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            DisciplineDataBaseDAO disciplineDAO = new DisciplineDataBaseDAO();
            StudentDataBaseDAO studentDataBaseDAO = new StudentDataBaseDAO();
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
            logger.error("Can't get Evaluation", e);
            throw new DaoException("Can't get Evaluation", e);
        }
    }

    @Override
    public void create(Evaluation value) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE);
        ) {
            System.out.println("eval in dao="+value);

            statement.setInt(1, value.getDiscipline().getId());
            statement.setInt(2, value.getStudent().getId());
            statement.setString(3, value.getStatus().name());
            statement.setString(4, (value.getMark() == null) ?  null : value.getMark().name() );
            statement.setString(5, value.getFeedback());
            System.out.println("eval stat="+statement);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Can't create Evaluation", e);
            throw new DaoException("Can't create Evaluation", e);
        }
    }

    @Override
    public void remove(Evaluation value) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE);
        ) {
            statement.setInt(1, value.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't remove Evaluation", e);
            throw new DaoException("Can't remove Evaluation", e);
        }
    }

    @Override
    public void update(Evaluation value) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE);
        ) {
            statement.setString(1, value.getStatus().name());
            statement.setString(2, value.getMark().name());
            statement.setString(3, value.getFeedback());
            statement.setInt(4, value.getDiscipline().getId());
            statement.setInt(5, value.getStudent().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update Evaluation", e);
            throw new DaoException("Can't update Evaluation", e);
        }
    }

    @Override
    public void setStatusForStudent(Human student, Discipline discipline, Evaluation.StatusInDiscipline status)
            throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_STATUS_FOR_STUDENT_IN_DISCIPLINE);
        ) {
            statement.setString(1, status.name());
            statement.setInt(2, discipline.getId());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't set set for Student in Discipline", e);
            throw new DaoException("Can't set set for Student in Discipline", e);
        }
    }

    @Override
    public Evaluation.StatusInDiscipline getStatusForStudent(Human student, Discipline discipline)
            throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_STATUS_OF_STUDENT_IN_DISCIPLINE);
        ) {
            statement.setInt(1, discipline.getId());
            statement.setInt(2, student.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS));
            } else throw new DaoException("resultSet get nothing");
        } catch (SQLException e) {
            logger.error("Can't get position for Teacher", e);
            throw new DaoException("Can't get position for Teacher", e);
        }
    }

    @Override
    public List<Discipline> getDisciplinesByStatus(Human student, Evaluation.StatusInDiscipline status)
           throws DaoException {
        List<Discipline> resultList = new ArrayList<Discipline>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_DISCIPLINES_BY_STATUS);
        ) {
            System.out.println("stud id="+student.getId());

            statement.setInt(1, student.getId());
            statement.setString(2, status.name());

            System.out.println("stat="+statement);
            ResultSet resultSet = statement.executeQuery();
            DisciplineDataBaseDAO disciplineDAO = new DisciplineDataBaseDAO();
            while (resultSet.next()) {
                resultList.add(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
            }
            return resultList;
        } catch (SQLException e) {
            logger.error("Can't get all Evaluation", e);
            throw new DaoException("Can't get all Evaluation", e);
        }
    }

    @Override
    public List<Evaluation> getByStudentAndStatus(Human human, Evaluation.StatusInDiscipline status)
            throws DaoException {
        List<Evaluation> resultList = new ArrayList<Evaluation>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_STUDENT_AND_STATUS);
        ) {
            System.out.println("stud id="+human.getId());

            statement.setInt(1, human.getId());
            statement.setString(2, status.name());

            System.out.println("stat="+statement);
            ResultSet resultSet = statement.executeQuery();
            Evaluation evaluation;

            DisciplineDataBaseDAO disciplineDAO = new DisciplineDataBaseDAO();
            StudentDataBaseDAO studentDataBaseDAO = new StudentDataBaseDAO();
            while (resultSet.next()) {
                evaluation = new Evaluation();
                evaluation.setId(resultSet.getInt(ID));
                evaluation.setDiscipline(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
                Student student = (Student) studentDataBaseDAO.get(resultSet.getInt(STUDENT_ID));
                student.setContract(studentDataBaseDAO.getContractForStudent(student));
                evaluation.setStudent(student);
                evaluation.setStatus(Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS)));
                evaluation.setMark(resultSet.getString(MARK)==null ? null: Evaluation.Mark.valueOf(resultSet.getString(MARK)));
                evaluation.setFeedback(resultSet.getString(FEEDBACK));
                resultList.add(evaluation);
            }
            return resultList;
        } catch (SQLException e) {
            logger.error("Can't get all Evaluation", e);
            throw new DaoException("Can't get all Evaluation", e);
        }
    }

    @Override
    public List<Evaluation> getByDisciplineAndStatus(
            Discipline discipline, Evaluation.StatusInDiscipline status) throws DaoException {
        List<Evaluation> resultList = new ArrayList<Evaluation>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_DISCIPLINE_AND_STATUS);
        ) {
            System.out.println("!!!!!!!!!!!!!!!!!dis id=" + discipline.getId());

            statement.setInt(1, discipline.getId());
            statement.setString(2, status.name());

            System.out.println("stat="+statement);
            ResultSet resultSet = statement.executeQuery();
            Evaluation evaluation;
            DisciplineDataBaseDAO disciplineDAO = new DisciplineDataBaseDAO();
            StudentDataBaseDAO studentDataBaseDAO = new StudentDataBaseDAO();
            System.out.println(" begin get rez");
            while (resultSet.next()) {
                System.out.println(" rez 1 =");
                evaluation = new Evaluation();
                evaluation.setId(resultSet.getInt(ID));
                System.out.println("dis id="+resultSet.getInt(DISCIPLINE_ID));
                evaluation.setDiscipline(disciplineDAO.get(resultSet.getInt(DISCIPLINE_ID)));
                Student student = (Student) studentDataBaseDAO.get(resultSet.getInt(STUDENT_ID));
                student.setContract(studentDataBaseDAO.getContractForStudent(student));
                evaluation.setStudent(student);
                evaluation.setStatus(Evaluation.StatusInDiscipline.valueOf(resultSet.getString(STATUS)));
                evaluation.setMark(resultSet.getString(MARK)==null ? null: Evaluation.Mark.valueOf(resultSet.getString(MARK)));
                evaluation.setFeedback(resultSet.getString(FEEDBACK));
                System.out.println("eval finish="+evaluation);
                resultList.add(evaluation);
            }
            System.out.println("GET BY dis and status OK");
            return resultList;
        } catch (SQLException e) {
            logger.error("Can't get all Evaluation Of Discipline ", e);
            throw new DaoException("Can't get all Evaluation Of Discipline", e);
        }
    }
}
