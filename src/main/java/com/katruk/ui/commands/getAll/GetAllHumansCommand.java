package com.katruk.ui.commands.getAll;


import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.human.Human;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class GetAllHumansCommand implements ICommand, PageAttribute {

	private static final Logger logger = Logger.getLogger(GetAllHumansCommand.class);

	private String page;
    private DaoFactory daoFactory = DaoFactory.getDAOFactory();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {

            List<Human> humanList = daoFactory.getHumanDAO().getAll();
            request.setAttribute(HUMAN_LIST, humanList);

			page = Config.getInstance().getValue(Config.ALL_HUMANS);
			logger.info(String.format("get all humans = %d", humanList.size()));
		} catch (Exception e) {
			page = Config.getInstance().getValue(Config.ERROR_PAGE);
			logger.error("Unable get all humans", e);
		}
		return page;
	}
}
