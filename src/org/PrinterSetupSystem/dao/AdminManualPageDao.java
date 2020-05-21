package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.PrinterSetupSystem.conn.ConnectionUtils;

public class AdminManualPageDao 
{
	/**
	Function returns help manual from systemsettings table.
	@return Returns string
	*/
	public static String GetHelpManual()
    {
		String helpmanual = "No manual found";
		
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select value from systemsettings where parameter='helpmanual'");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	helpmanual = rs.getString("value");
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return helpmanual;
    }
	
	/**
	Function sets help manual to systemsettings table.
	*/
	public static Boolean SetHelpManual(String helpmanual)
    {
		Boolean result = true;
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("update systemsettings set value = ? where parameter='helpmanual'");
            pstmt.setString(1, helpmanual);
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
