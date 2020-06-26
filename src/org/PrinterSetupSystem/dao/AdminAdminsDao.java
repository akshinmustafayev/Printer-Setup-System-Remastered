package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.PrinterSetupSystem.beans.User;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.HashUtil;

/** Represents Admin Administrator Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminAdminsDao 
{
	/**
	Function returns lists of Administrators from DB.
	@return Returns array of User
	*/
	public static ArrayList<User> GetAdministrators()
	{
		ArrayList<User> administrators = new ArrayList<User>();
		
		try
		{
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pstmt = null;
            
			pstmt = conn.prepareStatement("select * from users");
			ResultSet rs = pstmt.executeQuery();
            
			while (rs.next())
			{
				User administrator = new User();
				administrator.SetId(rs.getInt("id"));
				administrator.SetLogin(rs.getString("login"));
				administrator.SetFullName(rs.getString("fullname"));
				administrators.add(administrator);
			}
            
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return administrators;
	}
	
	/**
	Function deletes Administrator from DB by its ID.
	@param	adminid	Administrator id Integer type
	@return Returns true if successful
	*/
	public static Boolean DeleteAdministrator(Integer adminid)
	{
		Boolean result = true;
		
		try
        	{
        		Connection conn = ConnectionUtils.getConnection();
        		PreparedStatement pstmt = null;
            
        		pstmt = conn.prepareStatement("delete from users where id=?");
        		pstmt.setInt(1, adminid);
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
	
	/**
	Function creates Administrator.
	@param	newadminlogin	New login of administrator
	@param	newadminfullname	Full name of new administrator
	@param	newadminpassword	Password
	@return Returns true if successful
	*/
	public static Boolean CreateAdministrator(String newadminlogin, String newadminfullname, String newadminpassword)
    {
		Boolean result = true;
		
		newadminpassword = HashUtil.GetSHA256String(newadminpassword);
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("insert into users (login, fullname, password) values (?, ?, ?)");
            pstmt.setString(1, newadminlogin);
            pstmt.setString(2, newadminfullname);
            pstmt.setString(3, newadminpassword);
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
