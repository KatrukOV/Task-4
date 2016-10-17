package com.katruk.domain.commands.redirect;

import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.entity.human.Human;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RedirectToProfileCommand implements ICommand, PageAttribute {

  private static final Logger logger = Logger.getLogger(RedirectToProfileCommand.class);
  private static final String ERROR = "error";

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String page = null;
    try {
      HttpSession session = request.getSession();
      Human user = (Human) session.getAttribute(USER);
      Human.Role role = user.getRole();
      switch (role) {
        case STUDENT: {
          page = Config.getInstance().getValue(Config.STUDENT_PROFILE);
          break;
        }
        case TEACHER: {
          page = Config.getInstance().getValue(Config.TEACHER_PROFILE);
          break;
        }
        case ADMIN: {
          page = Config.getInstance().getValue(Config.ADMIN_PROFILE);
          break;
        }
      }
    } catch (Exception e) {
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error(ERROR, e);
    }
//		request.setAttribute(PAGE, page);
    return page;
  }
}
