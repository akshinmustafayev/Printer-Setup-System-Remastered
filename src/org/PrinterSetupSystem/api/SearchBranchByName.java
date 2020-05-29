package org.PrinterSetupSystem.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

public class SearchBranchByName extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException
    {
		AuthorizeUtil.FixUtf8(response);
		
		String result = "{ \"branches\": [ ";
		PrintWriter out = response.getWriter();

        if(request.getParameter("branchname") != null && request.getParameter("branchname") != "")
		{
        	String branchname = request.getParameter("branchname");
        	
        	ArrayList<Branch> branches = new ArrayList<Branch>();
    		
    		try
            {
            	Connection conn = ConnectionUtils.getConnection();
                PreparedStatement pstmt = null;
                
                String sql = "select * from branches where name like ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%" + branchname + "%");
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
                	branches.add(branch);
                }
                
                pstmt.close();
                conn.close();
            }
    		catch(Exception e)
            {
                e.printStackTrace();
            }
    		
    		for (int i = 0; i < branches.size(); i++) 
        	{
    			if(i == branches.size() - 1)
        		{
    				result = result + "{ \"branchid\" : " + branches.get(i).GetId() + " , \"branchname\" : \"" + branches.get(i).GetName() + "\" , \"branchdescription\" : \"" + branches.get(i).GetDescription() + "\", \"branchimage\" : \"" + branches.get(i).GetImage() + "\" }";
        		}
        		else
        		{
        			result = result + "{ \"branchid\" : " + branches.get(i).GetId() + " , \"branchname\" : \"" + branches.get(i).GetName() + "\" , \"branchdescription\" : \"" + branches.get(i).GetDescription() + "\", \"branchimage\" : \"" + branches.get(i).GetImage() + "\" }, ";
        		}
        	}
    		
    		result = result + " ] }";
        	
    		if(branches.size() == 0)
    			result = "{ \"branches\": [ { \"branchid\" : -1 , \"branchname\" : \"Not found\" , \"branchdescription\" : \"Not found\", \"branchimage\" : \"img/no-image.png\" } ] }";
	        out.println(result);
		}
        else
        {
        	result = "{ \"branches\": [ { \"branchid\" : -1 , \"branchname\" : \"Not found\" , \"branchdescription\" : \"Not found\", \"branchimage\" : \"img/no-image.png\" } ] }";
	        out.println(result);
        }
    }
}

