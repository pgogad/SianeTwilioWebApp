package com.advisor.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;

import com.advisor.db.DbManager;

@Singleton
@Path("/")
public class PayPalApprovalPost
{
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/paypalapprovalpost")
	@POST
	public String doStoreApprovals( InputStream input )
	{

		// /{payLoad}/{email}/{amount}/{date}"
		String returnStatement = "fail";
		try
		{
			StringBuffer buff = new StringBuffer( );
			BufferedReader reader = new BufferedReader( new InputStreamReader( input ) );
			String line = null;

			while( (line = reader.readLine( )) != null )
			{
				buff.append( line );
			}

			JSONObject parent = new JSONObject( buff.toString( ) );

			Connection conn = DbManager.getConnection( );
			Statement stmt = conn.createStatement( );

			System.out.println( parent.toString( ) );

			DateTime date = new DateTime( DateTimeZone.UTC );
			String format = "yyyy-MM-dd HH:mm:ss";
			String query = "INSERT INTO PAY_PAL_TRANSACTIONS (APPROVAL_OBJ,USER_ID,AMOUNT ,APPROVAL_DATE ,INSERT_DATE) values( '"
					+ parent.getString( "payLoad" ) + "','" 
					+ parent.getString( "email" ) + "','" 
					+ parent.getString( "amount" ) + "','"
					+ parent.getString( "date" ) + "','"
					+ date.toString( format )
					 + "')";
			
			System.out.println( "Query : " + query);

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
