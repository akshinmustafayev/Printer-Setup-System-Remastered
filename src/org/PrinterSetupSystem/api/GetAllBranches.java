package org.PrinterSetupSystem.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

public class GetAllBranches extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException
    {
		AuthorizeUtil.FixUtf8(response);
		
		String result = "{ \"branches\": [ ";
		PrintWriter out = response.getWriter();
		
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
            	if(rs.getString("image") != "")
            		branch.SetImage("uploads/" + rs.getString("image"));
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
    		result = "{ \"branches\": [ { \"branchid\" : -1 , \"branchname\" : \"Not found\" , \"branchdescription\" : \"Empty\", \"branchimage\" : \"img/no-image.png\" } ] }";
	    out.println(result);
    }
}

