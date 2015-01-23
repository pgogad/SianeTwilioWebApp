package com.advisor.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/")
public class PayPalApprovals
{
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/paypalapproval")
	@POST
	public String doStoreApprovals( InputStream incomingData )
	{
		StringBuilder input = new StringBuilder( );

		try
		{
			BufferedReader in = new BufferedReader( new InputStreamReader( incomingData ) );
			String line = null;
			while( (line = in.readLine( )) != null )
			{
				input.append( line );
			}

//			Connection conn = DbManager.getInstance( );
			
			System.out.println( input.toString( ) );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		// return Response.status(200).entity(input.toString()).build();
		return "Success";
	}
}
