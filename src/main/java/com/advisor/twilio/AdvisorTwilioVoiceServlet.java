package com.advisor.twilio;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.verbs.Dial;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

public class AdvisorTwilioVoiceServlet extends HttpServlet
{
	private static final long serialVersionUID = 5848806612938409009L;

	public void doPost( HttpServletRequest request, HttpServletResponse response )
	{
		try
		{
			createResponseTwiml( request, response );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
	}

	public void service( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		createResponseTwiml( request, response );
	}

	private void createResponseTwiml( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		String phoneNumber = request.getParameter( "PhoneNumber" );

		//phoneNumber = "+18662226303";
		phoneNumber = "+18552200999";
		// No need to get phone number from client we can set Our number here
		String timeLimit = request.getParameter( "timeLimit" );
		String callerId = "+15128616053";
		TwiMLResponse twiml = new TwiMLResponse( );
		Dial dial;
		try
		{
			if ( phoneNumber != null )
			{
				dial = new Dial( phoneNumber );
				dial.setCallerId( callerId );
				dial.setTimeLimit( Integer.valueOf( timeLimit ) );
				twiml.append( dial );
			}
		}
		catch ( TwiMLException e )
		{
			e.printStackTrace( );
		}
		response.setContentType( "application/xml" );
		response.getWriter( ).print( twiml.toXML( ) );
	}

	// public static void main(String[] args) throws Exception
	// {
	// Server server = new Server(Integer.valueOf(System.getenv("PORT")));
	// ServletContextHandler context = new
	// ServletContextHandler(ServletContextHandler.SESSIONS);
	// context.setContextPath("/");
	// server.setHandler(context);
	// context.addServlet(new ServletHolder(new SianeTwilioVoiceServlet()),
	// "/*");
	// server.start();
	// server.join();
	// }
}
