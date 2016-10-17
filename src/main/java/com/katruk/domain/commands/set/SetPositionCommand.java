package com.katruk.domain.commands.set;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.entity.human.Human;
import com.katruk.domain.entity.human.Teacher;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetPositionCommand implements ICommand, PageAttribute {

  private static final Logger logger = Logger.getLogger(SetPositionCommand.class);

  private String page;
  private DaoFactory daoFactory = DaoFactory.getDAOFactory();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    try {
      Teacher.Position position = Teacher.Position.valueOf(request.getParameter(POSITION));
      System.out.println(">>>>>>>>>>>> position= " + position);

      Human human = daoFactory.getHumanDAO().get(request.getParameter(LOGIN));
      System.out.println(">>>>>>>>>>>> teacher login= " + human);

      daoFactory.getTeacherDAO().setPositionForTeacher(human, position);

      List<Human> teacherList = daoFactory.getTeacherDAO().getAllByRole(Human.Role.TEACHER);
      request.setAttribute("teacherList", teacherList);
      page = Config.getInstance().getValue(Config.ALL_TEACHERS);
//            page = new RedirectToLobbyCommand().execute(request, response);

      logger.info(String.format("set position=%s for teacher= %s", position, human));
    } catch (Exception e) {
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error("Unable set position for teacher", e);
    }
    return page;
  }
}
