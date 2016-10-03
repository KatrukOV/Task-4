package com.katruk.dao.mySqlDoaImpl;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.interfase.StudentDAO;
import com.katruk.dao.sql.statment.StudentPrepareStatement;
import com.katruk.dao.sql.table.StudentTable;
import com.katruk.dao.utils.ConnectionPool;
import com.katruk.domen.entity.Discipline;
import com.katruk.domen.entity.human.Human;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDataBaseDAO extends HumanDataBaseDAO implements StudentDAO, StudentPrepareStatement, StudentTable {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(StudentDataBaseDAO.class);

    public StudentDataBaseDAO() {
    }

    @Override
    public List<Human> getAllStudentsFor(Discipline discipline) throws DaoException {

        List<Human> resultList = new ArrayList<Human>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_STUDENT_FOR_DISCIPLINE);
        ) {
            statement.setInt(1, discipline.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultList.add(get(resultSet.getInt(1)));
//                resultList.add(get(resultSet.getInt(STUDENT_ID)));
//                resultList.add(get(resultSet.getInt("student_human_id")));
            }
            return resultList;
        } catch (SQLException e) {
            logger.error("Can't get students for discipline", e);
            throw new DaoException("Can't get students for discipline", e);
        }
    }

    @Override
    public void setContractForStudent(Human student, boolean contract) throws DaoException {

        System.out.println(">>>>>>>>> try set contract");

        if (isContractForStudent(student)) {
            try (Connection connection = connectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SET_CONTRACT_FOR_STUDENT);
            ) {
                System.out.println(">>>>>>>>> contract=" + contract);
                System.out.println(">>>>>>>>> stud =" + student.getLogin());
                statement.setBoolean(1, contract);
                statement.setString(2, student.getLogin());
                System.out.println(">>>>>>>>> statement=" + statement);
                statement.executeUpdate();
                System.out.println(">>rez=" + statement.executeUpdate());
            } catch (SQLException e) {
                logger.error("Can't set Contract for Student", e);
                throw new DaoException("Can't set Contract for Student", e);
            }
        } else {
            logger.info("create new row Contract for Student");
            try (Connection connection = connectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(INSERT_CONTRACT_FOR_STUDENT);
            ) {

                System.out.println(">>>> student id =" + student.getLogin());
                System.out.println(">>>> student contract =" + contract);
                statement.setBoolean(1, contract);
                statement.setString(2, student.getLogin());
                statement.execute();
            } catch (SQLException e) {
                logger.error("Can't create Contract for Student", e);
                throw new DaoException("Can't create Contract for Student", e);
            }
        }
    }

    private boolean isContractForStudent(Human student) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(IS_CONTRACT_FOR_STUDENT);
        ) {
            statement.setString(1, student.getLogin());
            System.out.println(">>> st=" + statement);

            result = statement.executeQuery().next();
            System.out.println("rez=" + result);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                resultSet.getInt(HUMAN_ID); return true;
//            } else throw new DaoException("resultSet get nothing");
        } catch (SQLException e) {
            logger.error("have not Student", e);
            throw new DaoException("have not Student", e);
        }
        return result;
    }

    @Override
    public boolean getContractForStudent(Human student) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CONTRACT_OF_STUDENT);
        ) {
            statement.setInt(1, student.getId());
            System.out.println("stat="+statement);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(CONTRACT);
            } else throw new DaoException("resultSet get nothing");
        } catch (SQLException e) {
            logger.error("Can't get Contract for Student", e);
            throw new DaoException("Can't get Contract for Student", e);
        }
    }


}
