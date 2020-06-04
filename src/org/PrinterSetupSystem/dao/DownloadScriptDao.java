package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.conn.ConnectionUtils;

/** Represents Download Script Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class DownloadScriptDao
{
	/**
	Function gets Printer object by its ID.
	@param	printerid	ID of the printer
	@return Returns Printer object
	*/
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
            	printer.SetCustomField1(rs.getString("customfield1"));
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
	
	/**
	Function gets install script.
	@return Returns install script string
	*/
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
	
	/**
	Function gets install script extension.
	@return Returns install script extension string
	*/
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
