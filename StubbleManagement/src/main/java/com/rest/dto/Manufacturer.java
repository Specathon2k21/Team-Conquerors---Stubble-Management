package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "manufacturerId")
@JsonIgnoreProperties(allowSetters = true, value = { "purchaseOrderList" })
@XmlRootElement
@Entity
public class Manufacturer {
	@Id@GeneratedValue
	private int manufacturerId;	
	private String address;
	private String companyName;
	private String gstId;
	private String mailId;
	private String manufacturerAadhar;
	private String manufacturerName;
	private String password;
	private String pinNo;
	
	
	//@JsonIgnore
	@OneToMany(mappedBy="manufacturer")
	//@Fetch(value = FetchMode.SUBSELECT)
	private List<PurchaseOrder> purchaseOrderList =new ArrayList<PurchaseOrder>();


	public int getManufacturerId() {
		return manufacturerId;
	}


	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getGstId() {
		return gstId;
	}


	public void setGstId(String gstId) {
		this.gstId = gstId;
	}


	public String getMailId() {
		return mailId;
	}


	public void setMailId(String mailId) {
		this.mailId = mailId;
	}


	public String getManufacturerAadhar() {
		return manufacturerAadhar;
	}


	public void setManufacturerAadhar(String manufacturerAadhar) {
		this.manufacturerAadhar = manufacturerAadhar;
	}


	public String getManufacturerName() {
		return manufacturerName;
	}


	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPinNo() {
		return pinNo;
	}


	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}


	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}


	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}
	
	
}
