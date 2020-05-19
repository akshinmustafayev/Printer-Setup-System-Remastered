package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.dao.PrinterDao;
import org.PrinterSetupSystem.interfaces.IPrinterShow;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrinterController extends HttpServlet 
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
    	System.out.println("Enter doGet for Printer Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	
    	if(request.getParameter("id") != null &&
        		request.getParameter("id") != "")
        {
    		Integer printerID = 0;
        	try
        	{
        		printerID = Integer.parseInt(request.getParameter("id"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorPrinterNotNumber", true); 
            	request.getRequestDispatcher("/home").forward(request, response);
        	}
        	
        	IPrinterShow printer = new PrinterDao();
	    	Printer _printer = printer.GetPrinter(printerID);
	    	
	    	if(_printer != null)
        	{
		    	PrinterType _printertype = printer.GetPrinterType();
		    	Branch _printerbranch = printer.GetPrinterBranch();
	    		request.setAttribute("printer", _printer);
	    		request.setAttribute("printertype", _printertype);
	    		request.setAttribute("printerbranch", _printerbranch);
        	}
        	else
        	{
        		request.setAttribute("ErrorPrinterNotFound", true); 
            	request.getRequestDispatcher("/branch?id=1").forward(request, response);
        	}
        }
        else
        {
        	request.setAttribute("ErrorEmptyPrinterID", true); 
        	request.getRequestDispatcher("/branch?id=1").forward(request, response);
        }
    	
        RequestDispatcher rd = request.getRequestDispatcher("/Printer.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	System.out.println("Enter doPost for Printer Controller");
    }
}