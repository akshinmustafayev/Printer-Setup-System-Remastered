package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.dao.AdminOtherDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Represents Admin Other Controller
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminOtherController extends HttpServlet 
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
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminOther.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_resetviewsallprinters") != null)
		{
    		Boolean result = AdminOtherDao.ResetAllPrinterViews();
	        if(result)
	        {
	        	request.setAttribute("SuccessResetPrintersViews", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorResetPrintersViews", true); 
	        }
		}
    	
    	if(request.getParameter("button_cleanallprinters") != null)
		{
    		Boolean result = AdminOtherDao.CleanAllPrinters();
	        if(result)
	        {
	        	request.setAttribute("SuccessCleanPrinters", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorCleanPrinters", true); 
	        }
		}
    	
    	if(request.getParameter("button_cleanallbranches") != null)
		{
    		Boolean result = AdminOtherDao.CleanAllBranches();
	        if(result)
	        {
	        	request.setAttribute("SuccessCleanBranches", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorCleanBranches", true); 
	        }
		}
    	
    	if(request.getParameter("button_cleanallprinterstypes") != null)
		{
    		Boolean result = AdminOtherDao.CleanAllPrinterTypes();
	        if(result)
	        {
	        	request.setAttribute("SuccessCleanPrintersTypes", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorCleanPrintersTypes", true); 
	        }
		}
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminOther.jsp"); 
        rd.include(request, response);
    }
}