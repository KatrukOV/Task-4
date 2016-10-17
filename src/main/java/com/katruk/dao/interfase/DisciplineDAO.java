package com.katruk.dao.interfase;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.human.Human;

import java.util.List;

public interface DisciplineDAO extends IDAO<Discipline> {

  Discipline get(String title) throws DaoException;

  List<Discipline> getAllDisciplinesOfTeacher(Human teacher) throws DaoException;
}
