package com.katruk.dao.utils;

import com.katruk.dao.DisciplineDAO;
import com.katruk.dao.EvaluationDAO;
import com.katruk.dao.HumanDAO;
import com.katruk.dao.StudentDAO;
import com.katruk.dao.TeacherDAO;

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
