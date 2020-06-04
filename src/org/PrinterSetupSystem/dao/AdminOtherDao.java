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
	Function clears all Printers types and adds None type printers type which is default system type as well as changes all printers types IDs to 1.
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
	
	/**
	Function resets install script to system default.
	@return Returns true if successful
	*/
	public static Boolean ResetInstallScript()
    {
		Boolean result = true;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            
    		pstmt = conn.prepareStatement("UPDATE `systemsettings` SET `value` = 'printerName = \\\"%PRINTER_SHARE_NAME%\\\"\\r\\n\\r\\nSet objExplorer = CreateObject(\\\"InternetExplorer.Application\\\")\\r\\n\\r\\nobjExplorer.Navigate \\\"about:blank\\\"  \\r\\nobjExplorer.ToolBar = 0\\r\\nobjExplorer.StatusBar = 0\\r\\nobjExplorer.Left = 500\\r\\nobjExplorer.Top = 250\\r\\nobjExplorer.Width = 500\\r\\nobjExplorer.Height = 220\\r\\nobjExplorer.Visible = 1\\r\\n\\r\\nobjExplorer.Document.Title = \\\"Installation of the printer\\\"\\r\\nobjExplorer.Document.Body.InnerHTML = \\\"<table style=\\\"\\\"width:100%\\\"\\\"><tr><td id=\\\"\\\"progress\\\"\\\" style=\\\"\\\"font-family:Segoe UI;text-align: center;font-size:48px;border-bottom:1px solid black;\\\"\\\">Installing printer: 0%</td></tr><tr><td style=\\\"\\\"font-family:Segoe UI;text-align: center;font-size:22px;\\\"\\\">\\\" & printerName & \\\"</td></tr></table>\\\"\\r\\n\\r\\nWscript.Sleep 500\\r\\nobjExplorer.document.getElementById(\\\"progress\\\").innerText = \\\"Installing printer: 10%\\\"\\r\\n\\r\\nSet WshNetwork = CreateObject(\\\"WScript.Network\\\")\\r\\nWshNetwork.AddWindowsPrinterConnection printerName\\r\\nWSHNetwork.SetDefaultPrinter printerName\\r\\n\\r\\nWscript.Sleep 200\\r\\nobjExplorer.document.getElementById(\\\"progress\\\").innerText = \\\"Installing printer: 20%\\\"\\r\\n\\r\\nWscript.Sleep 200\\r\\nobjExplorer.document.getElementById(\\\"progress\\\").innerText = \\\"Installing printer: 40%\\\"\\r\\n\\r\\nWscript.Sleep 200\\r\\nobjExplorer.document.getElementById(\\\"progress\\\").innerText = \\\"Installing printer: 60%\\\"\\r\\n\\r\\nWscript.Sleep 200\\r\\nobjExplorer.document.getElementById(\\\"progress\\\").innerText = \\\"Installing printer: 80%\\\"\\r\\n\\r\\nWscript.Sleep 100\\r\\nobjExplorer.document.getElementById(\\\"progress\\\").innerText = \\\"Printer installed!\\\"\\r\\n\\r\\nWscript.Sleep 3000\\r\\nobjExplorer.Quit' WHERE parameter = 'installscript'");
            pstmt.executeUpdate();
            
            pstmt2 = conn.prepareStatement("UPDATE systemsettings SET value = 'vbs' WHERE parameter = 'installscriptextension'");
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
