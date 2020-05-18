package org.PrinterSetupSystem.beans;

public class PrinterType 
{
	private Integer id;
	private String type;
	private String createddate;
	
	public PrinterType() { }
	
	public Integer GetId() {
		return id;
	}
	
	public void SetId(Integer id) {
		this.id = id;
	}
	
	public String GetType() {
		return type;
	}
	
	public void SetType(String type) {
		this.type = type;
	}
	
	public String GetCreatedDate() {
		return createddate;
	}
	
	public void SetCreatedDate(String createddate) {
		this.createddate = createddate;
	}
}
