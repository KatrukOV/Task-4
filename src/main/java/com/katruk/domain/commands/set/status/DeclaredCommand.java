package com.katruk.domain.commands.set.status;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Evaluation;
import com.katruk.domain.entity.human.Student;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeclaredCommand implements ICommand, PageAttribute {

  private static final String ERROR_LOGIN = "Can't declared to discipline";
  private static final Logger logger = Logger.getLogger(DeclaredCommand.class);
  private DaoFactory daoFactory = DaoFactory.getDAOFactory();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String page = null;
    System.out.println("begin declared");
    try {
      HttpSession session = request.getSession();
      String title = request.getParameter(TITLE);

      System.out.println("title=" + title);

      Student student = (Student) session.getAttribute(USER);

      Discipline discipline = daoFactory.getDisciplineDAO().get(title);

      Evaluation evaluation = new Evaluation();

      evaluation.setDiscipline(discipline);
      evaluation.setStudent(student);

      evaluation.setStatus(Evaluation.StatusInDiscipline.DECLARED);
      System.out.println("stud=" + student);
      System.out.println("stud id=" + student.getId());
      System.out.println("dis=" + discipline);
      System.out.println("dis id=" + discipline.getId());
      Evaluation
          evaluationInDB =
          daoFactory.getEvaluationDAO().getByDisciplineAndStudent(discipline, student);
      if (evaluationInDB == null) {
        daoFactory.getEvaluationDAO().create(evaluation);
        List<Discipline> disciplineList = daoFactory.getDisciplineDAO().getAll();
        request.setAttribute(DISCIPLINE_LIST, disciplineList);
      } else {
        if (evaluationInDB.getStatus().equals(Evaluation.StatusInDiscipline.REVOKED)) {
          daoFactory.getEvaluationDAO().setStatusForStudent(student, discipline,
                                                            Evaluation.StatusInDiscipline.DECLARED);
        } else {
          request.setAttribute(ERROR, "you can't declared discipline");
        }
      }

      System.out.println("eval=" + evaluation);

      System.out.println("OK create  eval");

      page = Config.getInstance().getValue(Config.DISCIPLINES);
    } catch (Exception e) {
      request.setAttribute("error", ERROR_LOGIN);
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error(ERROR_LOGIN, e);
    }
    return page;
  }
}
