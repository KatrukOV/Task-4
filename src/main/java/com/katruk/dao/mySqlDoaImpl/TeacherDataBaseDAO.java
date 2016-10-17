package com.katruk.dao.mySqlDoaImpl;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.interfase.TeacherDAO;
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


public class TeacherDataBaseDAO extends HumanDataBaseDAO implements TeacherDAO, TeacherPrepareStatement, TeacherTable {


    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(TeacherDataBaseDAO.class);

    public TeacherDataBaseDAO() {
    }

    @Override
    public void setPositionForTeacher(Human teacher, Teacher.Position position) throws DaoException {

        if (isPositionForTeacher(teacher)) {
            try (Connection connection = connectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SET_POSITION_FOR_TEACHER);
            ) {
                System.out.println(">>>>>>>>> position=" + position);
                System.out.println(">>>>>>>>> Teacher =" + teacher.getLogin());
                statement.setString(1, position.name());
                statement.setString(2, teacher.getLogin());
                System.out.println(">>>>>>>>> statement=" + statement);
                statement.executeUpdate();
                System.out.println(">>rez=" + statement.executeUpdate());
            } catch (SQLException e) {
                logger.error("Can't set position for Teacher", e);
                throw new DaoException("Can't set position for Teacher", e);
            }
        } else {
            logger.info("create new row position for Teacher");
            try (Connection connection = connectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(INSERT_POSITION_FOR_TEACHER);
            ) {
                System.out.println(">>>> Teacher id =" + teacher.getLogin());
                System.out.println(">>>> Teacher position =" + position);
                statement.setString(1, position.name());
                statement.setString(2, teacher.getLogin());
                statement.execute();
            } catch (SQLException e) {
                logger.error("Can't create position for Teacher", e);
                throw new DaoException("Can't create position for Teacher", e);
            }
        }

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_POSITION_FOR_TEACHER);
        ) {
            statement.setString(1, position.name());
            statement.setString(2, teacher.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    private boolean isPositionForTeacher(Human teacher) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(IS_POSITION_FOR_TEACHER);
        ) {
            statement.setString(1, teacher.getLogin());
            System.out.println(">>> st=" + statement);

            result = statement.executeQuery().next();
            System.out.println("rez=" + result);
        } catch (SQLException e) {
            logger.error("have not Student", e);
            throw new DaoException("have not Student", e);
        }
        return result;
    }

    @Override
    public Teacher.Position getPositionForTeacher(Human teacher) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_POSITION_OF_TEACHER);
        ) {
            statement.setString(1, teacher.getLogin());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Teacher.Position.valueOf(resultSet.getString(POSITION));
            } else throw new DaoException("resultSet get nothing");
        } catch (SQLException e) {
            logger.error("Can't get position for Teacher", e);
            throw new DaoException("Can't get position for Teacher", e);
        }
    }

}
