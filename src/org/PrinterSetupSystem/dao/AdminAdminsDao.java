package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.PrinterSetupSystem.beans.User;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.HashUtil;

public class AdminAdminsDao 
{
	/**
	Function returns help manual from systemsettings table.
	@return Returns string
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
	Function deletes administrator from users table.
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
	Function creates administrator in users table.
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
