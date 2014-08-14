package com.siane.twilio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.client.TwilioCapability;
import com.twilio.sdk.client.TwilioCapability.DomainException;

public class SianeTwilioServlet extends HttpServlet
{
	private static final long serialVersionUID = -6400005296333054231L;

	public static final String ACCOUNT_SID = "ACab45f33c19bb7c80810538df10ccb061";
	public static final String AUTH_TOKEN = "b0c71987d0f1af79b7cc5d177f5ea8a9";

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String applicationSid = "APabe7650f654fc34655fc81ae71caa3ff";

		TwilioCapability capability = new TwilioCapability(ACCOUNT_SID, AUTH_TOKEN);
		capability.allowClientOutgoing(applicationSid);
		String token = null;

		try
		{
			token = capability.generateToken();
		}
		catch (DomainException e)
		{
			e.printStackTrace();
		}

		response.setContentType("text/html");
		request.setAttribute("token", token);
		RequestDispatcher view = request.getRequestDispatcher("client/client.jsp");
		view.forward(request, response);
		return;
	}
}
