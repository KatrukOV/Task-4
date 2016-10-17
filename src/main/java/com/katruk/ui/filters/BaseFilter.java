package com.katruk.ui.filters;

import com.katruk.ui.PageAttribute;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BaseFilter implements Filter, PageAttribute {

  protected FilterConfig filterConfig = null;

  @Override
  public void init(FilterConfig config) {
    this.filterConfig = config;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    this.filterConfig = null;
  }
}
