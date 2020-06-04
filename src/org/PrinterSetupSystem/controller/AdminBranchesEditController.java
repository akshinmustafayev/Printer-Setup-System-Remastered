package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.dao.AdminBranchesEditDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/** Represents Admin Branches Edit Controller
@author Akshin A. Mustafayev
@version 1.0
*/
@MultipartConfig
public class AdminBranchesEditController extends HttpServlet 
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
    	
    	if(request.getParameter("branchid") != null &&
        		request.getParameter("branchid") != "")
        {
    		Integer branchid = 0;
        	try
        	{
        		branchid = Integer.parseInt(request.getParameter("branchid"));
        	}
        	catch (NumberFormatException e) 
        	{
        		request.setAttribute("ErrorBranchIDNotNumber", true); 
            	request.getRequestDispatcher("/adminbranches").forward(request, response);
        	}
        	
        	if(branchid == 1)
        	{
        		request.setAttribute("ErrorMainBranchCanNotEdited", true); 
            	request.getRequestDispatcher("/adminbranches").forward(request, response);
        	}
        	
        	Branch branch = AdminBranchesEditDao.GetBranch(branchid);
        	if(branch != null)
        	{
    	    	request.setAttribute("branch", branch);
    	    	
    	    	RequestDispatcher rd = request.getRequestDispatcher("/AdminBranchesEdit.jsp"); 
    	        rd.include(request, response);
        	}
        	else
        	{
        		request.setAttribute("ErrorBranchNotFound", true); 
            	request.getRequestDispatcher("/adminbranches").forward(request, response);
        	}
        }
    	else
    	{
    		request.setAttribute("ErrorBranchIDNotNumber", true); 
        	request.getRequestDispatcher("/adminbranches").forward(request, response);
    	}
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("button_savebranch") != null && 
				request.getParameter("editbranchname") != null)
		{
    		String editbranchname = request.getParameter("editbranchname");
    		Integer editbranchid = Integer.parseInt(request.getParameter("editbranchid"));
    		String editbranchdescription = "None";
    		String editbranchimagenull = request.getParameter("editbranchimagenull");
    		Part editbranchimage = request.getPart("editbranchimage");
    		
    		if(request.getParameter("editbranchdescription") != null)
    			editbranchdescription = request.getParameter("editbranchdescription");
    		
    		Branch branch = new Branch();
    		branch.SetId(editbranchid);
    		branch.SetName(editbranchname);
    		branch.SetDescription(editbranchdescription);
    		
    		Boolean result = AdminBranchesEditDao.SaveBranch(branch, editbranchimage, editbranchimagenull);
	        if(result)
	        {
	        	request.setAttribute("BranchSaved", true); 
	        	response.sendRedirect(request.getContextPath() + "/adminbranchesedit?branchid=" + editbranchid);
	        }
	        else
	        {
	        	Branch _branch = AdminBranchesEditDao.GetBranch(editbranchid);
	        	if(_branch != null)
	        	{
	    	    	request.setAttribute("branch", _branch);
	    	    	request.setAttribute("ErrorEditBranchSave", true); 
	        	}
	        	else
	        	{
	        		request.setAttribute("ErrorBranchNotFound", true); 
	            	request.getRequestDispatcher("/adminbranches").forward(request, response);
	        	}
	        }

	    	RequestDispatcher rd = request.getRequestDispatcher("/AdminBranchesEdit.jsp"); 
	        rd.include(request, response);
		}
    	else
    	{
    		request.setAttribute("ErrorBranchIDNotNumber", true); 
        	request.getRequestDispatcher("/adminbranches").forward(request, response);
    	}
    }
}