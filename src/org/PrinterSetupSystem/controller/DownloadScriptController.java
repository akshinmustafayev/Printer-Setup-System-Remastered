package org.PrinterSetupSystem.controller;

import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.dao.DownloadScriptDao;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadScriptController extends HttpServlet 
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
    	System.out.println("Enter doGet for Branch Controller");
    	
    	if(request.getParameter("printerid") != null)
        {
    		Integer printerid = Integer.parseInt(request.getParameter("printerid"));
    		Printer printer = DownloadScriptDao.GetPrinter(printerid);
    		if(printer != null)
    		{
	    		String scriptname = "none";
	    		String script= "none";
	    		String scriptextension = "txt";
	    		
	    		script = DownloadScriptDao.GetInstallScript();
	    		scriptextension = DownloadScriptDao.GetInstallScriptExtension();
	    		scriptname = printer.GetName().trim();
	    		
	    		response.setContentType("application/octet-stream");
	    		response.setHeader("Content-Disposition", "attachment;filename=" + scriptname + "." + scriptextension);
	    		
	    		StringBuffer sb = new StringBuffer(script);
	    		InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
	    		ServletOutputStream out = response.getOutputStream();
	
	    		byte[] outputByte = new byte[4096];
	    		while(in.read(outputByte, 0, 4096) != -1)
	    		{
	    			out.write(outputByte, 0, 4096);
	    		}
	    		in.close();
	    		out.flush();
	    		out.close();
    		}
        }
    }
}