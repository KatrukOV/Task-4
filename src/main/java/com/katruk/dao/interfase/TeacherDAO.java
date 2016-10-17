package com.katruk.dao.interfase;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domain.entity.human.Human;
import com.katruk.domain.entity.human.Teacher;

public interface TeacherDAO extends HumanDAO {

  void setPositionForTeacher(Human teacher, Teacher.Position position) throws DaoException;

  Teacher.Position getPositionForTeacher(Human teacher) throws DaoException;

}
