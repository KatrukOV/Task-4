package com.katruk.domain.commands.set.status;

import com.katruk.dao.utils.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Evaluation;
import com.katruk.domain.entity.Student;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletedCommand implements ICommand, PageAttribute {

  private static final String ERROR_LOGIN = "Can't set set in student and discipline";
  private static final Logger logger = Logger.getLogger(RevokedCommand.class);
  private DaoFactory daoFactory = DaoFactory.getDAOFactory();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String page = null;
    try {
      String title = request.getParameter(TITLE);
      Student student = (Student) daoFactory.getHumanDAO().get(request.getParameter(LOGIN));

      Discipline discipline = daoFactory.getDisciplineDAO().get(title);
      daoFactory.getEvaluationDAO()
          .setStatusForStudent(student, discipline, Evaluation.StatusInDiscipline.DELETED);

      page = Config.getInstance().getValue(Config.DISCIPLINES);
    } catch (Exception e) {
      request.setAttribute("error", ERROR_LOGIN);
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error(ERROR_LOGIN, e);
    }
    return page;
  }
}

