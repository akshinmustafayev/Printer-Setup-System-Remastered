package org.PrinterSetupSystem.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

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
