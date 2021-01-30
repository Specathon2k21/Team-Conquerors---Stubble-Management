package com.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.dto.Farmer;
import com.rest.dto.Manufacturer;
import com.rest.dto.Product;
import com.rest.dto.PurchaseOrder;
import com.ts.dao.FarmerDAO;
import com.ts.dao.ManufacturerDAO;
import com.ts.dao.ProductDAO;
import com.ts.dao.PurchaseOrderDAO;




@Path("myresource1")
public class MyResource1 {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}
	
	@Path("regFarmer")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String registerFarmer() {
		FarmerDAO farmerDao = new FarmerDAO();
		/*Farmer farmer=new Farmer();		
		farmer.setFarmerName("suga");
		farmer.setAddress("UttarPradesh");
		farmer.setMobile("9410236579");
    	farmer.setPassword(farmerDao.hashPassword("suga123"));
		farmer.setAadhar("5996 1452 3578");
		
		farmerDao.register(farmer);*/
		if(farmerDao.checkPass("suga12", "$2a$10$ZSti1ubmsuHZefyf37dnvONgdq7CjR4vgiCEJYvi9f9A6oj.HaWiK")) {
		return "Success";
		} else {
			return "fail";
		}
	}
	
	@Path("regProduct")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String registerProduct() {
		Product product = new Product();
		product.setProductName("sugarcane");
		product.setQuantity(100);
		product.setPrice(1000);
		
		FarmerDAO farmerDao = new FarmerDAO();
		product.setFarmer(farmerDao.getFarmer(1));
		
		ProductDAO productDao = new ProductDAO();
		productDao.register(product);
		
		return "success";
	}
	
		

	@Path("getAllFarmers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Farmer> getAllFarmer() {
		System.out.println("Recieved in getAllFarmers "); 
		FarmerDAO farmerDao = new FarmerDAO();
		List<Farmer> farmer = farmerDao.getAllFarmers();
		return farmer;

	}

	@Path("getAllProducts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		//Farmer farmerList = new Farmer();
		System.out.println("Recieved in getAllProducts "); 
		ProductDAO productDao = new ProductDAO();
		List<Product> product = productDao.getAllProducts();
		//System.out.println(product); 
		return product;
	}
	
	@Path("getFarmerById/{farmerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Farmer getFarmerById(@PathParam("farmerId") int farmerId){
		System.out.println("Recieved in getFarmerByID : " + farmerId); 
		FarmerDAO farmerDao = new FarmerDAO();
		Farmer farmer = farmerDao.getFarmer(farmerId);	
		System.out.println(farmer); 
		return farmer;
	}
	
	@Path("getProductById/{productId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductById(@PathParam("productId") int productId){
		System.out.println("Recieved in getProductByID : " + productId); 
		ProductDAO productDao = new ProductDAO();
		Product product = productDao.getProduct(productId);	
		System.out.println(product); 
		return product;
		}
	
	@Path("regManufacturer")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ManufacturerDaoTest(){
		Manufacturer manufacturer=new Manufacturer();
		manufacturer.setManufacturerName("Namjoon");
		manufacturer.setMailId("Namjoon123@gmail.com");
		manufacturer.setCompanyName("BigHit");
		manufacturer.setAddress("seoul,Mumbai");
		manufacturer.setPassword("password");
		manufacturer.setPinNo("0053");
		manufacturer.setGstId("22AAAAA0000A1Z5");
		manufacturer.setManufacturerAadhar("124556987458");
		
		ManufacturerDAO manufacturerDao = new ManufacturerDAO();
		manufacturerDao.register(manufacturer);
		
		return "Success";
	}
	
	@Path("regPurchaseOrder")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String Purchaseregister(){
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		
		/*FarmerDAO farmerDao = new FarmerDAO();
		Farmer farmer = farmerDao.getFarmer(1);*/
		
	    ProductDAO productDao = new ProductDAO();
	    Product product = productDao.getProduct(1);
		
		ManufacturerDAO manufacturerDao = new ManufacturerDAO();
		Manufacturer manufacturer = manufacturerDao.getManufacturer(1);
		
		purchaseOrder.setPurchaseId(1);
		purchaseOrder.setQuantity(3);
		purchaseOrder.setPrice(2500);
		purchaseOrder.setDateOfPurchase(new Date("1/21/14"));
		purchaseOrder.setDateOfDelivery(new Date("5/12/20"));
		//purchaseOrder.setFarmer(farmer);
		purchaseOrder.setManufacturer(manufacturer);
		purchaseOrder.setProduct(product);
		PurchaseOrderDAO purchaseOrderDao = new PurchaseOrderDAO();
		purchaseOrderDao.register(purchaseOrder);
		
		
		
		return "Success";
	}

}
