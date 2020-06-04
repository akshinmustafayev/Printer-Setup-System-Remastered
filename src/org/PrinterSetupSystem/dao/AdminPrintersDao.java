package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.conn.ConnectionUtils;

/** Represents Admin Printers Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminPrintersDao 
{
	/**
	Function gets all Printers.
	@return Returns ArrayList<Printer>
	*/
	public static ArrayList<Printer> GetPrinters()
    {
		ArrayList<Printer> printers = new ArrayList<Printer>();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select printers.id, printers.name, IFNULL(branches.name, \"Branch not found\") as printerbranch, printers.ip from printers left join branches on printers.branchid = branches.id");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	Printer printer = new Printer();
            	printer.SetId(rs.getInt("id"));
            	printer.SetName(rs.getString("name"));
            	printer.SetBranchName(rs.getString("printerbranch"));
            	printer.SetIp(rs.getString("ip"));
            	printers.add(printer);
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return printers;
    }
	
	/**
	Function deletes Printer by its ID.
	@param	printerid	ID of the printer
	@return Returns true if successful
	*/
	public static Boolean DeletePrinter(Integer printerid)
    {
		Boolean result = true;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("delete from printers where id=?");
            pstmt.setInt(1, printerid);
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
