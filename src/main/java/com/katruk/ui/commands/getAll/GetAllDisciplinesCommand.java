package com.katruk.ui.commands.getAll;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.Discipline;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllDisciplinesCommand implements ICommand, PageAttribute {

	private static final Logger logger = Logger.getLogger(GetAllDisciplinesCommand.class);
	private String page;
    private DaoFactory daoFactory = DaoFactory.getDAOFactory();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Discipline> disciplineList = daoFactory.getDisciplineDAO().getAll();
            request.setAttribute(DISCIPLINE_LIST, disciplineList);
            page = Config.getInstance().getValue(Config.DISCIPLINES);
            logger.info(String.format("get all disciplines = %d", disciplineList.size()));
        } catch (Exception e) {
            page = Config.getInstance().getValue(Config.ERROR_PAGE);
            logger.error("Unable get all disciplines", e);
        }
        return page;
	}
}
