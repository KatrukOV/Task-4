package com.katruk.dao;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Evaluation;
import com.katruk.domain.entity.Human;

import java.util.List;

public interface EvaluationDAO extends IDAO<Evaluation> {


  void setStatusForStudent(Human student, Discipline discipline,
                           Evaluation.StatusInDiscipline status)
      throws DaoException;

  Evaluation.StatusInDiscipline getStatusForStudent(Human student, Discipline discipline)
      throws DaoException;

  List<Discipline> getDisciplinesByStatus(Human student,
                                          Evaluation.StatusInDiscipline status)
      throws DaoException;

  List<Evaluation> getByStudentAndStatus(Human student, Evaluation.StatusInDiscipline status)
      throws DaoException;

  List<Evaluation> getByDisciplineAndStatus(Discipline discipline,
                                            Evaluation.StatusInDiscipline status)
      throws DaoException;

  Evaluation getByDisciplineAndStudent(Discipline discipline, Human student)
      throws DaoException;
}
