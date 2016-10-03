package com.katruk.dao.interfase;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domen.entity.Discipline;
import com.katruk.domen.entity.human.Human;

import java.util.List;

public interface StudentDAO extends HumanDAO {

    public List<Human> getAllStudentsFor(Discipline discipline) throws DaoException;

	public void setContractForStudent(Human student, boolean contract) throws DaoException;

	public boolean getContractForStudent(Human student) throws DaoException;

}
