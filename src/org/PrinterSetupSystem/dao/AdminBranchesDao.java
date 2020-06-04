package org.PrinterSetupSystem.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.PrinterSetupSystem.beans.Branch;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.TimeUtil;

/** Represents Admin Branches Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminBranchesDao 
{
	/**
	Function returns array list of Branches.
	@return Returns ArrayList<Branch>
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
	Function deletes Branch by its ID.
	@param	branchid	ID of the branch
	@return Returns true if successful
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
	
	/**
	Function creates new Branch.
	@param	newbranchname	Name of the new branch
	@param	newbranchdescription	Description of the new branch
	@param	newbranchimage	Image of the new branch
	@return Returns true if successful
	*/
	public static Boolean CreateBranch(String newbranchname, String newbranchdescription, Part newbranchimage)
    {
		Boolean result = true;
		
		String createddate = TimeUtil.GetTimeNow();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            InputStream imagestream = null;
            if(newbranchimage != null)
            {
            	imagestream = newbranchimage.getInputStream();
            	pstmt = conn.prepareStatement("insert into branches (name, description, image, createddate) values (?, ?, ?, ?)");
            	pstmt.setString(1, newbranchname);
                pstmt.setString(2, newbranchdescription);
                pstmt.setBlob(3, imagestream);
                pstmt.setString(4, createddate);
            }
            else
            {
            	pstmt = conn.prepareStatement("insert into branches (name, description, createddate) values (?, ?, ?)");
            	pstmt.setString(1, newbranchname);
                pstmt.setString(2, newbranchdescription);
                pstmt.setString(3, createddate);
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
