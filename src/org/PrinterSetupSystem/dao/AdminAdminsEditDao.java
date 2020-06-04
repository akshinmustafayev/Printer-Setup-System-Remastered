package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.HashUtil;

/** Represents Admin Administrator Edit Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminAdminsEditDao 
{
	/**
	Function updates Administrator password.
	@param	adminid	ID of the administrator
	@param	newpassword	New password
	@return Returns true if successful
	*/
	public static Boolean UpdatePassword(Integer adminid, String newpassword)
    {
		Boolean result = true;
		
		String dbpassword = HashUtil.GetSHA256String(newpassword);
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
    		pstmt = conn.prepareStatement("update users set password = ? where id=?");
    		pstmt.setString(1, dbpassword);
            pstmt.setInt(2, adminid);
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
