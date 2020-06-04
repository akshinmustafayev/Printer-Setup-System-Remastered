package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.HashUtil;

/** Represents User Settings Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class UserSettingsDao 
{
	/**
	Function updates Administrator password.
	@param	newpasswrod	New password
	@param	newpasswrodconfirm	New password confirm
	@param	login	User current login from cookies
	@param	csession	User current session from cookies
	@return Returns true if successful
	*/
	public static Boolean ChangeUserPassword(String newpasswrod, String newpasswrodconfirm, String login, String csession)
    {
		Boolean result = true;
		
		if(!newpasswrod.equals(newpasswrodconfirm))
			return false;
		
		String dbpassword = HashUtil.GetSHA256String(newpasswrod);
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
    		pstmt = conn.prepareStatement("update users set password = ? where login=? and session=?");
    		pstmt.setString(1, dbpassword);
    		pstmt.setString(2, login);
    		pstmt.setString(3, csession);
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
