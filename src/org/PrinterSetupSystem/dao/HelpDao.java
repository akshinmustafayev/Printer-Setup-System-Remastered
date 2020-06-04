package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.PrinterSetupSystem.conn.ConnectionUtils;

/** Represents Help Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class HelpDao 
{
	/**
	Function gets Help Manual string.
	@return Returns Help Manual string
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
            
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return helpmanual;
    }
}
