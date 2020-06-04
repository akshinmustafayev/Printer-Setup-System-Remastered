package org.PrinterSetupSystem.beans;

/** Represents Branch Bean
@author Akshin A. Mustafayev
@version 1.0
*/
public class Branch 
{
	private Integer id;
	private String name;
	private String description;
	private String image;
	private String createddate;
	
	public Branch() { }
	
	public Integer GetId() {
		return id;
	}
	
	public void SetId(Integer id) {
		this.id = id;
	}
	
	public String GetName() {
		return name;
	}
	
	public void SetName(String name) {
		this.name = name;
	}
	
	public String GetDescription() {
		return description;
	}
	
	public void SetDescription(String description) {
		this.description = description;
	}
	
	public String GetImage() {
		return image;
	}
	
	public void SetImage(String image) {
		this.image = image;
	}
	
	public String GetCreatedDate() {
		return createddate;
	}
	
	public void SetCreatedDate(String createddate) {
		this.createddate = createddate;
	}
}
