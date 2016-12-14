package com.katruk.domain.commands.redirect;


import com.katruk.dao.utils.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.dto.StudentMark;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Evaluation;
import com.katruk.domain.entity.Student;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        for (Evaluation evaluation : evaluationList) {
          Discipline discipline = evaluation.getDiscipline();
          studentMarkList.add(new StudentMark(discipline.getTitle(),
                                              evaluation.getMark().name(),
                                              evaluation.getFeedback()));
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
