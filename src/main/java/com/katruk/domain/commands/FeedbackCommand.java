package com.katruk.domain.commands;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.redirect.RedirectToEvaluationCommand;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Evaluation;
import com.katruk.domain.entity.human.Student;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeedbackCommand implements ICommand, PageAttribute {

  private static final Logger logger = Logger.getLogger(RedirectToEvaluationCommand.class);
  private static final String ERROR_LOGIN = "Can't set mark";
  private String page;
  private DaoFactory daoFactory = DaoFactory.getDAOFactory();

  public FeedbackCommand() {
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String page = null;
    try {
      String title = request.getParameter(TITLE);
      String mark = request.getParameter(MARK);
      String feedback = request.getParameter(FEEDBACK);
      String studentLogin = request.getParameter(STUDENT);
      Discipline discipline = daoFactory.getDisciplineDAO().get(title);
      Student student = (Student) daoFactory.getStudentDAO().get(studentLogin);
      student.setContract(daoFactory.getStudentDAO().getContractForStudent(student));

      Evaluation evaluation = new Evaluation(discipline, student,
                                             Evaluation.StatusInDiscipline.CONFIRMED,
                                             Evaluation.Mark.valueOf(mark), feedback);

      daoFactory.getEvaluationDAO().update(evaluation);

      List<Evaluation> evaluationList = daoFactory.getEvaluationDAO().getByDisciplineAndStatus(
          discipline, Evaluation.StatusInDiscipline.CONFIRMED);

      if (evaluationList != null) {
        request.setAttribute(EVALUATION_LIST, evaluationList);
        logger.info(String.format("show all CONFIRMED evaluations"));
      }
      page = Config.getInstance().getValue(Config.TEACHER_EVALUATION);
    } catch (Exception e) {
      request.setAttribute(ERROR, ERROR_LOGIN);
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error(ERROR_LOGIN, e);
    }
    return page;
  }
}
