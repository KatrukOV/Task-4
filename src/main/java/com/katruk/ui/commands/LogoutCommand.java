package com.katruk.ui.commands;

import com.katruk.dao.utils.Config;
import com.katruk.ui.PageAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements ICommand, PageAttribute {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(">>> log out userLog=" + request.getSession().getAttribute(LOGIN));

		request.getSession().removeAttribute(LOGIN);
		request.getSession().invalidate();

		return Config.getInstance().getValue(Config.INDEX);
	}
}
