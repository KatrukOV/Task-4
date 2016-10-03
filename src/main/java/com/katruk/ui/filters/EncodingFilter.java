package com.katruk.ui.filters;

import com.katruk.ui.PageAttribute;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter extends BaseFilter implements Filter, PageAttribute {

	private static final Logger logger = Logger.getLogger(EncodingFilter.class);
	private String encoding;


	@Override
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("requestEncoding");
		if (encoding == null)
			encoding = ISO_8859_1;
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
