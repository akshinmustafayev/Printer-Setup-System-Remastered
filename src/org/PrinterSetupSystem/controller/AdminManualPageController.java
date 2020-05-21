package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.dao.AdminManualPageDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminManualPageController extends HttpServlet 
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
    	System.out.println("Enter doGet for Admin Manual Page Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	String helpmanual = AdminManualPageDao.GetHelpManual();
    	request.setAttribute("helpmanual", helpmanual);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminManualPage.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	System.out.println("Enter doPost for Admin Manual Page Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("savemanual_button") != null && request.getParameter("adminmanual") != null)
        {
    		String helpmanual = request.getParameter("adminmanual");
    		Boolean result = AdminManualPageDao.SetHelpManual(helpmanual);
    		if(result)
    		{
    			request.setAttribute("ManualPageSaved", true); 
    		}
    		else
    		{
    			request.setAttribute("ManualPageSaveError", true);
    		}
        }
    	
    	String helpmanual = AdminManualPageDao.GetHelpManual();
    	request.setAttribute("helpmanual", helpmanual);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminManualPage.jsp"); 
        rd.include(request, response);
    }
}