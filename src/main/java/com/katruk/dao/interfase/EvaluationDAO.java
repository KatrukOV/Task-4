package com.katruk.dao.interfase;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Evaluation;
import com.katruk.domain.entity.human.Human;

import java.util.List;

public interface EvaluationDAO extends IDAO<Evaluation> {



	public void setStatusForStudent(Human student, Discipline discipline, Evaluation.StatusInDiscipline status)
			throws DaoException;

	public Evaluation.StatusInDiscipline getStatusForStudent(Human student, Discipline discipline)
			throws DaoException;

//	public List<Discipline> getDisciplines(Human student) throws DaoException;

    public List<Discipline> getDisciplinesByStatus(Human student, Evaluation.StatusInDiscipline status)
            throws DaoException;

    public List<Evaluation> getByStudentAndStatus(Human student, Evaluation.StatusInDiscipline status)
            throws DaoException;

    public List<Evaluation> getByDisciplineAndStatus(Discipline discipline, Evaluation.StatusInDiscipline status)
            throws DaoException;

    public Evaluation getByDisciplineAndStudent(Discipline discipline, Human student) throws DaoException;

//    public List<Human> getStudentOfTeacher(Human teacher, Evaluation.StatusInDiscipline status) throws DaoException;


//	public Map<String, String> collectMarksAndFeedbackFor(Human student);

//	public List<Discipline> getAllAvailableDisciplinesFor(Human student);
//
//	public List<Discipline> getAllDisciplinesForStudent(Human human);
//
//	public List<Discipline> getAllDisciplinesOfTeacher(Human teacher);

//	public String getFeedBackForStudent(Human student, Discipline discipline);
//
//	public String getMarkForStudent(Human student, Discipline discipline);

//	public boolean saveEvaluation(Discipline discipline, Human student, String mark, String feedback);

//	public boolean enroll(Discipline discipline, Human human);
//
//	public boolean unEnroll(Discipline discipline, Human human);

}
