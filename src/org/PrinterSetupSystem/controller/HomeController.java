package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.dao.HomeDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Represents Home Controller
@author Akshin A. Mustafayev
@version 1.0
*/
public class HomeController extends HttpServlet 
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
    	
    	ArrayList<Branch> branches = HomeDao.GetBranches();
    	request.setAttribute("branches", branches);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp"); 
        rd.include(request, response);
    }
}