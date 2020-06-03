package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.dao.AdminInstallScriptDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInstallScriptController extends HttpServlet 
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
    	
    	String installscript = AdminInstallScriptDao.GetInstallScript();
    	request.setAttribute("installscript", installscript);
    	String scriptextension = AdminInstallScriptDao.GetScriptExtension();
    	request.setAttribute("scriptextension", scriptextension);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminInstallScript.jsp"); 
        rd.include(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
    {
    	AuthorizeUtil.FixUtf8(response);
    	AuthorizeUtil.SetAdminAuthorized(request, response);
    	AuthorizeUtil.AuthorizedRedirect(request, response);
    	
    	if(request.getParameter("savescript_button") != null && request.getParameter("adminscript") != null
    			 && request.getParameter("adminscriptextension") != null)
        {
    		String installscript = request.getParameter("adminscript");
    		String scriptextension = request.getParameter("adminscriptextension");
    		
    		Boolean result = AdminInstallScriptDao.SetInstallScript(installscript);
    		if(result)
    		{
    			request.setAttribute("InstallScriptSaved", true); 
    		}
    		else
    		{
    			request.setAttribute("InstallScriptSaveError", true);
    		}
    		
    		Boolean result2 = AdminInstallScriptDao.SetScriptExtension(scriptextension);
    		if(result2)
    		{
    			request.setAttribute("ScriptExtensionSaved", true); 
    		}
    		else
    		{
    			request.setAttribute("ScriptExtensionSaveError", true);
    		}
        }
    	
    	String installscript = AdminInstallScriptDao.GetInstallScript();
    	request.setAttribute("installscript", installscript);
    	String scriptextension = AdminInstallScriptDao.GetScriptExtension();
    	request.setAttribute("scriptextension", scriptextension);
    	
        RequestDispatcher rd = request.getRequestDispatcher("/AdminInstallScript.jsp"); 
        rd.include(request, response);
    }
}