package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.HashUtil;

public class UserSettingsDao 
{
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
