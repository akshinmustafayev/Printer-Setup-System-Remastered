package org.PrinterSetupSystem.beans;

/** Represents User
@author Akshin A. Mustafayev
@version 1.0
*/
public class Settings 
{
	private Integer id;
	private String parameter;
	private String value;
	
	public Settings() { }
	
	public Integer GetId() {
		return id;
	}
	
	public void SetId(Integer id) {
		this.id = id;
	}
	
	public String GetParameter() {
		return parameter;
	}
	
	public void SetParameter(String parameter) {
		this.parameter = parameter;
	}
	
	public String GetValue() {
		return value;
	}
	
	public void SetValue(String value) {
		this.value = value;
	}
}
