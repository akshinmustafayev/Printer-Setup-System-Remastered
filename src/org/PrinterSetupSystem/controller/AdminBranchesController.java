package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.dao.AdminBranchesDao;
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

/** Represents Admin Branches Controller
@author Akshin A. Mustafayev
@version 1.0
*/
@MultipartConfig
public class AdminBranchesController extends HttpServlet 
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
    	
    	ArrayList<Branch> branches = AdminBranchesDao.GetBranches();
    	request.setAttribute("branches", branches);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminBranches.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_deletebranch") != null && request.getParameter("deletebranchid") != null)
        {
    		Integer branchid = 0;
        	try
        	{
        		branchid = Integer.parseInt(request.getParameter("deletebranchid"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorBranchIDNotNumber", true); 
        	}

        	if(branchid == 1)
        	{
        		request.setAttribute("ErrorMainBranchCanNotBeDeleted", true);
        	}
        	else
        	{
	        	Boolean result = AdminBranchesDao.DeleteBranch(branchid);
	        	if(result)
	        	{
	        		request.setAttribute("BranchDeleted", true); 
	        	}
	        	else
	        	{
	        		request.setAttribute("ErrorBranchDelete", true); 
	        	}
        	}
        }
    	
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

    	ArrayList<Branch> branches = AdminBranchesDao.GetBranches();
    	request.setAttribute("branches", branches);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminBranches.jsp"); 
        rd.include(request, response);
    }
}