package org.PrinterSetupSystem.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.servlet.http.Part;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.conn.ConnectionUtils;

public class AdminBranchesEditDao 
{
	public static Branch GetBranch(Integer branchid)
    {
		Branch branch = null;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from branches where id=?");
            pstmt.setInt(1, branchid);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
            	branch = new Branch();
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
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
		catch(Exception e)
        {
			branch = null;
            e.printStackTrace();
        }
		
		return branch;
    }
	
	public static Boolean SaveBranch(Branch branch, Part editbranchimage, String editbranchimagenull)
    {
		Boolean result = true;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
    		InputStream imagestream = null;
    		if(editbranchimage.getSize() > 0 || editbranchimagenull != null)
    		{
    			imagestream = editbranchimage.getInputStream();
    			pstmt = conn.prepareStatement("update branches set name = ?, description = ?, image = ? where id=?");
    			pstmt.setString(1, branch.GetName());
                pstmt.setString(2, branch.GetDescription());
                pstmt.setBlob(3, imagestream);
                pstmt.setInt(4, branch.GetId());
    		}
    		else
    		{
    			pstmt = conn.prepareStatement("update branches set name = ?, description = ? where id=?");
    			pstmt.setString(1, branch.GetName());
                pstmt.setString(2, branch.GetDescription());
                pstmt.setInt(3, branch.GetId());
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
}
