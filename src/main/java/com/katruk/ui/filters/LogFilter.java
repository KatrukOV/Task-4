package com.katruk.ui.filters;

import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter extends BaseFilter implements Filter, PageAttribute {

  private static final Logger logger = Logger.getLogger(LogFilter.class);

  @Override
  public void init(FilterConfig config) {
    this.filterConfig = config;
    //Get init parameter
    String testParam = config.getInitParameter("test-param");

    //Print the init parameter
    System.out.println("Test Param: " + testParam);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;

    //Get the IP address of client machine.
    String ipAddress = httpRequest.getRemoteAddr();

    //Log the IP address and current timestamp.
    logger.info(String.format("IP=%s Time=%s", ipAddress, new Date().toString()));

//		System.out.println(String.format("IP=%s Time=%s", ipAddress, new Date().toString()));

    chain.doFilter(request, response);
  }

}
