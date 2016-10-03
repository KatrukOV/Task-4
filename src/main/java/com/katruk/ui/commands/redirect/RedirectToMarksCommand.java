package com.katruk.ui.commands.redirect;


import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.dto.StudentMark;
import com.katruk.domen.entity.Discipline;
import com.katruk.domen.entity.Evaluation;
import com.katruk.domen.entity.human.Student;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class RedirectToMarksCommand implements ICommand, PageAttribute {

	private static final Logger logger = Logger.getLogger(RedirectToMarksCommand.class);

	private String page;
    private DaoFactory daoFactory = DaoFactory.getDAOFactory();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
//			String title = request.getParameter(TITLE);
            Student student = (Student) session.getAttribute(USER);

            List<StudentMark> studentMarkList = new ArrayList<>();
            List<Evaluation> evaluationList = daoFactory.getEvaluationDAO().
                    getByStudentAndStatus(student, Evaluation.StatusInDiscipline.CONFIRMED);

            if (evaluationList != null) {
                for(Evaluation evaluation : evaluationList){
                    Discipline discipline = evaluation.getDiscipline();
                    studentMarkList.add(new StudentMark(discipline.getTitle(),
                            evaluation.getMark().name(), evaluation.getFeedback()));
                }
            }
	        request.setAttribute(STUDENT_MARK_LIST, studentMarkList);
            logger.info(String.format("show marks of student: %s", student));

            page = Config.getInstance().getValue(Config.STUDENT_MARKS);

        } catch (Exception e) {
            page = Config.getInstance().getValue(Config.ERROR_PAGE);
            logger.error("Can't show marks of student", e);
        }
        return page;
	}
}
