package org.PrinterSetupSystem.misc;

/** Represents functions for IP address check
@author Akshin A. Mustafayev
@version 1.0
*/
public class IPv4Util 
{
	/**
	Function for check if IP is valid.
	@param ip IP address string
	@return Returns SHA256 String 
	*/
	public static boolean CheckIfIpIsValid(String ip) 
	{
	    if(ip == null || ip.length() < 7 || ip.length() > 15) return false;

	    try 
	    {
	        int x = 0;
	        int y = ip.indexOf('.');

	        if (y == -1 || ip.charAt(x) == '-' || Integer.parseInt(ip.substring(x, y)) > 255) return false;

	        x = ip.indexOf('.', ++y);
	        if (x == -1 || ip.charAt(y) == '-' || Integer.parseInt(ip.substring(y, x)) > 255) return false;

	        y = ip.indexOf('.', ++x);
	        return  !(y == -1 ||
	                ip.charAt(x) == '-' ||
	                Integer.parseInt(ip.substring(x, y)) > 255 ||
	                ip.charAt(++y) == '-' ||
	                Integer.parseInt(ip.substring(y, ip.length())) > 255 ||
	                ip.charAt(ip.length()-1) == '.');

	    }
	    catch (NumberFormatException e) 
	    {
	        return false;
	    }
	}
}
