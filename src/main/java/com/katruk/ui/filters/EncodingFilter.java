package com.katruk.ui.filters;

import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter extends BaseFilter implements Filter, PageAttribute {

  private static final Logger logger = Logger.getLogger(EncodingFilter.class);
  private String encoding;


  @Override
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
    encoding = filterConfig.getInitParameter("requestEncoding");
    if (encoding == null) {
      encoding = ISO_8859_1;
    }
    logger.info(String.format("Set encoding - %s", encoding));
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    request.setCharacterEncoding(encoding);
    response.setCharacterEncoding(encoding);
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    encoding = null;
  }
}
