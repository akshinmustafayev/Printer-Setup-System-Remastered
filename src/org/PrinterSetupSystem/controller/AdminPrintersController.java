package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.dao.AdminPrintersDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPrintersController extends HttpServlet 
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
    	System.out.println("Enter doGet for Admin Printers Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	ArrayList<Printer> printers = AdminPrintersDao.GetPrinters();
    	request.setAttribute("printers", printers);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminPrinters.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	System.out.println("Enter doPost for Admin Printers Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	
    	if(request.getParameter("button_deleteprinter") != null && request.getParameter("deleteprinterid") != null)
        {
    		Integer printerid = 0;
        	try
        	{
        		printerid = Integer.parseInt(request.getParameter("deleteprinterid"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorPrinterIDNotNumber", true); 
        	}

        	Boolean result = AdminPrintersDao.DeletePrinter(printerid);
	        if(result)
	        {
	        	request.setAttribute("PrinterDeleted", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorPrinterDelete", true); 
	        }
        }
    	
    	/*
    	if(request.getParameter("button_createbranch") != null && request.getParameter("newbranchname") != null &&
    			request.getParameter("newbranchdescription") != null)
        {
    		String newbranchname = request.getParameter("newbranchname");
    		String newbranchdescription = request.getParameter("newbranchdescription");
    		Part newbranchimage = request.getPart("newbranchimage");
    		
	        Boolean result = AdminBranchesDao.CreateBranch(newbranchname, newbranchdescription, newbranchimage);
	        if(result)
	        {
	        	request.setAttribute("NewBranchCreateSuccess", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorNewBranchCreate", true); 
	        }
        }
		*/
    	
    	ArrayList<Printer> printers = AdminPrintersDao.GetPrinters();
    	request.setAttribute("printers", printers);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminPrinters.jsp"); 
        rd.include(request, response);
    }
}