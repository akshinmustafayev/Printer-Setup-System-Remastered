package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.User;
import org.PrinterSetupSystem.dao.AdminAdminsDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Represents Admin Administrators Controller
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminAdminsController extends HttpServlet 
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
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	ArrayList<User> administrators = AdminAdminsDao.GetAdministrators();
    	request.setAttribute("administrators", administrators);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminAdmins.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_deleteadmin") != null && request.getParameter("deleteadminid") != null)
        {
    		Integer adminid = 0;
        	try
        	{
        		adminid = Integer.parseInt(request.getParameter("deleteadminid"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorAdminIDNotNumber", true); 
        	}

        	if(adminid == 1)
        	{
        		request.setAttribute("ErrorMainAdminCanNotBeDeleted", true);
        	}
        	else
        	{
	        	Boolean result = AdminAdminsDao.DeleteAdministrator(adminid);
	        	if(result)
	        	{
	        		request.setAttribute("AdminDeleted", true); 
	        	}
	        	else
	        	{
	        		request.setAttribute("ErrorAdminDelete", true); 
	        	}
        	}
        }
    	
    	if(request.getParameter("button_createadmin") != null && request.getParameter("newadminlogin") != null &&
    			request.getParameter("newadminfullname") != null && request.getParameter("newadminpassword") != null)
        {
    		String newadminlogin = request.getParameter("newadminlogin");
    		String newadminfullname = request.getParameter("newadminfullname");
    		String newadminpassword = request.getParameter("newadminpassword");

	        Boolean result = AdminAdminsDao.CreateAdministrator(newadminlogin, newadminfullname, newadminpassword);
	        if(result)
	        {
	        	request.setAttribute("NewAdminCreateSuccess", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorNewAdminCreate", true); 
	        }
        }
    	
    	ArrayList<User> administrators = AdminAdminsDao.GetAdministrators();
    	request.setAttribute("administrators", administrators);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminAdmins.jsp"); 
        rd.include(request, response);
    }
}