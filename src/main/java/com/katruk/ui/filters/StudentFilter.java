package com.katruk.ui.filters;

import com.katruk.dao.utils.Config;
import com.katruk.domain.entity.human.Human;
import com.katruk.ui.PageAttribute;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentFilter extends BaseFilter implements Filter, PageAttribute {

	private static final Logger logger = Logger.getLogger(StudentFilter.class);

	@Override
	public void init(FilterConfig config) {
		this.filterConfig = config;
		System.out.println(">> student filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		Human user = (Human) session.getAttribute(USER);
		String url = ((HttpServletRequest) request).getRequestURL().toString();
		System.out.println(">>>>>stud filter user =" + user);
		System.out.println(">>>>>stud filter url =" + url);

		if ((user != null) && (user.getRole().equals(Human.Role.STUDENT))) {
			chain.doFilter(request, response);
			logger.info(String.format("Received a request from user %s to %s", user, url));
			System.out.println(">>>>>>>>>>> stud filter if ");
		} else {
			System.out.println(">>>>>>>>>>> stud filter else ");
			((HttpServletResponse) response).sendRedirect(Config.getInstance().getValue(Config.INDEX));
			logger.info(String.format("Unauthorized access to: %s. Denied. Redirecting to login page...", url));
		}
	}

	@Override
	public void destroy() {
		System.out.println(">> student filter destroy");
		this.filterConfig = null;
	}
}
