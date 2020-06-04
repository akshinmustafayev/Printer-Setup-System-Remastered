package org.PrinterSetupSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.TimeUtil;

/** Represents Admin Other Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class AdminOtherDao 
{
	/**
	Function sets all Printers views to 0.
	@return Returns true if successful
	*/
	public static Boolean ResetAllPrinterViews()
    {
		Boolean result = true;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
    		pstmt = conn.prepareStatement("update printers set views=0");
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
	
	/**
	Function clears all Printers.
	@return Returns true if successful
	*/
	public static Boolean CleanAllPrinters()
    {
		Boolean result = true;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
    		pstmt = conn.prepareStatement("delete from printers");
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
	
	/**
	Function clears all Branches and adds None type branch which is default system branch as well as changes all printers IDs to 1 
	@return Returns true if successful
	*/
	public static Boolean CleanAllBranches()
    {
		Boolean result = true;
		
		String createddate = TimeUtil.GetTimeNow();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            PreparedStatement pstmt3 = null;
    		pstmt = conn.prepareStatement("delete from branches");
            pstmt.executeUpdate();
            
            pstmt2 = conn.prepareStatement("insert into branches (id, name, description, image, createddate) values (1, 'None', 'If not selected any branch, printer will be added to this group', null, ?)");
            pstmt2.setString(1, createddate);
            pstmt2.executeUpdate();

            pstmt3 = conn.prepareStatement("update printers set branchid=1");
            pstmt3.executeUpdate();
            
            pstmt.close();
            pstmt2.close();
            pstmt3.close();
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
	Function clears all Printers types and adds None type printers type which is default system type as well as changes all printers types IDs to 1 
	@return Returns true if successful
	*/
	public static Boolean CleanAllPrinterTypes()
    {
		Boolean result = true;
		
		String createddate = TimeUtil.GetTimeNow();
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            PreparedStatement pstmt3 = null;
    		pstmt = conn.prepareStatement("delete from printerstype");
            pstmt.executeUpdate();
            
            pstmt2 = conn.prepareStatement("insert into printerstype (id, type, createddate) values (1, 'None', ?)");
            pstmt2.setString(1, createddate);
            pstmt2.executeUpdate();

            pstmt3 = conn.prepareStatement("update printers set printertypeid=1");
            pstmt3.executeUpdate();
            
            pstmt.close();
            pstmt2.close();
            pstmt3.close();
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
