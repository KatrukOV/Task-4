package com.katruk.ui.commands.set;


import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.human.Human;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SetRoleCommand implements ICommand, PageAttribute {

	private static final Logger logger = Logger.getLogger(SetRoleCommand.class);

	private String page;
	private DaoFactory daoFactory = DaoFactory.getDAOFactory();


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Human.Role role = Human.Role.valueOf(request.getParameter(ROLE));
            System.out.println(">>>>>>>>>>>> role= " + role);

            Human human = daoFactory.getHumanDAO().get(request.getParameter(LOGIN));

            System.out.println(">>>>>>>>>>>> human= " + human);

            daoFactory.getHumanDAO().setRole(human, role);

            List<Human> humanList = daoFactory.getHumanDAO().getAll();
            request.setAttribute("humanList", humanList);

			page = Config.getInstance().getValue(Config.ALL_HUMANS);

			logger.info(String.format("set role=%s for human= %s", role, human));
		} catch (Exception e) {
			page = Config.getInstance().getValue(Config.ERROR_PAGE);
			logger.error("Unable set role for human", e);
		}
		return page;
	}
}

