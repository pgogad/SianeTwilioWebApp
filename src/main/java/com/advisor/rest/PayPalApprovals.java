package com.advisor.rest;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Statement;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.advisor.db.DbManager;

@Singleton
@Path("/")
public class PayPalApprovals
{
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/paypalapproval/{payLoad}/{email}/{amount}/{date}")
	@GET
	public String doStoreApprovals( @PathParam("payLoad") String approval, @PathParam("email") String email, @PathParam("amount") String amount,
			@PathParam("date") String insertDate )
	{
		String returnStatement = "fail";
		try
		{
			Connection conn = DbManager.getConnection( );
			Statement stmt = conn.createStatement( );

			DateTime date = new DateTime(DateTimeZone.UTC);
			String format = "yyyy-MM-dd HH:mm:ss";
			String query = "INSERT INTO PAY_PAL_TRANSACTIONS (APPROVAL_OBJ,USER_ID,AMOUNT ,APPROVAL_DATE ,INSERT_DATE) values( '"
					+ URLDecoder.decode( approval, "UTF-8" ) + "','" 
					+ URLDecoder.decode( email, "UTF-8" ) + "', '" 
					+ URLDecoder.decode( amount, "UTF-8" ) + "','" 
					+ date.toString( format ) + "','" 
					+ URLDecoder.decode( insertDate, "UTF-8" ) 
					+ "')";

			stmt.execute( query );

			if ( !conn.isClosed( ) )
			{
				conn.close( );
			}
			
			returnStatement = "Success"; 
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		return returnStatement;
	}
}
