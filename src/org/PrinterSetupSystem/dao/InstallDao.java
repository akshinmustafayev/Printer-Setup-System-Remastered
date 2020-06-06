package org.PrinterSetupSystem.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Properties;

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
	public static Boolean InstallSystem(String installdbip, String installdbuser, String installdbpassword)
    {
		Boolean result = true;
		
		try
        {
			Properties prop = LoadProperties();
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
							"db.password=" + installdbpassword);
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
	
	/*private static void LoadProperties()
    {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream stream = loader.getResourceAsStream("config.properties");
		try 
		{
			prop.load(stream);
			System.out.println(prop.getProperty("db.hostname"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }*/
	
	/**
	Function Loads properties file
	@return Returns Properties
	*/
	public static Properties LoadProperties()
    {
		Properties prop = null;
		FileInputStream stream = null;
		try 
		{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			File file = new File(classLoader.getResource("config.properties").getFile());
		  
			stream = new FileInputStream(file);
			InputStreamReader input = new InputStreamReader(stream);
			
			prop = new Properties();
			prop.load(input);
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
	private static void SaveProperties(String text)
    {
		try 
		{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			File file = new File(classLoader.getResource("config.properties").getFile());
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