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
import org.PrinterSetupSystem.conn.ConnectionUtils;

public class HomeDao 
{
	/**
	Function returns all branches in ArrayList type.
	@return Returns ArrayList<Branch> array
	*/
	public static ArrayList<Branch> GetBranches()
    {
		ArrayList<Branch> branches = new ArrayList<Branch>();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from branches");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	if(rs.getInt("id") == 1) continue;
            	
            	Branch branch = new Branch();
            	branch.SetId(rs.getInt("id"));
            	branch.SetName(rs.getString("name"));
            	branch.SetDescription(rs.getString("description"));
            	
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
	            		branch.SetImage(base64Image);
                	}
                	else
                		branch.SetImage("img/no-image.png");
            	}
            	else
            		branch.SetImage("img/no-image.png");
            	
            	branch.SetCreatedDate(rs.getString("createddate"));
            	branches.add(branch);
            }
            
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return branches;
    }
}
