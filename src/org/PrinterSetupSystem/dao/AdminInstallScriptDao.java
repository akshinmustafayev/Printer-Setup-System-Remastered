package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.PrinterSetupSystem.conn.ConnectionUtils;

/** Represents Admin Administrator Install Script Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminInstallScriptDao 
{
	/**
	Function gets Install Script from database.
	@return Returns Install Script string
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
	Function updates Install Script in database.
	@param	installscript	Script text
	@return Returns true if successful
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
	
	/**
	Function gets Install Script Extension.
	@return Returns Script Extension string
	*/
	public static String GetScriptExtension()
    {
		String scriptextension = "No script extension found";
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select value from systemsettings where parameter='installscriptextension'");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	scriptextension = rs.getString("value");
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return scriptextension;
    }
	
	/**
	Function updates Install Script Extension in database.
	@param	scriptextension	Script Extension text
	@return Returns true if successful
	*/
	public static Boolean SetScriptExtension(String scriptextension)
    {
		Boolean result = true;
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("update systemsettings set value = ? where parameter='installscriptextension'");
            pstmt.setString(1, scriptextension);
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
