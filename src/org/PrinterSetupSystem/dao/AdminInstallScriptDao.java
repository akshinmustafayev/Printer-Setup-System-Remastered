package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.PrinterSetupSystem.conn.ConnectionUtils;

public class AdminInstallScriptDao 
{
	/**
	Function returns install script from systemsettings table.
	@return Returns string
	*/
	public static String GetInstallScript()
    {
		String installscript = "No script found";
		
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select value from systemsettings where parameter='installscript'");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	installscript = rs.getString("value");
            }
            
            rs.close();
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
	Function sets install script to systemsettings table.
	*/
	public static Boolean SetInstallScript(String installscript)
    {
		Boolean result = true;
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("update systemsettings set value = ? where parameter='installscript'");
            pstmt.setString(1, installscript);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
			result = false;
            e.printStackTrace();
        }
		
		return result;
    }
}
