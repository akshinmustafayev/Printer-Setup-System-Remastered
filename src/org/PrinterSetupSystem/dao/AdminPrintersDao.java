package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.conn.ConnectionUtils;

public class AdminPrintersDao 
{
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
	Function deletes branch from branches table.
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
	
	/*
	public static Boolean CreateBranch(String newbranchname, String newbranchdescription, Part newbranchimage)
    {
		Boolean result = true;
		
		String createddate = TimeUtil.GetTimeNow();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            InputStream imagestream = null;
            if(newbranchimage != null)
            {
            	imagestream = newbranchimage.getInputStream();
            	pstmt = conn.prepareStatement("insert into branches (name, description, image, createddate) values (?, ?, ?, ?)");
            	pstmt.setString(1, newbranchname);
                pstmt.setString(2, newbranchdescription);
                pstmt.setBlob(3, imagestream);
                pstmt.setString(4, createddate);
            }
            else
            {
            	pstmt = conn.prepareStatement("insert into branches (name, description, createddate) values (?, ?, ?)");
            	pstmt.setString(1, newbranchname);
                pstmt.setString(2, newbranchdescription);
                pstmt.setString(3, createddate);
            }
            
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
    */
}
