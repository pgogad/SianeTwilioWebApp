package com.advisor.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.twilio.sdk.client.TwilioCapability;
import com.twilio.sdk.client.TwilioCapability.DomainException;

@Singleton
@Path("/rest/{minutes}")
public class AdvisorToken
{

	public static final String ACCOUNT_SID = "ACab45f33c19bb7c80810538df10ccb061";
	public static final String AUTH_TOKEN = "b0c71987d0f1af79b7cc5d177f5ea8a9";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getCapabilityToken( @PathParam("minutes") String minutes )
	{
		String min = null;
		try
		{
			min = URLDecoder.decode( minutes ,"UTF-8" );
		}
		catch ( UnsupportedEncodingException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generateCapabilityToken( min );
	}

	private String generateCapabilityToken( String minutes )
	{
		String applicationSid = "AP5b2f8c0258d8978f8c449f72734c7ebb";
		TwilioCapability capability = new TwilioCapability( ACCOUNT_SID, AUTH_TOKEN );
		capability.allowClientOutgoing( applicationSid );
		String token = null;

		try
		{
			int min = Integer.valueOf( minutes );
			token = capability.generateToken( 60 * min );
		}
		catch ( DomainException e )
		{
			e.printStackTrace( );
		}
		return token;
	}
}
