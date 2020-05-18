package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.conn.ConnectionUtils;

public class BranchDao 
{
	/**
	Function returns all branches in ArrayList type.
	@return Returns ArrayList<Branch> array
	*/
	public static ArrayList<Printer> GetPrinters(Integer branchid)
    {
		ArrayList<Printer> printers = new ArrayList<Printer>();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from printers where branchid=?");
            pstmt.setInt(1, branchid);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	Printer printer = new Printer();
            	printer.SetId(rs.getInt("id"));
            	printer.SetName(rs.getString("name"));
            	printer.SetDescription(rs.getString("description"));
            	if(rs.getString("image") != "")
            		printer.SetImage("uploads/" + rs.getString("image"));
            	else
            		printer.SetImage("img/no-image.png");
            	printer.SetCreatedDate(rs.getString("createddate"));
            	printers.add(printer);
            }
            
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
	Function returns all branches in ArrayList type.
	@return Returns ArrayList<Branch> array
	*/
	public static Branch GetBranchByID(Integer branchid)
    {
		Branch branch = null;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from branches where id=?");
            pstmt.setInt(1, branchid);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	branch = new Branch();
            	branch.SetId(rs.getInt("id"));
            	branch.SetName(rs.getString("name"));
            }
            
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return branch;
    }
}
