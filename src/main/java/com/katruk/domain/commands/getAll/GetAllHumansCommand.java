package com.katruk.domain.commands.getAll;


import com.katruk.dao.utils.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.entity.Human;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
