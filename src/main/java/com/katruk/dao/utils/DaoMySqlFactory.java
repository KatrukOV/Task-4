package com.katruk.dao.utils;

import com.katruk.dao.DisciplineDAO;
import com.katruk.dao.EvaluationDAO;
import com.katruk.dao.HumanDAO;
import com.katruk.dao.StudentDAO;
import com.katruk.dao.TeacherDAO;
import com.katruk.dao.exceptions.DaoException;
import com.katruk.dao.mysql.DisciplineMySql;
import com.katruk.dao.mysql.EvaluationMySql;
import com.katruk.dao.mysql.HumanMySql;
import com.katruk.dao.mysql.StudentMySql;
import com.katruk.dao.mysql.TeacherMySql;

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
