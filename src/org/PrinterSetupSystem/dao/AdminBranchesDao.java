package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.conn.ConnectionUtils;

public class AdminBranchesDao 
{
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
            	Branch branch = new Branch();
            	branch.SetId(rs.getInt("id"));
            	branch.SetName(rs.getString("name"));
            	branch.SetDescription(rs.getString("description"));
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
	
	/**
	Function deletes branch from branches table.
	*/
	public static Boolean DeleteBranch(Integer branchid)
    {
		Boolean result = true;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            
            pstmt = conn.prepareStatement("delete from branches where id=?");
            pstmt.setInt(1, branchid);
            pstmt.executeUpdate();
            
            pstmt2 = conn.prepareStatement("update printers set branchid = 1 where branchid=?");
            pstmt2.setInt(1, branchid);
            pstmt2.executeUpdate();

            pstmt.close();
            pstmt2.close();
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
