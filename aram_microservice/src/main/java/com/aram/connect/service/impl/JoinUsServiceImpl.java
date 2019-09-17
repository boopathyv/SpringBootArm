package com.aram.connect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aram.connect.AramConstants;
import com.aram.connect.dataObjects.EmailVO;
import com.aram.connect.persistence.dao.JoinUs;
import com.aram.connect.persistence.dao.JoinUsRepository;
import com.aram.connect.service.JoinUSService;
import com.aram.connect.util.EmailNotice;
import com.aram.connect.util.getHtmlmailtemplate;

@Service
public class JoinUsServiceImpl implements JoinUSService{

	
	@Autowired
	JoinUsRepository joinUsRepository;
	
	EmailNotice emailNotice = new EmailNotice();

	@Override
	public List<JoinUs> getAllJoinUs() throws Exception {
		// TODO Auto-generated method stub
		List<JoinUs> joinUs =null;
		try {
			joinUs = joinUsRepository.findAll();
		}
		catch(Exception e){
				throw new Exception("Exception in getAllJoinUS service"+e);
			}
		return joinUs;	}

	@Override
	public JoinUs createJoinUS(JoinUs joinUs) throws Exception {
		// TODO Auto-generated method stub
		JoinUs joinUsDetails=null;

		try {
			joinUsDetails = joinUsRepository.save(joinUs);
			try {
				constructAndSendEmail(joinUs);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(Exception e){
			throw new Exception("Exception in createJoinUS service"+e);
		}

		return joinUsDetails;	
		
	}
	
	private void constructAndSendEmail(JoinUs joinUs) throws Throwable {
		
		EmailVO email = new EmailVO();
		String[] toArray = new String[1];
		
		String htmltemplate = null;
			getHtmlmailtemplate htmltemplatetext = new getHtmlmailtemplate();
			
			htmltemplate = htmltemplatetext.getmailtemplateCont(joinUs);
		
		email.setContent(htmltemplate);
		email.setFromAddress(AramConstants.ARAM_GMAIL);
		email.setPassword(AramConstants.ARAM_GMAIL_PWD);
		toArray[0] = AramConstants.ARAM_MAIL;
		email.setToAddress(toArray);
		email.setSubject("Enquiry Form");
		
		emailNotice.sendTest(email);
	}

	
	
	
	
	
	

}
