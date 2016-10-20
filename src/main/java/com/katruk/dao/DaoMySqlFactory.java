package com.katruk.dao;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.mySqlDoa.DisciplineMySql;
import com.katruk.dao.mySqlDoa.EvaluationMySql;
import com.katruk.dao.mySqlDoa.HumanMySql;
import com.katruk.dao.mySqlDoa.StudentMySql;
import com.katruk.dao.mySqlDoa.TeacherMySql;
import com.katruk.dao.utils.ConnectionPool;

import java.sql.Connection;

public class DaoMySqlFactory extends DaoFactory {

  private static ConnectionPool connectionPool;

  public static Connection getConnection() throws DaoException {
    if (connectionPool == null) {
      connectionPool = ConnectionPool.getInstance();
    }
    return connectionPool.getConnection();
  }

  public static void close(Connection connection) throws DaoException {
    connectionPool.close(connection);
  }

  @Override
  public HumanDAO getHumanDAO() {
    return new HumanMySql();
  }

  @Override
  public StudentDAO getStudentDAO() {
    return new StudentMySql();
  }

  @Override
  public TeacherDAO getTeacherDAO() {
    return new TeacherMySql();
  }

  @Override
  public DisciplineDAO getDisciplineDAO() {
    return new DisciplineMySql();
  }

  @Override
  public EvaluationDAO getEvaluationDAO() {
    return new EvaluationMySql();
  }
}
