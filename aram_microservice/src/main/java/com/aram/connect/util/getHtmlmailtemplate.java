package com.aram.connect.util;

import com.aram.connect.persistence.dao.JoinUs;

public class getHtmlmailtemplate {

	private final String[] monthNames = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "Decemebr" };

	private final String font_family = "Arial,Helvetica,sans-serif";

	
	
	public String getmailtemplateCont(JoinUs joinUs) {
		String htmlText = null;
		try {

			 htmlText = "<table style=\"border: 1px solid black;border-collapse: collapse;width: 100%;\"><tr style=\"border: 1px solid black; height: 50px\">"
			 		+ "<th style=\"border: 1px solid black;\" >Name</th><th style=\"border: 1px solid black;\" >Mobile Number</th><th style=\"border: 1px solid black;\">EmailId</th><th>Message</th></tr>"
			 		+ "<tbody>"
					+ "<tr style=\"border: 1px solid black; height: 50px\" ><td style=\"border: 1px solid black;\" >"+joinUs.getUserName()+ "</td><td style=\"border: 1px solid black;\" >" + joinUs.getMobileNumber() +  "</td><td style=\"border: 1px solid black;\">" + joinUs.getEmailId() +  "</td><td style=\"border: 1px solid black;\">" + joinUs.getMessage() + "</td></tr>"
					+ "</tbody>"
			 		+ "</table>";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return htmlText;
	}
}
