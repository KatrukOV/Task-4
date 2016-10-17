package com.katruk.dao.interfase;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.human.Human;

import java.util.List;

public interface DisciplineDAO extends IDAO<Discipline> {

	public Discipline get(String title) throws DaoException;

    public List<Discipline> getAllDisciplinesOfTeacher(Human teacher) throws DaoException;


//	public Map<String, String> collectMarksAndFeedbackFor(Human student);

//	public List<Discipline> getAllAvailableDisciplinesFor(Human student);
//
//	public List<Discipline> getAllDisciplinesForStudent(Human human);



//	public String getFeedBackForStudent(Human student, Discipline discipline, Connection connection);
//
//	public String getMarkForStudent(Human student, Discipline discipline, Connection connection);
//
//	public boolean saveFeedback(Discipline discipline, Human student, String mark, String feedback);

//	public boolean enroll(Discipline discipline, Human human);
//
//	public boolean unEnroll(Discipline discipline, Human human);

}
