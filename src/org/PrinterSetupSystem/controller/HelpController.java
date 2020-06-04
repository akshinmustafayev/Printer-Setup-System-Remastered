package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.dao.HelpDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Represents Help Controller
@author Akshin A. Mustafayev
@version 1.0
*/
public class HelpController extends HttpServlet 
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
    	
    	String helpmanual = HelpDao.GetHelpManual();
    	request.setAttribute("helpmanual", helpmanual);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/Help.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/Help.jsp"); 
        rd.include(request, response);
    }
}