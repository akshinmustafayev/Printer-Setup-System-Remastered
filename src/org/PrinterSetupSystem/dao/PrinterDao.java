package org.PrinterSetupSystem.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.interfaces.IPrinterShow;
import org.PrinterSetupSystem.misc.EncodingUtil;

/** Represents Printer Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class PrinterDao implements IPrinterShow
{
	private Printer printer = null;
	private PrinterType printertype = null;
	
	/**
	Function gets Printer object by its ID
	@param	printerid	ID of the printer
	@return Returns Printer object
	*/
	@Override
	public Printer GetPrinter(Integer printerid)
	{
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select printers.*, IFNULL(printerstype.type, \"Type not found\") as printertype, printerstype.createddate as printertypedate, IFNULL(branches.name, \"Branch not found\") as branchname from printers left join printerstype on printers.printertypeid = printerstype.id left join branches on printers.branchid = branches.id where printers.id = ?"); 
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
            	printer.SetBranchName(rs.getString("branchname"));
            	printer.SetIp(rs.getString("ip"));
            	printer.SetVendor(rs.getString("vendor"));
            	printer.SetCreatedDate(rs.getString("createddate"));
            	printer.SetViews(rs.getInt("views"));
            	printer.SetServerShareName(rs.getString("serversharename"));
            	printer.SetLocation(rs.getString("location"));
            	
            	printertype = new PrinterType();
            	printertype.SetType(rs.getString("printertype"));
            	printertype.SetCreatedDate(rs.getString("printertypedate"));
            	
            	_AddViewsCountToPrinter(printer);
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
	
	/**
	Function gets Printer Type object.
	@return Returns Printer Type object
	*/
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
	
	/**
	Function adds count +1 to printer views column.
	*/
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
	
	/**
	Function gets Printer logo by Vendor column.
	@param	_printer	Printer object
	@return Returns HTML string of the logo image
	*/
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
		else if(_printer.GetVendor().toLowerCase().contains("undefined"))
		{
			vendorlogo = "<img src=\"img/vendors/undefined.png\" style=\"margin-right:5px;height:18px;\">";
		}
		else if(_printer.GetVendor().toLowerCase().contains("unknown"))
		{
			vendorlogo = "<img src=\"img/vendors/undefined.png\" style=\"margin-right:5px;height:18px;\">";
		}
		else if(_printer.GetVendor().toLowerCase().equals(""))
		{
			vendorlogo = "<img src=\"img/vendors/undefined.png\" style=\"margin-right:5px;height:18px;\">";
		}
		else if(_printer.GetVendor().toLowerCase().equals(" "))
		{
			vendorlogo = "<img src=\"img/vendors/undefined.png\" style=\"margin-right:5px;height:18px;\">";
		}
		
		return vendorlogo;
	}
	
	/**
	Function gets send by email link.
	@param	_printer	Printer object
	@param	request	Default HttpServletRequest
	@return Returns HTML string of the mailto: tag
	*/
	public static String GetPrinterEmailLink(Printer _printer, HttpServletRequest request)
	{
		String emaillink = "";
		String mailto = "mail";
		String printername = _printer.GetName().toString();
		String printerlink = request.getRequestURL().toString();
		
		printername = EncodingUtil.EncodeURIComponent(printername);
		printerlink = EncodingUtil.EncodeURIComponent(printerlink);
		
		emaillink = mailto + "?subject=" + printername + " - Printer Install Link&body=Link%20for%20the%20printer%20is%3A%0D%0A" + printerlink + "?id=" + _printer.GetId().toString();
		
		return emaillink;
	}
}
