package com.katruk.dao.interfase;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domen.entity.human.Human;
import com.katruk.domen.entity.human.Teacher;

public interface TeacherDAO extends HumanDAO {

	public void setPositionForTeacher(Human teacher, Teacher.Position position) throws DaoException;

	public Teacher.Position getPositionForTeacher(Human teacher) throws DaoException;

}
