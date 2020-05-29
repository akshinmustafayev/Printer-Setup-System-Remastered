package org.PrinterSetupSystem.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.conn.ConnectionUtils;
import org.PrinterSetupSystem.misc.AuthorizeUtil;

public class SearchPrinterByName extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException
    {
		AuthorizeUtil.FixUtf8(response);
		
		String result = "{ \"printers\": [ ";
		PrintWriter out = response.getWriter();

        if(request.getParameter("printername") != null && request.getParameter("printername") != "" &&
        		request.getParameter("printerbranch") != null && request.getParameter("printerbranch") != "")
		{
        	String printername = request.getParameter("printername");
        	Integer printerbranch = Integer.parseInt(request.getParameter("printerbranch"));
        	
        	ArrayList<Printer> printers = new ArrayList<Printer>();
    		
    		try
            {
            	Connection conn = ConnectionUtils.getConnection();
                PreparedStatement pstmt = null;
                
                String sql = "select * from printers where name like ? and branchid = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%" + printername + "%");
                pstmt.setInt(2, printerbranch);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next())
                {
                	Printer printer = new Printer();
                	printer.SetId(rs.getInt("id"));
                	printer.SetName(rs.getString("name"));
                	printer.SetDescription(rs.getString("description"));
                	
                	if(rs.getString("image") != null || rs.getString("image") != "")
                	{
                		Blob blob = rs.getBlob("image");
                		InputStream inputStream = blob.getBinaryStream();
                		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                		byte[] buffer = new byte[4096];
                		int bytesRead = -1;
                		while ((bytesRead = inputStream.read(buffer)) != -1) {
                		    outputStream.write(buffer, 0, bytesRead);
                		}
                		byte[] imageBytes = outputStream.toByteArray();
                		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                		inputStream.close();
                		outputStream.close();
                		printer.SetImage(base64Image);
                	}
                	else
                		printer.SetImage("img/no-image.png");
                	
                	printers.add(printer);
                }
                
                pstmt.close();
                conn.close();
            }
    		catch(Exception e)
            {
                e.printStackTrace();
            }
    		
    		for (int i = 0; i < printers.size(); i++) 
        	{
    			if(i == printers.size() - 1)
        		{
    				result = result + "{ \"printerid\" : " + printers.get(i).GetId() + " , \"printername\" : \"" + printers.get(i).GetName() + "\" , \"printerdescription\" : \"" + printers.get(i).GetDescription() + "\", \"printerimage\" : \"" + printers.get(i).GetImage() + "\" }";
        		}
        		else
        		{
        			result = result + "{ \"printerid\" : " + printers.get(i).GetId() + " , \"printername\" : \"" + printers.get(i).GetName() + "\" , \"printerdescription\" : \"" + printers.get(i).GetDescription() + "\", \"printerimage\" : \"" + printers.get(i).GetImage() + "\" }, ";
        		}
        	}
    		
    		result = result + " ] }";
        	
    		if(printers.size() == 0)
    			result = "{ \"printers\": [ { \"printerid\" : -1 , \"printername\" : \"Not found\" , \"printerdescription\" : \"Not found\", \"printerimage\" : \"img/no-image.png\" } ] }";
	        out.println(result);
		}
        else
        {
        	result = "{ \"printers\": [ { \"printerid\" : -1 , \"printername\" : \"Not found\" , \"printerdescription\" : \"Not found\", \"printerimage\" : \"img/no-image.png\" } ] }";
	        out.println(result);
        }
    }
}

