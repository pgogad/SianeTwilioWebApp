package com.advisor.rest;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

public class TestRestService
{

	@SuppressWarnings("resource")
	@Ignore
	@Test
	public void requestWebService()
	{
		try
		{
			HttpURLConnection urlConnection = null;
			URL urlToRequest = new URL("http://dry-dusk-8611.herokuapp.com/rest");
			urlConnection = (HttpURLConnection) urlToRequest.openConnection();
			urlConnection.setConnectTimeout(1000 * 20);
			urlConnection.setRequestMethod("GET");
			urlConnection.setReadTimeout(1000 * 20);
			int statusCode = urlConnection.getResponseCode();

			if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED)
			{
				// handle unauthorized (if service requires user login)
			}
			else if (statusCode != HttpURLConnection.HTTP_OK)
			{
				// handle any other errors, like 404, 500,..
			}

			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
		    
			
			
			System.out.println(s.next());
			
			// JSONObject obj = new JSONObject(getResponseText(in));
			// return null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@SuppressWarnings({ "unused", "resource" })
	private static String getResponseText(InputStream inStream)
	{
		// very nice trick from
		// http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
		return new Scanner(inStream).useDelimiter("\\A").next();
	}
}
