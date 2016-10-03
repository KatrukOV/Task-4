package com.katruk.ui.commands;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.human.Human;
import com.katruk.domen.entity.human.Teacher;
import com.katruk.ui.PageAttribute;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ICommand, PageAttribute {

	private static final String ERROR_LOGIN = "Wrong login or password";
	private static final Logger logger = Logger.getLogger(LoginCommand.class);
	private DaoFactory daoFactory = DaoFactory.getDAOFactory();


    @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = null;
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		HttpSession session = request.getSession();


		try {
			if (login != null && password != null) {

                System.out.println(">>good 2 login=" + login + " pas=" + password);
				if (!login.isEmpty() && !password.isEmpty()) {
//			Human user = ((HumanDataBaseDAO)DaoFactory.getInstance().create(DaoFactory.DaoType.HumanDAO)).get(login);


                    Human user = daoFactory.getHumanDAO().get(login);

                    System.out.println(">> user= " + user);

					if (user != null) {
						System.out.println(">>> pass page= " + password);
						System.out.println(">>> pass user= " + DigestUtils.sha1Hex(password));
						System.out.println(">>> pass dao= " + user.getPassword());

						if (user.getPassword().equals(DigestUtils.sha1Hex(password))) {
//						HttpSession session = request.getSession();
							session.setAttribute(USER, user);
							session.setAttribute(LOGIN, user.getLogin());
							session.setAttribute(ROLE, user.getRole());

							session.setMaxInactiveInterval(60 * 60 * 24);

//						 todo????
//							request.setAttribute(LOGIN, user.getLogin());
//							request.setAttribute(USER, user);
//							addRequestContent(request, user);
//						 todo????

							Human.Role role = user.getRole();
							System.out.println(">> role= " + role);
							switch (role) {
								case STUDENT: {
//                                    todo
                                    boolean contractForStudent = daoFactory.getStudentDAO().getContractForStudent(user);
                                    System.out.println("contractForStudent-"+contractForStudent);
                                    session.setAttribute(CONTRACT, contractForStudent);

									page = Config.getInstance().getValue(Config.STUDENT_PROFILE);
									break;
								}
								case TEACHER: {
                                    Teacher.Position position = daoFactory.getTeacherDAO().getPositionForTeacher(user);
                                    System.out.println("position-"+position);
                                    session.setAttribute(POSITION, position);
									page = Config.getInstance().getValue(Config.TEACHER_PROFILE);
									break;
								}
								case ADMIN: {
									page = Config.getInstance().getValue(Config.ADMIN_PROFILE);
									break;
								}
							}
							System.out.println(">> page= " + page);
						} else {
							page = redirectToLoginPage(request);
						}
					} else {
						page = redirectToLoginPage(request);
					}
				} else {
					page = redirectToLoginPage(request);
				}
			} else {
				page = redirectToLoginPage(request);
			}
		} catch (Exception e) {
			request.setAttribute("error", ERROR_LOGIN);
			page = Config.getInstance().getValue(Config.ERROR_PAGE);
			logger.error(ERROR_LOGIN);
		}

		System.out.println(">> page exit = " + page);
//		request.getSession().setAttribute(PAGE, page);
		return page;
	}

	private String redirectToLoginPage(HttpServletRequest request) {
		request.setAttribute("error", ERROR_LOGIN);
		logger.error(ERROR_LOGIN);
		return Config.getInstance().getValue(Config.INDEX);
	}
}