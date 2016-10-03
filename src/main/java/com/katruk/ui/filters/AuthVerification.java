package com.katruk.ui.filters;

import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.human.Human;
import com.katruk.ui.PageAttribute;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthVerification extends BaseFilter implements Filter, PageAttribute {

	private static final Logger logger = Logger.getLogger(AuthVerification.class);

	@Override
	public void init(FilterConfig filterConfig) {
		System.out.println(">>> auth init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Human user = (Human) ((HttpServletRequest) request).getSession().getAttribute(USER);
		Human user1 = (Human) (request).getAttribute(USER);

		String url = ((HttpServletRequest) request).getRequestURL().toString();

		System.out.println(">> auth filter  user=" + user + " user1=" + user1 + " url=" + url);

		if (user != null) {
			chain.doFilter(request, response);
			logger.info(String.format("Received a request from user %s to %s", user, url));
		} else {
			System.out.println(">>> filter req login=" + request.getParameter(LOGIN));
			System.out.println(">>> filter url=" + url);
			if (url.contains("/reg/registration.jsp") || request.getParameter(LOGIN) != null) {
				chain.doFilter(request, response);
				System.out.println(">>>>>>>>>>> 1 if");
			} else {
				((HttpServletResponse) response).sendRedirect(Config.getInstance().getValue(Config.INDEX));
				System.out.println(">>>>>>>>>>> else");
				logger.info(String.format("Unauthorized access to %s. Denied. Redirecting to login page...", url));
			}
		}
	}
}
