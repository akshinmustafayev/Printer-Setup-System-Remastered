package org.PrinterSetupSystem.beans;
import org.PrinterSetupSystem.misc.HashUtil;

/** Represents User
@author Akshin A. Mustafayev
@version 1.0
*/
public class User 
{
	private Integer id;
	private String login;
	private String password;
	private String fullname;
	private String lastlogindate;
	private String passwordsalt;
	private String session;
	
	public User() { }
	
	public Integer GetId() {
		return id;
	}
	
	public void SetId(Integer id) {
		this.id = id;
	}
	
	public String GetLogin() {
		return login;
	}
	
	public void SetLogin(String login) {
		this.login = login;
	}

	public String GetPassword() {
		return password;
	}
	
	public void SetPassword(String password) {
		this.password = HashUtil.GetSHA256String(password);
	}

	public String GetFullName() {
		return fullname;
	}
	
	public void SetFullName(String fullname) {
		this.fullname = fullname;
	}

	public String GetLastLoginDate() {
		return lastlogindate;
	}
	
	public void SetLastLoginDate(String lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	public String GetPasswordSalt() {
		return passwordsalt;
	}
	
	public void SetPasswordSalt(String passwordsalt) {
		this.passwordsalt = passwordsalt;
	}

	public String GetSession() {
		return session;
	}
	
	public void SetSession(String session) {
		this.session = session;
	}
}
