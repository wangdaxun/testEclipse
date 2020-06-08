package com.qst.itoffer.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.qst.itoffer.dao.CompanyDAO;

/**
 * Application Lifecycle Listener implementation class CompanyVIewCountListener
 *
 */
@WebListener
public class CompanyVIewCountListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public CompanyVIewCountListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    	HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
    	String requestURI = request.getRequestURI();
    	String queryString = request.getQueryString() == null ? "" :request.getQueryString();
    	// 判断是否是向企业处理Servlet发出的请求，并且含有表示企业信息查看的请求参数
    	if(requestURI.indexOf("CompanyServlet") >= 0 && (queryString.indexOf("select") >= 0)) {
    		int id = Integer.parseInt(queryString.substring(queryString.lastIndexOf('=') + 1));
    		// 更新此企业信息的浏览次数
    		CompanyDAO companyDao = new CompanyDAO();
    		companyDao.updateCompanyViewCount(id);
    	}
    }
	
}
