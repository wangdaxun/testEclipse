package com.qst.itoffer.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ApplicantAuthorityFilter
 */
@WebFilter(
		urlPatterns = {"/applicant/*" },
		servletNames = {"com.qst.itoffer.servlet.ResumeBasicinfoServlet",
				"com.qst.itoffer.servlet.ResumePicUploadServlet",
				"com.qst.itoffer.servlet.JobApplyServlet"},
		initParams = {@WebInitParam(name = "loginPage", value = "login.jsp")},
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD}
		)
public class ApplicantAuthorityFilter implements Filter {
	private String loginPage = "login.jsp";
    /**
     * Default constructor. 
     */
    public ApplicantAuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		this.loginPage = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if(session.getAttribute("SESSION_APPLICANT") == null) {
			String requestPath = req.getRequestURI();
			if(req.getQueryString() != null) {
				requestPath += "?" +req.getQueryString();
			}
			req.setAttribute("requestPath", requestPath);
			request.getRequestDispatcher("/" + loginPage).forward(request, response);
			return;
		}else {
			chain.doFilter(request, response);		
		}	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		loginPage = fConfig.getInitParameter("loginPage");
		if(null == loginPage) {
			loginPage = "login.jsp";
		}
	}

}
