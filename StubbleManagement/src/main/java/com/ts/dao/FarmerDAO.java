package com.ts.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import com.rest.dto.Farmer;
import com.ts.db.HibernateTemplate;

public class FarmerDAO {

	private SessionFactory factory = null;

	public Farmer getFarmerByUserPass(String aadhar) {

		return (Farmer)HibernateTemplate.getObjectByUserPass(aadhar);
	}

	public int register(Farmer farmer) {
		System.out.println("Inside farmer registration");
		return HibernateTemplate.addObject(farmer);
	}

	public List<Farmer> getAllFarmers() {
		List<Farmer> farmers=(List)HibernateTemplate.getObjectListByQuery("From Farmer");
		System.out.println("Inside All Farmers ..."+farmers);
		return farmers;	
	}

	public Farmer getFarmer(int id) {
		return (Farmer)HibernateTemplate.getObject(Farmer.class, id);
	}

	public String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

	public boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
	}
}