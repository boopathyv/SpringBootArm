package com.aram.connect.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aram.connect.dataObjects.SmsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SendSMS {
	
	private ObjectMapper objectMapper;
	
	public SmsResponse sendSms(String message, String number) {
		SmsResponse smsResponse = new SmsResponse();
		try {
			// Construct data
			String apiKey = "apikey=" + "kz//hfjyffs-zVuLAdWnSWIyqlZcv17YYph2shEQPc";
			String messages = "&message=Thank you for registering with Aram IAS Academy.Your Password is  " + message;
			String sender = "&sender=" + "ARAMIN";
			String numbers = "&numbers=" + number;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + messages + sender ;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println(stringBuffer.toString());
			smsResponse = getObjectMapper().readValue(stringBuffer.toString(),SmsResponse.class);
			
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			e.printStackTrace();
			return null;
		}
		return smsResponse;
	}
	
	private ObjectMapper getObjectMapper() {
		if (this.objectMapper == null) {
			this.objectMapper = new ObjectMapper();
		}
		return this.objectMapper;
	}

}
