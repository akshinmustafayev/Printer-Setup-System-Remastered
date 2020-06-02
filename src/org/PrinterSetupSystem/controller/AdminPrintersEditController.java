package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.dao.AdminPrintersEditDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class AdminPrintersEditController extends HttpServlet 
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
    	System.out.println("Enter doGet for Admin Printers Edit Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("printerid") != null &&
        		request.getParameter("printerid") != "")
        {
    		Integer printerid = 0;
        	try
        	{
        		printerid = Integer.parseInt(request.getParameter("printerid"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorPrinterNotNumber", true); 
            	request.getRequestDispatcher("/adminprinters").forward(request, response);
        	}
        	
        	Printer printer = AdminPrintersEditDao.GetPrinter(printerid);
        	if(printer != null)
        	{
        		ArrayList<Branch> branches = AdminPrintersEditDao.GetBranches();
    	    	ArrayList<PrinterType> printerstypes = AdminPrintersEditDao.GetPrinterTypes();
    	    	request.setAttribute("branches", branches);
    	    	request.setAttribute("printerstypes", printerstypes);
    	    	request.setAttribute("printer", printer);
    	    	
    	    	RequestDispatcher rd = request.getRequestDispatcher("/AdminPrintersEdit.jsp"); 
    	        rd.include(request, response);
        	}
        	else
        	{
        		request.setAttribute("ErrorPrinterNotFound", true); 
            	request.getRequestDispatcher("/adminprinters").forward(request, response);
        	}
        }
    	else
    	{
    		request.setAttribute("ErrorPrinterNotNumber", true); 
        	request.getRequestDispatcher("/adminprinters").forward(request, response);
    	}
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	System.out.println("Enter doPost for Admin Printers Edit Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_saveprinter") != null && 
				request.getParameter("editprintername") != null && 
				request.getParameter("editprinterbranch") != null && 
				request.getParameter("editprintertype") != null)
		{
    		String editprintername = request.getParameter("editprintername");
    		Integer editprinterbranch = Integer.parseInt(request.getParameter("editprinterbranch"));
    		Integer editprintertype = Integer.parseInt(request.getParameter("editprintertype"));
    		Integer editprinterid = Integer.parseInt(request.getParameter("editprinterid"));
    		String editprinterdescription = "None";
    		String editprinterip = "";
    		String editprintervendor = "Undefined";
    		String editprintersharename = "None";
    		String editprinterlocation = "None";
    		String editprinterimagenull = request.getParameter("editprinterimagenull");
    		Part editprinterimage = request.getPart("editprinterimage");
    		
    		if(request.getParameter("editprinterdescription") != null)
    			editprinterdescription = request.getParameter("editprinterdescription");
    		if(request.getParameter("editprinterip") != null)
    			editprinterip = request.getParameter("editprinterip");
    		if(request.getParameter("editprintervendor") != null)
    			editprintervendor = request.getParameter("editprintervendor");
    		if(request.getParameter("editprintersharename") != null)
    			editprintersharename = request.getParameter("editprintersharename");
    		if(request.getParameter("editprinterlocation") != null)
    			editprinterlocation = request.getParameter("editprinterlocation");
    		
    		Printer printer = new Printer();
    		printer.SetId(editprinterid);
    		printer.SetName(editprintername);
    		printer.SetBranchId(editprinterbranch);
    		printer.SetPrinterTypeId(editprintertype);
    		printer.SetDescription(editprinterdescription);
    		printer.SetIp(editprinterip);
    		printer.SetVendor(editprintervendor);
    		printer.SetServerShareName(editprintersharename);
    		printer.SetLocation(editprinterlocation);
    		
    		Boolean result = AdminPrintersEditDao.SavePrinter(printer, editprinterimage, editprinterimagenull);
	        if(result)
	        {
	        	request.setAttribute("PrinterSaved", true); 
	        	response.sendRedirect(request.getContextPath() + "/adminprintersedit?printerid=" + editprinterid);
	        }
	        else
	        {
	        	Printer _printer = AdminPrintersEditDao.GetPrinter(editprinterid);
	        	if(_printer != null)
	        	{
	        		ArrayList<Branch> branches = AdminPrintersEditDao.GetBranches();
	    	    	ArrayList<PrinterType> printerstypes = AdminPrintersEditDao.GetPrinterTypes();
	    	    	request.setAttribute("branches", branches);
	    	    	request.setAttribute("printerstypes", printerstypes);
	    	    	request.setAttribute("printer", _printer);
	    	    	request.setAttribute("ErrorEditPrinterSave", true); 
	        	}
	        	else
	        	{
	        		request.setAttribute("ErrorPrinterNotFound", true); 
	            	request.getRequestDispatcher("/adminprinters").forward(request, response);
	        	}
	        }

	    	RequestDispatcher rd = request.getRequestDispatcher("/AdminPrintersEdit.jsp"); 
	        rd.include(request, response);
		}
    	else
    	{
    		request.setAttribute("ErrorPrinterNotNumber", true); 
        	request.getRequestDispatcher("/adminprinters").forward(request, response);
    	}
    }
}