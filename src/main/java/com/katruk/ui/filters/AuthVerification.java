package com.katruk.ui.filters;

import com.katruk.dao.utils.Config;
import com.katruk.domain.entity.Human;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

//    System.out.println(">> auth filter  user=" + user + " user1=" + user1 + " url=" + url);

    if (user != null) {
      chain.doFilter(request, response);
      logger.info(String.format("Received a request from user %s to %s", user, url));
    } else {
      if (url.contains("registration") || request.getParameter(LOGIN) != null) {
        chain.doFilter(request, response);
      } else {
        ((HttpServletResponse) response).sendRedirect(Config.getInstance().getValue(Config.INDEX));
        logger.info(
            String.format("Unauthorized access to %s. Denied. Redirecting to login page...", url));
      }
    }
  }
}
