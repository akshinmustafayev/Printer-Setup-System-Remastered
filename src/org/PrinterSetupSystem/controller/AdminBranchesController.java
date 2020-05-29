package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.dao.AdminBranchesDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	System.out.println("Enter doGet for Admin Branches Controller");
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
    	System.out.println("Enter doPost for Admin Branches Controller");
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
    	
    	/*
    	if(request.getParameter("button_createadmin") != null && request.getParameter("newadminlogin") != null &&
    			request.getParameter("newadminfullname") != null && request.getParameter("newadminpassword") != null)
        {
    		String newadminlogin = request.getParameter("newadminlogin");
    		String newadminfullname = request.getParameter("newadminfullname");
    		String newadminpassword = request.getParameter("newadminpassword");

	        Boolean result = AdminAdminsDao.CreateAdministrator(newadminlogin, newadminfullname, newadminpassword);
	        if(result)
	        {
	        	request.setAttribute("NewAdminCreateSuccess", true); 
	        }
	        else
	        {
	        	request.setAttribute("ErrorNewAdminCreate", true); 
	        }
        }
    	*/
    	ArrayList<Branch> branches = AdminBranchesDao.GetBranches();
    	request.setAttribute("branches", branches);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminBranches.jsp"); 
        rd.include(request, response);
    }
}