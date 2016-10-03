package com.katruk.dao;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.interfase.*;
import com.katruk.dao.mySqlDoaImpl.*;
import com.katruk.dao.utils.ConnectionPool;

import java.sql.Connection;

public class DaoMySqlFactory extends DaoFactory {

    private static ConnectionPool connectionPool;

    public static Connection getConnection() throws DaoException {
        if (connectionPool == null)
            connectionPool = ConnectionPool.getInstance();
        return  connectionPool.getConnection();
    }

    public static void close(Connection connection) throws DaoException {
        connectionPool.close(connection);
    }

    @Override
    public HumanDAO getHumanDAO() {
        return new HumanDataBaseDAO();
    }

    @Override
    public StudentDAO getStudentDAO() {
        return new StudentDataBaseDAO();
    }

    @Override
    public TeacherDAO getTeacherDAO() {
        return new TeacherDataBaseDAO();
    }

    @Override
    public DisciplineDAO getDisciplineDAO() {
        return new DisciplineDataBaseDAO();
    }

    @Override
    public EvaluationDAO getEvaluationDAO() {
        return new EvaluationDataBaseDAO();
    }
}
