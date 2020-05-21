package org.PrinterSetupSystem.misc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents functions for working with Time
	@author Akshin A. Mustafayev
	@version 1.0
*/
public class TimeUtil 
{
	/**
	Function for calculating current time with pattern: yyyy-MM-dd HH:mm
	@return Current time in String
	*/
	public static String GetTimeNow() 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime today = LocalDateTime.now();
		return formatter.format(today);
	}
}
