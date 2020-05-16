package org.PrinterSetupSystem.misc;

import org.PrinterSetupSystem.beans.User;
import org.PrinterSetupSystem.conn.ConnectionUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Represents Authorization functions
	@author Akshin A. Mustafayev
	@version 1.0
*/
public class AuthorizeUtil 
{
	/**
	Function for User authorization which gets all User information.
	This function should be checked if Null result returned.
	@param	request	Default HttpServletRequest
	@param	response	Default HttpServletResponse
	@return Returns User bean class if true, else it returns Null
	*/
	public static User AuthorizeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		User user;
		HttpSession session = request.getSession();
		
		String sessionLogin = (String) session.getAttribute("login");
		String sessionSession = (String) session.getAttribute("session");
		
		try
        {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pstmt = null;
			
			pstmt = conn.prepareStatement("select * from users where login=? and session=?"); 
            pstmt.setString(1, sessionLogin);
            pstmt.setString(2, sessionSession);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	user = new User();
            	user.SetId(rs.getInt("id"));
            	user.SetLogin((String)rs.getString("login"));
            	user.SetEmail((String)rs.getString("email"));
            	user.SetFullName(rs.getString("fullname"));
            	user.SetLastLoginDate(rs.getString("lastlogindate"));
            	user.SetSession(rs.getString("session"));
            	user.SetLanguage(rs.getString("language"));
            	user.SetLanguage(rs.getString("language"));
            	user.SetRequestsGroup(rs.getInt("requestsgroup"));
                return user;
            }
            
        }
		catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
		return null;
    }
	
	/**
	Function for checking if User is authorized and if not forward to /login page.
	This function should be used everywhere.
	@param	request	Default HttpServletRequest
	@param	response	Default HttpServletResponse
	*/
	public static void AuthorizedRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
    	String sessionc = (String) session.getAttribute("session");
    	String fullname = (String) session.getAttribute("fullname");
        if (login == null || sessionc == null || fullname == null) 
        {
        	response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			response.setHeader("Location", request.getContextPath() + "/login");
        }
    }
	
	/**
	Function for checking if User is authorized and if not forward to /login page.
	This function should be used for API
	@param	request	Default HttpServletRequest
	@param	response	Default HttpServletResponse
	@return Returns true if user is authorized, else it returns false
	*/
	public static Boolean CheckAuthorized(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
    	String sessionc = (String) session.getAttribute("session");
    	String fullname = (String) session.getAttribute("fullname");
        if (login == null || sessionc == null || fullname == null) 
        {
        	return false;
        }
        return true;
    }
	
	/**
	Function for checking if User authorized but pointed to /login page, 
	and if yes, redirect user to /home page.
	@param	request	Default HttpServletRequest
	@param	response	Default HttpServletResponse
	*/
	public static void AuthorizedRedirectLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
    	String sessionc = (String) session.getAttribute("session");
    	String fullname = (String) session.getAttribute("fullname");
        if (login != null && sessionc != null & fullname != null) 
        {
        	response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			response.setHeader("Location", request.getContextPath() + "/home");
        }
    }
	
	/**
	Function for checking if User pointed to .jsp page itself, 
	and if yes, redirect user to its alias page configured in web.xml.
	For example "Home.jsp" redirect to "/home".
	@param	request	Default HttpServletRequest
	@param	response	Default HttpServletResponse
	@param	pagename	Full .jsp page name
	@param	pagedestination	Destination to the alias of the given .jsp page 
	*/
	public static void UserLoadedJspRedirect(HttpServletRequest request, HttpServletResponse response, String pagename, String pagedestination) throws ServletException, IOException
    {
		String url = request.getRequestURL().toString();
		if(url.endsWith(pagename))
		{
			response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			response.setHeader("Location", request.getContextPath() + pagedestination);
		}
    }
	
	/**
	Function for fixing UTF-8 for page. 
	Required to declare at he beginning of the doPost() and doGet() functions,
	of the Servlet. Without this pages do not show pages in other languages.
	@param	response	Default HttpServletResponse
	*/
	public static void FixUtf8(HttpServletResponse response)
    {
		response.setCharacterEncoding("UTF-8");
    }
	
	/**
	Get All User Info by ID. 
	This function should be checked if Null result returned.
	@param	userid	User id number
	@return Returns User bean class
	*/
	public static User GetUserById(Integer userid)
    {
		User user = null;
		
		try
        {
        	Connection conn = ConnectionUtils.getConnection();
            PreparedStatement pstmt = null;
            
            pstmt = conn.prepareStatement("select * from users where id=?"); 
            pstmt.setInt(1, userid);

            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	user = new User();
            	user.SetId(rs.getInt("id"));
            	user.SetLogin((String)rs.getString("login"));
            	user.SetEmail((String)rs.getString("email"));
            	user.SetFullName(rs.getString("fullname"));
            	user.SetLastLoginDate(rs.getString("lastlogindate"));
            	user.SetLanguage(rs.getString("language"));
            	user.SetSession(rs.getString("session"));
            	user.SetLanguage(rs.getString("language"));
            	user.SetRequestsGroup(rs.getInt("requestsgroup"));
            } 
            
            pstmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return user;
    }
	
	/**
	Set cookie in UTF-8 format
	@param	response	Default HttpServletResponse
	@param	name	Cookie name
	@param	value	Cookie value
	@param	maxage	Cookie maximum age
	*/
	public static void SetUserCookie(HttpServletResponse response, String name, String value, int maxage) throws IOException 
	{
	    Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
	    cookie.setMaxAge(maxage);
	    response.addCookie(cookie);
	}
}
