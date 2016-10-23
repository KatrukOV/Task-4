package com.katruk.domain.commands;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.entity.human.Human;
import com.katruk.domain.entity.human.Student;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ICommand, PageAttribute {

  private static final Logger logger = Logger.getLogger(RegistrationCommand.class);
  private DaoFactory daoFactory = DaoFactory.getDAOFactory();

  private static final String ERROR = "error";
  private static final String LOG_OK = "User successfully registered";
  private static final String LOG_ERROR = "error detected";
  private static final String NULL_VALUE_ERROR = "Please, fill in all the fields.";
  private static final String USER_EXISTS = "Such user already exists.";
  private static final String PASS_NOT_MATCH = "Passwords don't match.";
  private static final boolean CONTRACT_DEFAULT = true;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {

    String page = null;
    String login = request.getParameter("login").trim();
    String password1 = request.getParameter("password1").trim();
    String password2 = request.getParameter("password2").trim();
    String name = request.getParameter("name").trim();
    String lastName = request.getParameter("lastName").trim();
    String patronymic = request.getParameter("patronymic").trim();

    System.out.println(">> userLogin=" + login + "  name= " + name);
    try {
      if (login != null && password1 != null && password2 != null && name != null
          && lastName != null && patronymic != null) {
        if (!login.isEmpty() && !password1.isEmpty() && !password2.isEmpty() && !name.isEmpty()
            && !lastName.isEmpty() && !patronymic.isEmpty()) {

          if (password1.equals(password2)) {
            Human user = daoFactory.getHumanDAO().get(login);
            System.out.println(">> user from dao=" + user);
            if (user == null) {
              user = new Student(login, password1, name, lastName, patronymic);
//							humanDAO.create(user);

              daoFactory.getStudentDAO().create(user);
              System.out.println(">>>>>>>>>>>>>>>>>>>>> new user =" + user);
              System.out.println(">>>>>>>>>>>>>>>>> new user id =" + user.getId());

//              Human student = daoFactory.getStudentDAO().get(user.getLogin());
//                            System.out.println(">>>>>>>>>>>> new student =" + student);
//                            System.out.println(">>>>>>>>. new student id =" +student.getId());
              daoFactory.getStudentDAO().setContractForStudent(user, CONTRACT_DEFAULT);
              System.out.println(">>OK! set contract");
              HttpSession session = request.getSession();
              session.setAttribute(LOGIN, user.getLogin());
              session.setAttribute(USER, user);
              session.setAttribute(CONTRACT, CONTRACT_DEFAULT);
//	      session.setAttribute(CONTRACT, ((Student)student).isContract());

              page = Config.getInstance().getValue(Config.REGISTRATION_OK);
//	      addRequestContent(request, user);
              logger.info(LOG_OK);
            } else {
              request.setAttribute(ERROR, USER_EXISTS);
              page = Config.getInstance().getValue(Config.REGISTRATION_ERROR);
              logger.error(String.format(LOG_ERROR, USER_EXISTS));
            }
          } else {
            request.setAttribute(ERROR, PASS_NOT_MATCH);
            page = Config.getInstance().getValue(Config.REGISTRATION_ERROR);
            logger.error(String.format(LOG_ERROR, PASS_NOT_MATCH));
          }
        } else {
          request.setAttribute(ERROR, NULL_VALUE_ERROR);
          page = Config.getInstance().getValue(Config.REGISTRATION_ERROR);
          logger.error(String.format(LOG_ERROR, NULL_VALUE_ERROR));
        }
      } else {
        request.setAttribute(ERROR, NULL_VALUE_ERROR);
        page = Config.getInstance().getValue(Config.REGISTRATION_ERROR);
        logger.error(String.format(LOG_ERROR, NULL_VALUE_ERROR));
      }

    } catch (Exception e) {
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error(LOG_ERROR, e);
    }
    System.out.println(">> page exit=" + page);
//		request.setAttribute(PAGE, page);
    return page;
  }

}