package org.PrinterSetupSystem.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

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
            	
            	_AddViewsCountToPrinter(printer);
            	printer.SetViews(rs.getInt("views"));
            	
            	printer.SetServerShareName(rs.getString("serversharename"));
            	printer.SetLocation(rs.getString("location"));
            	
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
	            		printerbranch.SetImage(base64Image);
                	}
                	else
                		printerbranch.SetImage("img/no-image.png");
            	}
            	else
            		printerbranch.SetImage("img/no-image.png");
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
	
	private void _AddViewsCountToPrinter(Printer _printer)
	{
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("UPDATE printers SET views = views + 1 where id=?"); 
            pstmt.setInt(1, _printer.GetId());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public static String GetPrinterLogoByName(Printer _printer)
	{
		String vendorlogo = "";
		if(_printer.GetVendor().toLowerCase().contains("hp") || _printer.GetVendor().toLowerCase().contains("hewlett") ||
				_printer.GetVendor().toLowerCase().contains("packard"))
		{
			vendorlogo = "<img src=\"img/vendors/hp.png\" style=\"margin-right:5px;height:18px;\">";
		}
		else if(_printer.GetVendor().toLowerCase().contains("xerox"))
		{
			vendorlogo = "<img src=\"img/vendors/xerox.png\" style=\"margin-right:5px;height:18px;\">";
		}
		else if(_printer.GetVendor().toLowerCase().contains("epson"))
		{
			vendorlogo = "<img src=\"img/vendors/epson.png\" style=\"margin-right:5px;height:18px;\">";
		}
		else if(_printer.GetVendor().toLowerCase().contains("canon"))
		{
			vendorlogo = "<img src=\"img/vendors/canon.png\" style=\"margin-right:5px;height:18px;\">";
		}
		
		return vendorlogo;
	}
	
	public static String GetPrinterEmailLink(Printer _printer, HttpServletRequest request)
	{
		String emaillink = "";
		String mailto = "mail@organization.local";
		String printername = _printer.GetName().toString();
		String printerlink = request.getRequestURL().toString();
		
		emaillink = "mailto:" + mailto + "?subject=" + printername + "&body=Link%20for%20the%20printer%20is%3A%0D%0A" + printerlink;
		
		try {
			URLEncoder.encode(emaillink, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emaillink;
	}
}
