package org.PrinterSetupSystem.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.PrinterSetupSystem.conn.ConnectionUtils;

import com.ibatis.common.jdbc.ScriptRunner;

/** Represents Install Page DAO
@author Akshin A. Mustafayev
@version 1.0
*/
public class InstallDao 
{
	/**
	Function Installs system.
	@return Returns true if successful
	*/
	public static Boolean InstallSystem(String installdbip, String installdbuser, String installdbpassword, ServletContext sc)
    {
		Boolean result = true;
		
		try
        {
			Properties prop = LoadProperties(sc);
			if(prop != null)
			{
				if(prop.getProperty("db.configured").trim().toString().equals("no"))
				{
					Connection conn = ConnectionUtils.getConnectionInstall(installdbip, "", installdbuser, installdbpassword);
					ScriptRunner runner=new ScriptRunner(conn, false, false);
					ClassLoader loader = Thread.currentThread().getContextClassLoader();
					InputStream stream = loader.getResourceAsStream("printersetupsystem.sql");
					InputStreamReader reader = new InputStreamReader(stream);
					runner.runScript(reader);
					reader.close();
					conn.close();
					
					SaveProperties("db.configured=yes\r\n" + 
							"db.hostname=" + installdbip + "\r\n" + 
							"db.dbname=printersetupsystem\r\n" + 
							"db.username=" + installdbuser + "\r\n" + 
							"db.password=" + installdbpassword, sc);
				}
				else
				{
					result = false;
				}
			}
			else
			{
				result = false;
			}
        }
		catch(Exception e)
        {
			result = false;
            e.printStackTrace();
        }
		
		return result;
    }
	
	/**
	Function Loads properties file
	@return Returns Properties
	*/
	public static Properties LoadProperties(ServletContext sc)
    {
		Properties prop = null;
		
		try 
		{
			prop = new Properties();
			prop.load(sc.getResourceAsStream("/WEB-INF/classes/config.properties"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
    }
	
	/**
	Function saves configuration to properties file
	*/
	private static void SaveProperties(String text, ServletContext sc)
    {
		try
		{
			//File file = new File(sc.getResource("/WEB-INF/config.properties").getPath());
			File file = new File(URLDecoder.decode(sc.getResource("/WEB-INF/classes/config.properties").getPath(), "utf-8"));
		    FileOutputStream outputStream = new FileOutputStream(file);
		    byte[] strToBytes = text.getBytes();
		    outputStream.write(strToBytes);
		    outputStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
}