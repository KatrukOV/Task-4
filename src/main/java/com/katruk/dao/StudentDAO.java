package com.katruk.dao;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Human;

import java.util.List;

public interface StudentDAO extends HumanDAO {

  List<Human> getStudentsByDiscipline(Discipline discipline) throws DaoException;

  void setContractForStudent(Human student, boolean contract) throws DaoException;

  boolean getContractForStudent(Human student) throws DaoException;

}
