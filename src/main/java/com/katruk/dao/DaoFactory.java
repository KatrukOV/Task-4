package com.katruk.dao;

import com.katruk.dao.interfase.DisciplineDAO;
import com.katruk.dao.interfase.EvaluationDAO;
import com.katruk.dao.interfase.HumanDAO;
import com.katruk.dao.interfase.StudentDAO;
import com.katruk.dao.interfase.TeacherDAO;

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
