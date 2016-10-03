package com.katruk.ui.controllers;

import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet implements PageAttribute {

	private static final DispatcherHelper helper = DispatcherHelper.getInstance();
	private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

	public DispatcherServlet() {
		super();
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {

		ICommand command = helper.getCommand(request);
		String pageURL = command.execute(request, response);
        System.out.println(">>>>>>>>>>>> servlet pageURL="+pageURL);

//        System.out.println(">>>>>>>>>>>> servlet pageURL="+pageURL);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageURL);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("dispatcher error", e);
		}
	}
}
