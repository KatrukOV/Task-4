package com.katruk.domain.commands;

import com.katruk.dao.utils.Config;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand implements ICommand {

  private static final String ERROR_LOGIN = "Unknown Command";
  private static final Logger logger = Logger.getLogger(UnknownCommand.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {

    return Config.getInstance().getValue(Config.ERROR_PAGE);
//		todo
//		logger.error(ERROR_LOGIN);
  }
}
