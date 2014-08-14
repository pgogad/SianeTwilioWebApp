package com.siane.twilio;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.verbs.Client;
import com.twilio.sdk.verbs.Dial;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.Verb;

public class SianeTwilioVoiceServlet extends HttpServlet
{
	private static final long serialVersionUID = 5848806612938409009L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String phoneNumber = request.getParameter("PhoneNumber");
		String callerId = "+15127574974";
		TwiMLResponse twiml = new TwiMLResponse();

		Dial dial = new Dial();
		try
		{
			if (phoneNumber != null)
			{
				Verb verb = new Verb("Client", callerId);
				dial.append(verb);
				dial.setCallerId(callerId);
			}
			else
			{
				dial.append(new Client("jenny"));
			}
			twiml.append(dial);
		}
		catch (TwiMLException e)
		{
			e.printStackTrace();
		}
		response.setContentType("application/xml");
		response.getWriter().print(twiml.toXML());
	}
}
