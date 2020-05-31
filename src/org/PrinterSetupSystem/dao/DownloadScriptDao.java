package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.conn.ConnectionUtils;

public class DownloadScriptDao
{
	public static Printer GetPrinter(Integer printerid)
	{
		Printer printer = null;
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from printers where id = ?");
            pstmt.setInt(1, printerid);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	printer = new Printer();
            	printer.SetId(rs.getInt("id"));
            	printer.SetName(rs.getString("name"));
            	printer.SetDescription(rs.getString("description"));
            	printer.SetBranchId(rs.getInt("branchid"));
            	printer.SetIp(rs.getString("ip"));
            	printer.SetVendor(rs.getString("vendor"));
            	printer.SetPrinterTypeId(rs.getInt("printertypeid"));
            	printer.SetCreatedDate(rs.getString("createddate"));
            	printer.SetViews(rs.getInt("views"));
            	printer.SetServerShareName(rs.getString("serversharename"));
            	printer.SetLocation(rs.getString("location"));
            } 
           
            pstmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return printer;
	}
	
	public static String GetInstallScript()
	{
		String installscript = "none";
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select value from systemsettings where parameter = \"installscript\"");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	installscript = rs.getString("value");
            } 
           
            pstmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return installscript;
	}
	
	public static String GetInstallScriptExtension()
	{
		String installscriptextension = "none";
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select value from systemsettings where parameter = \"installscriptextension\"");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	installscriptextension = rs.getString("value");
            } 
           
            pstmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return installscriptextension;
	}
}
