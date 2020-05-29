package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHomeController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	System.out.println("Enter doGet for Admin Home Controller");
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminHome.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	System.out.println("Enter doPost for Admin Home Controller");
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    }
}