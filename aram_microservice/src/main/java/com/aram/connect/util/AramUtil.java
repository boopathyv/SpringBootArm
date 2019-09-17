package com.aram.connect.util;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class AramUtil {

	public static Date toDate(String date) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newDate;
	}

	public static Date stringToDate_YYYY_MM_DD(String date) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newDate;
	}


	public static Date stringToDate(String date) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newDate;
	}

	public static String dateToString(Date date, String pattern) {
		if (null == date)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String newDate = null;
		newDate = sdf.format(date);
		return newDate;
	}

	public static String generateToken(String password, int userId, String role) {
		String token = "";
		String raw = userId + "_ESNSEIS" + password + "_APEIEjdjsu" + role + "uTUN_HGE";

		try {
			token = Base64.getEncoder().encodeToString(raw.getBytes("utf-8"));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return token.replaceAll("//s", "");
	}

	public static boolean checkIfListIsEmpty(Object list) {
		if (null == list)
			return true;
		if (list instanceof List && ((List) list).isEmpty())
			return true;
		if (list instanceof Set && ((Set) list).isEmpty())
			return true;
		return false;
	}

	public static boolean checkIfStringIsEmpty(String t) {
		if (null == t)
			return true;

		if ("".equals((t).trim()))
			return true;

		return false;
	}

	public static boolean checkIfObjectIsEmpty(Object t) {
		if (null == t)
			return true;
		return false;
	}

	public static LocalDate stringToLocalDate(String strDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(strDate, formatter);

		return date;
	}

	public static String addParaStyling(String input) {

		if (checkIfStringIsEmpty(input))
			return null;
		input = removeDoubleQuotes(input);
		String[] str = input.split("\\\\n");

		String output = str[0];

		int i = 0;
		for (String s : str) {
			i++;
			if (1 == i)
				continue;
			output += "<p>" + s + "</p>";
		}

		return output;
	}

	public static String removeDoubleQuotes(String name) {
		return name.replace("^\"|\"$", "");
	}

	public static String localDateToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String strDate = date.format(formatter);
		return strDate;
	}

	public static Timestamp getCurrentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String timestampToString(Timestamp ts) {
		if (null == ts)
			return null;
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(ts);
		return formattedDate;
	}

	public static String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

}
