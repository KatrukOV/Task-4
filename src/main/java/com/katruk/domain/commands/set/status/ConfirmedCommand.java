package com.katruk.domain.commands.set.status;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Evaluation;
import com.katruk.domain.entity.human.Human;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmedCommand implements ICommand, PageAttribute {

  private static final String ERROR_LOGIN = "Can't Confirmed discipline";
  private static final Logger logger = Logger.getLogger(ConfirmedCommand.class);
  private DaoFactory daoFactory = DaoFactory.getDAOFactory();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String page = null;
    try {
      HttpSession session = request.getSession();

      String title = request.getParameter(TITLE);
      System.out.println("title=========" + title);

      Discipline discipline = daoFactory.getDisciplineDAO().get(title);
      System.out.println("discipline=========" + discipline);

      Human student = daoFactory.getHumanDAO().get(request.getParameter(LOGIN));
      System.out.println("student=========" + student);

      Evaluation.StatusInDiscipline
          status =
          daoFactory.getEvaluationDAO().getStatusForStudent(student, discipline);
      if (!status.equals(Evaluation.StatusInDiscipline.DELETED)
          && !status.equals(Evaluation.StatusInDiscipline.REVOKED)) {
        daoFactory.getEvaluationDAO()
            .setStatusForStudent(student, discipline, Evaluation.StatusInDiscipline.CONFIRMED);

        List<Evaluation> evaluationList = daoFactory.getEvaluationDAO().getByDisciplineAndStatus(
            discipline, Evaluation.StatusInDiscipline.DECLARED);

        List<Human> humanList = new ArrayList<>();
        if (evaluationList != null) {
          for (Evaluation evaluation : evaluationList) {
            humanList.add(evaluation.getStudent());
          }
        }
        request.setAttribute(TITLE, title);
        request.setAttribute(HUMAN_LIST, humanList);

      } else {
        request.setAttribute(ERROR, "can't CONFIRMED");
      }
      page = Config.getInstance().getValue(Config.TEACHER_CONFIRMED);
    } catch (Exception e) {
      request.setAttribute(ERROR, ERROR_LOGIN);
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error(ERROR_LOGIN, e);
    }
    return page;
  }
}
