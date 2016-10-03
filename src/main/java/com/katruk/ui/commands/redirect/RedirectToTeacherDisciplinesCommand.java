package com.katruk.ui.commands.redirect;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.Discipline;
import com.katruk.domen.entity.human.Human;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RedirectToTeacherDisciplinesCommand implements ICommand, PageAttribute {

	private static final Logger logger = Logger.getLogger(RedirectToTeacherDisciplinesCommand.class);
	private String page;
    private DaoFactory daoFactory = DaoFactory.getDAOFactory();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {

            Human human1 = (Human) request.getSession().getAttribute(USER);
            Human human = daoFactory.getHumanDAO().get((String) request.getSession().getAttribute(LOGIN));

            System.out.println("!!! teacher session="+human1);
            System.out.println("!!! teacher dao="+human);

			List<Discipline> disciplineList = daoFactory.getDisciplineDAO().getAllDisciplinesOfTeacher(human);

			if (disciplineList != null) {
				request.setAttribute(DISCIPLINE_LIST, disciplineList);
				logger.info(String.format("show all teacher Disciplines"));
			} /*else throw new CommandException("Can't get any discipline");*/

            page = Config.getInstance().getValue(Config.TEACHER_DISCIPLINES);
            System.out.println(">>> RedirectToTeacherDisciplines ok=" + page);
		} catch (Exception e) {
			page = Config.getInstance().getValue(Config.ERROR_PAGE);
			logger.error("Unable to show all teacher disciplines", e);
		}
		return page;
	}
}
