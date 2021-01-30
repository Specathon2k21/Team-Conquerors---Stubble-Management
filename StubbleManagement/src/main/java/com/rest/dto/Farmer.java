package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties(allowSetters = true, value = { "productList" })
@XmlRootElement
@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="farmerId")
public class Farmer {
	@Id@GeneratedValue
	private int farmerId;
	private String farmerName;
	private String address;
	private String mobile;
	private String password;
	//private String passbookId;
	private String aadhar;

	//@JsonIgnore
	@OneToMany(mappedBy="farmer", cascade=CascadeType.ALL)
	//@Fetch(value = FetchMode.SUBSELECT)
    private List<Product> productList =new ArrayList<Product>();

	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public String getPassbookId() {
		return passbookId;
	}

	public void setPassbookId(String passbookId) {
		this.passbookId = passbookId;
	}*/

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	/*public List<Product> getProductList() {
		return productList;
	}*/

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	
	
}