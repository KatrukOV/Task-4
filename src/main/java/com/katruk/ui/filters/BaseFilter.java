package com.katruk.ui.filters;

import com.katruk.ui.PageAttribute;

import javax.servlet.*;
import java.io.IOException;

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
