package org.PrinterSetupSystem.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.TimeUtil;

public class AdminPrintersCreateDao 
{
	public static ArrayList<Branch> GetBranches()
    {
		ArrayList<Branch> branches = new ArrayList<Branch>();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select id, name from branches");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	Branch branch = new Branch();
            	branch.SetId(rs.getInt("id"));
            	branch.SetName(rs.getString("name"));
            	branches.add(branch);
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return branches;
    }
	
	public static ArrayList<PrinterType> GetPrinterTypes()
    {
		ArrayList<PrinterType> printerstypes = new ArrayList<PrinterType>();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select id, type from printerstype");
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
	
	public static Boolean CreatePrinter(Printer printer, Part newprinterimage)
    {
		Boolean result = true;
		String createddate = TimeUtil.GetTimeNow();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
    		InputStream imagestream = null;
    		if(newprinterimage != null)
    			imagestream = newprinterimage.getInputStream();
            
            pstmt = conn.prepareStatement("insert into printers (name, description, image, branchid, ip, vendor, createddate, printertypeid, serversharename, location, customfield1) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, printer.GetName());
            pstmt.setString(2, printer.GetDescription());
            pstmt.setBlob(3, imagestream);
            pstmt.setInt(4, printer.GetBranchId());
            pstmt.setString(5, printer.GetIp());
            pstmt.setString(6, printer.GetVendor());
            pstmt.setString(7, createddate);
            pstmt.setInt(8, printer.GetPrinterTypeId());
            pstmt.setString(9, printer.GetServerShareName());
            pstmt.setString(10, printer.GetLocation());
            pstmt.setString(11, printer.GetCustomField1());
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
