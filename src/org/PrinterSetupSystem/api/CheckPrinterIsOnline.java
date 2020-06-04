package org.PrinterSetupSystem.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.AuthorizeUtil;
import org.PrinterSetupSystem.misc.IPv4Util;

/** Represents CheckPrinterIsOnline API
@author Akshin A. Mustafayev
@version 1.0
*/
public class CheckPrinterIsOnline extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException
    {
		AuthorizeUtil.FixUtf8(response);
		
		String result = "offline";
		PrintWriter out = response.getWriter();

        if(request.getParameter("printerid") != null && request.getParameter("printerid") != "")
		{
        	Integer printerid = 0;
        	try
        	{
        		printerid = Integer.parseInt(request.getParameter("printerid"));
        	}
        	catch (NumberFormatException e) {}
        	String printerip = "";
        	
    		try
            {
            	Connection conn = ConnectionUtils.getConnection();
                PreparedStatement pstmt = null;
                
                pstmt = conn.prepareStatement("select ip from printers where id = ?");
                pstmt.setInt(1, printerid);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next())
                {
                	printerip = rs.getString("ip");
                }
                
                pstmt.close();
                conn.close();
            }
    		catch(Exception e)
            {
                e.printStackTrace();
            }
    		
    		if(printerip.equals(""))
    		{
    			result = "offline";
    		}
    		else
    		{
    			if(IPv4Util.CheckIfIpIsValid(printerip))
    			{
		            InetAddress inet = InetAddress.getByName(printerip);
		            if(inet.isReachable(500))
		            	result = "online";
		            else
		            	result = "offline";
    			}
    			else
    				result = "offline";
    		}
    		out.println(result);
		}
        else
        {
        	result = "offline";
	        out.println(result);
        }
    }
}

