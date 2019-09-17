package com.aram.connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.boot.json.JacksonJsonParser;

import com.aram.connect.dataObjects.SmsResponse;
import com.aram.connect.util.AramUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PracticeClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String token = AramUtil.generateToken("Test@123", 1, "Student");
		//		
		//System.out.println("Token " + token);

		PracticeClass prac = new PracticeClass();
		//prac.sendSms();
		String str ="Directions : following symbols have been used :\\n 'X' - stands for equal to.\\n'<' - stands for not equal to.\\n'-' - stands for greater than.\\n'+' - stands for not greater than.\\n'>' - stands for less than.\\n'=' - stands for not less than.";
				System.out.println(addParaStyling(str));

	}
	
	private static String removeDoubleQuotes(String name) {
		return name.replace("\"", "");
	}

	private static String addParaStyling(String input) {

		String[] str = input.split("\\\\n");

		String output = str[0];

		int i =0;
		for(String s :str) {
			i++;
			if( 1 == i) continue;
			output += "<p>" + s + "</p>";
		}

		return output;
	}

	private String creatPassword(String role) {
		String smallAlphabet = "abcdefghijklmnopqrstuvxyz";
		String capAlphabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
		String numbers = "0123456789";
		String puntuations = "@#$%&";
		String password = smallAlphabet+capAlphabet + numbers + puntuations;

		Random rand = new Random();

		char[] passChars = new char[4];
		for (int i = 0; i < 4; i++) {
			passChars[i] = password.charAt(rand.nextInt(password.length()));
		}


		return  "Aram" + role.toLowerCase() + String.valueOf(passChars) ;


	}

	public String sendSms() {
		ObjectMapper objectMap = new ObjectMapper();
		try {
			// Construct data
			String apiKey = "apikey=" + "kz//hfjyffs-zVuLAdWnSWIyqlZcv17YYph2shEQPc";
			String message = "&message=" + "Your Password is AramStud1244";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "91967793352";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message ;
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

			SmsResponse smsResponse = objectMap.readValue(stringBuffer.toString(),SmsResponse.class);
			System.out.println(smsResponse.toString());
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}

}
