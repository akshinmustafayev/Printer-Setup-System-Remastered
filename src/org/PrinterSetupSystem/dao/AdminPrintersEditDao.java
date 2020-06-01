package org.PrinterSetupSystem.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.Part;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.TimeUtil;

public class AdminPrintersEditDao 
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
	
	public static Printer GetPrinter(Integer printerid)
    {
		Printer printer = null;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from printers where id=?");
            pstmt.setInt(1, printerid);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
            	printer = new Printer();
            	printer.SetId(rs.getInt("id"));
            	printer.SetName(rs.getString("name"));
            	printer.SetDescription(rs.getString("description"));
            	if(rs.getBlob("image") != null)
            	{
            		byte[] imgcheck = rs.getBytes("image");
                	if(imgcheck.length != 0)
                	{
	            		Blob blob = rs.getBlob("image");
	            		InputStream inputStream = blob.getBinaryStream();
	            		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            		byte[] buffer = new byte[4096];
	            		int bytesRead = -1;
	            		while ((bytesRead = inputStream.read(buffer)) != -1) {
	            		    outputStream.write(buffer, 0, bytesRead);
	            		}
	            		byte[] imageBytes = outputStream.toByteArray();
	            		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	            		inputStream.close();
	            		outputStream.close();
	            		printer.SetImage(base64Image);
                	}
                	else
                		printer.SetImage("img/no-image.png");
            	}
            	else
            		printer.SetImage("img/no-image.png");
            	
            	printer.SetBranchId(rs.getInt("branchid"));
            	printer.SetIp(rs.getString("ip"));
            	printer.SetVendor(rs.getString("vendor"));
            	printer.SetCreatedDate(rs.getString("createddate"));
            	printer.SetViews(rs.getInt("views"));
            	printer.SetPrinterTypeId(rs.getInt("printertypeid"));
            	printer.SetServerShareName(rs.getString("serversharename"));
            	printer.SetLocation(rs.getString("location"));
            	
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
			printer = null;
            e.printStackTrace();
        }
		
		return printer;
    }
	
	public static Boolean SavePrinter(Printer printer, Part newprinterimage)
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
            
            pstmt = conn.prepareStatement("insert into printers (name, description, image, branchid, ip, vendor, createddate, printertypeid, serversharename, location) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
