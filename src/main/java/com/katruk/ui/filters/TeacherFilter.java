package com.katruk.ui.filters;

import com.katruk.dao.utils.Config;
import com.katruk.domain.entity.Human;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeacherFilter extends BaseFilter implements Filter, PageAttribute {

  private static final Logger logger = Logger.getLogger(TeacherFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpSession session = ((HttpServletRequest) request).getSession();
    Human user = (Human) session.getAttribute(USER);
    String url = ((HttpServletRequest) request).getRequestURL().toString();

    if ((user != null) && (user.getRole().equals(Human.Role.TEACHER))) {
      chain.doFilter(request, response);
      logger.info(String.format("Received a request from user %s to %s", user, url));
    } else {
      ((HttpServletResponse) response).sendRedirect(Config.getInstance().getValue(Config.INDEX));
      logger.info(
          String.format("Unauthorized access to: %s. Denied. Redirecting to login page...", url));
    }
  }

}
