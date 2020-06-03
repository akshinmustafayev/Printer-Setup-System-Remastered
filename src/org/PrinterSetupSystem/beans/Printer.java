package org.PrinterSetupSystem.beans;

public class Printer 
{
	private Integer id;
	private String name;
	private String description;
	private String image;
	private Integer branchid;
	private String ip;
	private String vendor;
	private String createddate;
	private Integer printertypeid;
	private Integer views;
	private String serversharename;
	private String location;
	private String customfield1;
	
	private String branchname;
	
	public Printer() { }
	
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
	
	public Integer GetBranchId() {
		return branchid;
	}
	
	public void SetBranchId(Integer branchid) {
		this.branchid = branchid;
	}
	
	public String GetIp() {
		return ip;
	}
	
	public void SetIp(String ip) {
		this.ip = ip;
	}
	
	public String GetVendor() {
		return vendor;
	}
	
	public void SetVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public String GetCreatedDate() {
		return createddate;
	}
	
	public void SetCreatedDate(String createddate) {
		this.createddate = createddate;
	}
	
	public Integer GetPrinterTypeId() {
		return printertypeid;
	}
	
	public void SetPrinterTypeId(Integer printertypeid) {
		this.printertypeid = printertypeid;
	}
	
	public Integer GetViews() {
		return views;
	}
	
	public void SetViews(Integer views) {
		this.views = views;
	}
	
	public String GetServerShareName() {
		return serversharename;
	}
	
	public void SetServerShareName(String serversharename) {
		this.serversharename = serversharename;
	}
	
	public String GetServerShareNameLink() {
		String serversharenamelink = serversharename;
		serversharenamelink = serversharenamelink.replace("\\", "/");
		return serversharename;
	}
	
	public String GetLocation() {
		return location;
	}
	
	public void SetLocation(String location) {
		this.location = location;
	}
	
	public String GetCustomField1() {
		return customfield1;
	}
	
	public void SetCustomField1(String customfield1) {
		this.customfield1 = customfield1;
	}
	

	public String GetBranchName() {
		return branchname;
	}
	
	public void SetBranchName(String branchname) {
		this.branchname = branchname;
	}
}
