package com.katruk.domain.commands;


import com.katruk.dao.utils.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.Teacher;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddDisciplineCommand implements ICommand, PageAttribute {

  private static final Logger logger = Logger.getLogger(AddDisciplineCommand.class);

  private String page;
  private DaoFactory daoFactory = DaoFactory.getDAOFactory();


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    try {
      String title = request.getParameter(TITLE);

      Teacher teacher = (Teacher) request.getSession().getAttribute(USER);

      System.out.println(">>>> teacher session=" + teacher);
      System.out.println(">>>> title=" + title);

      Discipline discipline = new Discipline(title, teacher);
      daoFactory.getDisciplineDAO().create(discipline);

      List<Discipline>
          disciplines =
          daoFactory.getDisciplineDAO().getAllDisciplinesOfTeacher(teacher);

      if (disciplines != null) {
        request.setAttribute(DISCIPLINE_LIST, disciplines);
        logger.info(String.format("show all teacher Disciplines"));
      }

      page = Config.getInstance().getValue(Config.TEACHER_DISCIPLINES);
      logger.info(String.format("add new Discipline = %s", discipline));
    } catch (Exception e) {
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error("Unable to create a discipline", e);
    }

    return page;
  }
}
