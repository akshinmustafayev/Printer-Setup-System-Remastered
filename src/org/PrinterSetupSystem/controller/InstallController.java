package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.dao.InstallDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Represents Install Controller
@author Akshin A. Mustafayev
@version 1.0
*/
public class InstallController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	
    	Properties prop = InstallDao.LoadProperties();
		if(prop != null)
		{
			if(prop.getProperty("db.configured").trim().toString().equals("yes"))
			{
				request.getRequestDispatcher("/home").forward(request, response);
			}
		}
		else
		{
			request.getRequestDispatcher("/home").forward(request, response);
		}
		
        RequestDispatcher rd = request.getRequestDispatcher("/Install.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	
    	if(request.getParameter("button_installsystem") != null && 
    			request.getParameter("installdbip") != null && request.getParameter("installdbip") != "" &&
   			    request.getParameter("installdbuser") != null && request.getParameter("installdbuser") != "" &&
   			    request.getParameter("installdbpassword") != null)
        {
    		String installdbip = request.getParameter("installdbip");
    		String installdbuser = request.getParameter("installdbuser");
    		String installdbpassword = request.getParameter("installdbpassword");
    		Properties prop = InstallDao.LoadProperties();
			if(prop != null)
			{
				if(prop.getProperty("db.configured").trim().toString().equals("no"))
				{
		    		Boolean result = InstallDao.InstallSystem(installdbip, installdbuser, installdbpassword);
		    		if(result)
		    		{
		    			request.getRequestDispatcher("/home").forward(request, response);
		    		}
		    		else
		    		{
		    			request.setAttribute("ErrorInstallSystem", true);
		    		}
				}
				else
				{
					request.getRequestDispatcher("/home").forward(request, response);
				}
			}
			else
			{
				request.getRequestDispatcher("/home").forward(request, response);
			}
        }
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/Install.jsp"); 
        rd.include(request, response);
    }
}