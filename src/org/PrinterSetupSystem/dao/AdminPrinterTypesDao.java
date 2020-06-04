package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.TimeUtil;

/** Represents Admin Printer Types Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminPrinterTypesDao 
{
	/**
	Function gets array list of all printer types.
	@return Returns ArrayList<PrinterType>
	*/
	public static ArrayList<PrinterType> GetPrintersTypes()
    {
		ArrayList<PrinterType> printerstypes = new ArrayList<PrinterType>();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from printerstype");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	PrinterType printerstype = new PrinterType();
            	printerstype.SetId(rs.getInt("id"));
            	printerstype.SetType(rs.getString("type"));
            	printerstypes.add(printerstype);
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return printerstypes;
    }
	
	/**
	Function deletes printer type from printerstype table.
	@return Returns true if successful
	*/
	public static Boolean DeletePrintersType(Integer typeid)
    {
		Boolean result = true;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            
            pstmt = conn.prepareStatement("delete from printerstype where id=?");
            pstmt.setInt(1, typeid);
            pstmt.executeUpdate();
            
            pstmt2 = conn.prepareStatement("update printers set printertypeid = 1 where printertypeid=?");
            pstmt2.setInt(1, typeid);
            pstmt2.executeUpdate();

            pstmt.close();
            pstmt2.close();
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
	Function creates Printer type in printerstype table.
	@param	newprintertype	New printer type name
	@return Returns true if successful
	*/
	public static Boolean CreatePrintersType(String newprintertype)
    {
		Boolean result = true;
		
		String createddate = TimeUtil.GetTimeNow();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("insert into printerstype (type, createddate) values (?, ?)");
            pstmt.setString(1, newprintertype);
            pstmt.setString(2, createddate);
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
