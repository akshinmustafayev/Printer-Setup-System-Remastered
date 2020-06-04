package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.dao.AdminPrinterTypesDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Represents Admin Printers Type Controller
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminPrinterTypesController extends HttpServlet 
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
    	
    	ArrayList<PrinterType> printerstypes = AdminPrinterTypesDao.GetPrintersTypes();
    	request.setAttribute("printerstypes", printerstypes);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminPrinterTypes.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_printerstype") != null && request.getParameter("deleteprinterstypeid") != null)
        {
    		Integer typeid = 0;
        	try
        	{
        		typeid = Integer.parseInt(request.getParameter("deleteprinterstypeid"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorTypeIDNotNumber", true); 
        	}

        	if(typeid == 1)
        	{
        		request.setAttribute("ErrorNoneTypeNotBeDeleted", true);
        	}
        	else
        	{
	        	Boolean result = AdminPrinterTypesDao.DeletePrintersType(typeid);
	        	if(result)
	        	{
	        		request.setAttribute("PrinterTypeDeleted", true); 
	        	}
	        	else
	        	{
	        		request.setAttribute("ErrorPrinterTypeDelete", true); 
	        	}
        	}
        }
    	if(request.getParameter("button_createprintertype") != null && request.getParameter("newprintertype") != null)
        {
    		String newprintertype = request.getParameter("newprintertype");

	        Boolean result = AdminPrinterTypesDao.CreatePrintersType(newprintertype);
	        if(result)
	        {
	        	request.setAttribute("NewPrinterTypeCreateSuccess", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorNewPrinterTypeCreate", true); 
	        }
        }
    	
    	ArrayList<PrinterType> printerstypes = AdminPrinterTypesDao.GetPrintersTypes();
    	request.setAttribute("printerstypes", printerstypes);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminPrinterTypes.jsp"); 
        rd.include(request, response);
    }
}