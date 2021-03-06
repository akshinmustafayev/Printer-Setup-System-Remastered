package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.dao.AdminPrintersCreateDao;
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

/** Represents Admin Printers Create Controller
@author Akshin A. Mustafayev
@version 1.0
*/
@MultipartConfig
public class AdminPrintersCreateController extends HttpServlet 
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
    	
    	ArrayList<Branch> branches = AdminPrintersCreateDao.GetBranches();
    	ArrayList<PrinterType> printerstypes = AdminPrintersCreateDao.GetPrinterTypes();
    	request.setAttribute("branches", branches);
    	request.setAttribute("printerstypes", printerstypes);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminPrintersCreate.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_createprinter") != null && 
				request.getParameter("newprintername") != null && 
				request.getParameter("newprinterbranch") != null && 
				request.getParameter("newprintertype") != null)
		{
    		String newprintername = request.getParameter("newprintername");
    		Integer newprinterbranch = Integer.parseInt(request.getParameter("newprinterbranch"));
    		Integer newprintertype = Integer.parseInt(request.getParameter("newprintertype"));
    		String newprinterdescription = "None";
    		String newprinterip = "";
    		String newprintervendor = "Undefined";
    		String newprintersharename = "None";
    		String newprinterlocation = "None";
    		String newprintercustomfield1 = "None";
    		Part newprinterimage = request.getPart("newprinterimage");
    		
    		if(request.getParameter("newprinterdescription") != null)
    			newprinterdescription = request.getParameter("newprinterdescription");
    		if(request.getParameter("newprinterip") != null)
    			newprinterip = request.getParameter("newprinterip");
    		if(request.getParameter("newprintervendor") != null)
    			newprintervendor = request.getParameter("newprintervendor");
    		if(request.getParameter("newprintersharename") != null)
    			newprintersharename = request.getParameter("newprintersharename");
    		if(request.getParameter("newprinterlocation") != null)
    			newprinterlocation = request.getParameter("newprinterlocation");
    		if(request.getParameter("newprintercustomfield1") != null)
    			newprintercustomfield1 = request.getParameter("newprintercustomfield1");
    		
    		Printer printer = new Printer();
    		printer.SetName(newprintername);
    		printer.SetBranchId(newprinterbranch);
    		printer.SetPrinterTypeId(newprintertype);
    		printer.SetDescription(newprinterdescription);
    		printer.SetIp(newprinterip);
    		printer.SetVendor(newprintervendor);
    		printer.SetServerShareName(newprintersharename);
    		printer.SetLocation(newprinterlocation);
    		printer.SetCustomField1(newprintercustomfield1);
    		
    		Boolean result = AdminPrintersCreateDao.CreatePrinter(printer, newprinterimage);
	        if(result)
	        {
	        	request.setAttribute("NewPrinterCreateSuccess", true);
	        	response.sendRedirect(request.getContextPath() + "/adminprinters");
	        }
	        else
	        {
	        	ArrayList<Branch> branches = AdminPrintersCreateDao.GetBranches();
	        	ArrayList<PrinterType> printerstypes = AdminPrintersCreateDao.GetPrinterTypes();
	        	request.setAttribute("branches", branches);
	        	request.setAttribute("printerstypes", printerstypes);
	        	request.setAttribute("printer", printer);
	        	request.setAttribute("ErrorNewPrinterCreate", true);
	        	
	        	RequestDispatcher rd = request.getRequestDispatcher("/AdminPrintersCreate.jsp"); 
		        rd.include(request, response);
	        }
		}
    	else
    	{
    		ArrayList<Branch> branches = AdminPrintersCreateDao.GetBranches();
        	ArrayList<PrinterType> printerstypes = AdminPrintersCreateDao.GetPrinterTypes();
        	request.setAttribute("branches", branches);
        	request.setAttribute("printerstypes", printerstypes);
        	
	        RequestDispatcher rd = request.getRequestDispatcher("/AdminPrintersCreate.jsp"); 
	        rd.include(request, response);
    	}
    }
}