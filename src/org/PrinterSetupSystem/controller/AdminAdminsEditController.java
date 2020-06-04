package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.dao.AdminAdminsEditDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Represents Admin Administrators Edit Controller
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminAdminsEditController extends HttpServlet 
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
    	
    	if(request.getParameter("adminid") != null &&
        		request.getParameter("adminid") != "")
        {
    		Integer adminid = 0;
        	try
        	{
        		adminid = Integer.parseInt(request.getParameter("adminid"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorAdminIDNotNumber", true); 
            	request.getRequestDispatcher("/adminadmins").forward(request, response);
        	}
        	
        	if(adminid == 1)
        	{
        		request.setAttribute("ErrorMainAdminCanNotBeEdited", true); 
            	request.getRequestDispatcher("/adminadmins").forward(request, response);
        	}
        	
        	request.setAttribute("adminid", adminid);
        	
        	RequestDispatcher rd = request.getRequestDispatcher("/AdminAdminsEdit.jsp"); 
	        rd.include(request, response);
        }
    	else
    	{
    		request.setAttribute("ErrorAdminIDNotNumber", true); 
        	request.getRequestDispatcher("/adminadmins").forward(request, response);
    	}
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_saveadmin") != null && 
				request.getParameter("editadminpassword") != null &&
				request.getParameter("editadminid") != null)
		{
    		String editadminpassword = request.getParameter("editadminpassword");
    		Integer editadminid = Integer.parseInt(request.getParameter("editadminid"));
    		
    		if(editadminid == 1)
    		{
    			request.setAttribute("ErrorMainAdminCanNotBeEdited", true); 
            	request.getRequestDispatcher("/adminadmins").forward(request, response);
    		}
    		
    		Boolean result = AdminAdminsEditDao.UpdatePassword(editadminid, editadminpassword);
	        if(result)
	        {
	        	request.setAttribute("AdminSaved", true); 
	        	response.sendRedirect(request.getContextPath() + "/adminadminsedit?adminid=" + editadminid);
	        }
	        else
	        {
	    	    request.setAttribute("adminid", editadminid);
	    	    request.setAttribute("ErrorEditAdminSave", true);
	        }

	    	RequestDispatcher rd = request.getRequestDispatcher("/AdminAdminsEdit.jsp"); 
	        rd.include(request, response);
		}
    	else
    	{
    		request.setAttribute("ErrorAdminIDNotNumber", true); 
        	request.getRequestDispatcher("/adminadmins").forward(request, response);
    	}
    }
}