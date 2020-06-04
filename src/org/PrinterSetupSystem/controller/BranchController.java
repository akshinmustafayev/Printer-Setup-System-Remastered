package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.dao.BranchDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Represents Branch Controller
@author Akshin A. Mustafayev
@version 1.0
*/
public class BranchController extends HttpServlet 
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
    	
    	if(request.getParameter("id") != null &&
        		request.getParameter("id") != "")
        {
    		Integer branchID = 0;
        	try
        	{
        		branchID = Integer.parseInt(request.getParameter("id"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorBranchNotNumber", true); 
            	request.getRequestDispatcher("/home").forward(request, response);
        	}
        	
	    	ArrayList<Printer> printers = BranchDao.GetPrinters(branchID);
	    	request.setAttribute("printers", printers);
	    	
	    	Branch branch = BranchDao.GetBranchByID(branchID);
	    	if(branch != null)
	    	{
	    		request.setAttribute("branch", branch);
	    		request.setAttribute("branchid", branchID);
	    	}
	    	else
	    	{
	    		request.setAttribute("ErrorBranchNotFound", true); 
	        	request.getRequestDispatcher("/home").forward(request, response);
	    	}
        }
        else
        {
        	request.setAttribute("ErrorEmptyBranchID", true); 
        	request.getRequestDispatcher("/home").forward(request, response);
        }
    	
        RequestDispatcher rd = request.getRequestDispatcher("/Branch.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/Branch.jsp"); 
        rd.include(request, response);
    }
}