package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.interfaces.IPrinterShow;

public class PrinterDao implements IPrinterShow
{
	private Printer printer = null;
	private PrinterType printertype = null;
	private Branch printerbranch = null;
	
	@Override
	public Printer GetPrinter(Integer printerid)
	{
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select printers.*, IFNULL(printerstype.type, \"Type not found\") as printertype, printerstype.createddate as printertypedate from printers left join printerstype on printers.printertypeid = printerstype.id where printers.id = ?"); 
            pstmt.setInt(1, printerid);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	printer = new Printer();
            	printer.SetId(rs.getInt("id"));
            	printer.SetName(rs.getString("name"));
            	printer.SetDescription(rs.getString("description"));
            	if(rs.getString("image") != "")
            		printer.SetImage("uploads/" + rs.getString("image"));
            	else
            		printer.SetImage("img/no-image.png");
            	printer.SetBranchId(rs.getInt("branchid"));
            	printer.SetIp(rs.getString("ip"));
            	printer.SetVendor(rs.getString("vendor"));
            	printer.SetCreatedDate(rs.getString("createddate"));
            	
            	printertype = new PrinterType();
            	printertype.SetType(rs.getString("printertype"));
            	printertype.SetCreatedDate(rs.getString("printertypedate"));
            	
            	printerbranch = _GetPrinterBranch();
            } 
           
            pstmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return printer;
	}

	@Override
	public PrinterType GetPrinterType()
	{
		if(printertype == null)
		{
			printertype = new PrinterType();
        	printertype.SetType("Null");
        	printertype.SetCreatedDate("Null");
		}
			 
		return printertype;
	}
	
	@Override
	public Branch GetPrinterBranch()
	{
		return printerbranch;
	}
	
	private Branch _GetPrinterBranch()
	{
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from branches where id=?"); 
            pstmt.setInt(1, printer.GetBranchId());
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	printerbranch = new Branch();
            	printerbranch.SetId(rs.getInt("id"));
    			printerbranch.SetName(rs.getString("name"));
    			printerbranch.SetDescription(rs.getString("description"));
    			printerbranch.SetImage(rs.getString("image"));
    			printerbranch.SetCreatedDate(rs.getString("createddate"));
            } 
           
            pstmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
		if(printerbranch == null)
		{
			printerbranch = new Branch();
			printerbranch.SetId(-1);
			printerbranch.SetName("Null");
			printerbranch.SetDescription("Null");
			printerbranch.SetImage("");
			printerbranch.SetCreatedDate("");
		}
			 
		return printerbranch;
	}
}
