package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.dao.UserSettingsDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSettingsController extends HttpServlet 
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
    	System.out.println("Enter doGet for User Settings Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/UserSettings.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	System.out.println("Enter doPost for User Settings Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_changepassword") != null && 
				request.getParameter("newpassword") != null &&
				request.getParameter("newpasswordconfirm") != null)
		{
    		String newpassword = request.getParameter("newpassword").toString();
    		String newpasswordconfirm = request.getParameter("newpasswordconfirm").toString();
    		
    		System.out.println(newpassword);
    		System.out.println(newpasswordconfirm);
    		
    		if(!newpassword.equals(newpasswordconfirm))
    		{
    			request.setAttribute("ErrorPasswordsAreNotEqual", true); 
    		}
    		else
    		{
    			HttpSession session = request.getSession();
    	    	String login = session.getAttribute("login").toString();
    	    	String csession = session.getAttribute("session").toString();
    	    	
    	    	Boolean result = UserSettingsDao.ChangeUserPassword(newpassword, newpasswordconfirm, login, csession);
    	    	if(result)
    	    	{
    	    		request.setAttribute("PasswordChanged", true); 
    	    	}
    	    	else
    	    	{
    	    		request.setAttribute("ErrorPasswordChange", true); 
    	    	}
    		}
		}
    	
        RequestDispatcher rd = request.getRequestDispatcher("/UserSettings.jsp"); 
        rd.include(request, response);
    }
}