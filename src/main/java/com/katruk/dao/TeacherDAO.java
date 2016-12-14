package com.katruk.dao;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domain.entity.Human;
import com.katruk.domain.entity.Teacher;

public interface TeacherDAO extends HumanDAO {

  void setPositionForTeacher(Human teacher, Teacher.Position position) throws DaoException;

  Teacher.Position getPositionForTeacher(Human teacher) throws DaoException;

}
