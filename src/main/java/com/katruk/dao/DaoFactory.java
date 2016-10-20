package com.katruk.dao;

public abstract class DaoFactory {

  public static DaoFactory getDAOFactory() {
    return new DaoMySqlFactory();
  }

  public abstract HumanDAO getHumanDAO();

  public abstract StudentDAO getStudentDAO();

  public abstract TeacherDAO getTeacherDAO();

  public abstract DisciplineDAO getDisciplineDAO();

  public abstract EvaluationDAO getEvaluationDAO();


}
