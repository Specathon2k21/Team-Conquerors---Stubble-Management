package com.ts.dao;

import java.util.List;  
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


import org.hibernate.SessionFactory;

import com.rest.dto.Farmer;
import com.rest.dto.Manufacturer;
import com.ts.db.HibernateTemplate;

public class ManufacturerDAO {
	
	private SessionFactory factory = null;

	public Manufacturer getManufacturerByMail(String mailId) {

		return (Manufacturer)HibernateTemplate.getObjectByMail(mailId);
	}

	public int register(Manufacturer manufacturer) {
		//java.util.Date utilDate = new java.sql.Date(employee.getJoinDate().getTime()); 
		return HibernateTemplate.addObject(manufacturer);
	}

	public List<Manufacturer> getAllManufacturers() {
		List<Manufacturer> manufacturers=(List)HibernateTemplate.getObjectListByQuery("From Manufacturer");
		System.out.println("Inside All Manufacturers ..."+manufacturers);
		return manufacturers;	
	}

	public Manufacturer getManufacturer(int id) {
		return (Manufacturer)HibernateTemplate.getObject(Manufacturer.class,id);
	}
	
	/*public void sendEmail(Manufacturer manufacturer) {

		System.out.println("In mail sending");

		       final String username = "stubblemanagement730@gmail.com";
		       final String password = "Stubble@123";
		       
		       String sendMessage = "GREETINGS FROM STUBBLE MANAGEMENT!!" + "This mail is to confirm that you successfully created an acount in our StubbleManagement!!!";
		       Properties prop = new Properties();
		       prop.put("mail.smtp.host", "smtp.gmail.com");
		       prop.put("mail.smtp.port", "465");
		       prop.put("mail.smtp.auth", "true");
		       prop.put("mail.smtp.starttls.enable", "true");

		       Session session = Session.getInstance(prop,
		               new javax.mail.Authenticator() {
		                   protected PasswordAuthentication getPasswordAuthentication() {
		                       return new PasswordAuthentication(username, password);
		                   }
		               });

		       try {

		           Message message = new MimeMessage(session);
		           message.setFrom(new InternetAddress("stubblemanagement730@gmail.com"));
		           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(manufacturer.getMailId()));
		           message.setSubject("Confirmation mail ");
		           message.setText(sendMessage);

		           Transport.send(message);

		           System.out.println("Done");

		       } catch (MessagingException e) {
		           e.printStackTrace();
		       }
		   }*/

}
