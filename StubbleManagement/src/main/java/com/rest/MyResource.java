package com.rest;

import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Path("myresource")
public class MyResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	@Path("regFarmer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerFarmer(Farmer farmer){
		System.out.println("Data received in register Farmer " + farmer);
		FarmerDAO farmerDao = new FarmerDAO();
		farmerDao.register(farmer);
	}

	@Path("regManufacturer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerManufacturer(Manufacturer manufacturer){
		System.out.println("Data received in register Manufacturer " + manufacturer);
		ManufacturerDAO manufacturerDao = new ManufacturerDAO();
		manufacturerDao.register(manufacturer);
		//manufacturerDao.sendEmail(manufacturer);
	}


	@Path("regProduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerProduct(Product product){
		System.out.println("Data received in register Product " + product); 	
		product.setStatus("available");
		ProductDAO productDao = new ProductDAO();
		productDao.register(product);
	}


	@Path("getAllFarmers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Farmer> getAllFarmers() {
		System.out.println("Recieved in getAllFarmers "); 
		FarmerDAO farmerDao = new FarmerDAO();
		List<Farmer> farmer = farmerDao.getAllFarmers();
		System.out.println(farmer); 
		return farmer;

	}

	@Path("farmerLogin/{aadhar}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Farmer getFarmerByAadhar(@PathParam("aadhar") String aadhar){
		System.out.println(aadhar);
		System.out.println("Recieved in getFarmerByAadhar : " + aadhar ); 
		FarmerDAO farmerDao = new FarmerDAO();
		Farmer farmer = farmerDao.getFarmerByUserPass(aadhar);
		return farmer;
	}

	@Path("manufacturerLogin/{mailId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Manufacturer getManufacturerByMailId(@PathParam("mailId") String mailId ){
		System.out.println("Recieved in getManufacturerByMailId : " + mailId ); 
		ManufacturerDAO manufacturerDao = new ManufacturerDAO();
		Manufacturer manufacturer = manufacturerDao.getManufacturerByMail(mailId);	
		if(manufacturer != null) {
			return manufacturer;
		} else {
			return null;
		}

	}


	@Path("getAllProducts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getallProducts() {
		System.out.println("Recieved in getAllProducts "); 
		ProductDAO productDao = new ProductDAO();
		List<Product> products = productDao.getAllProducts();
		System.out.println(products); 
		return products;
	}

	@Path("purchaseOrder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addOrder(PurchaseOrder purchaseorder){
		PurchaseOrderDAO purchaseorderDao = new PurchaseOrderDAO();
		purchaseorderDao.register(purchaseorder);
		addorder1(purchaseorder.getProduct());
	}

	@Path("purchaseOrder1")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String addorder1(Product product) {
		ProductDAO productDao = new ProductDAO();
		productDao.updateProduct(product);
		return "success";
	}

	@Path("getAllOrders/{manufacturerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PurchaseOrder> getAllOrders(@PathParam("manufacturerId") int manufacturerId) {
		System.out.println("Recieved in getAllOrders "); 
		PurchaseOrderDAO purchaseOrderDao = new PurchaseOrderDAO();
		List<PurchaseOrder> purchaseOrder = purchaseOrderDao.getPurchaseorders(manufacturerId);
		//System.out.println(purchaseOrder); 
		return purchaseOrder;
	}

	@Path("getProductByFarmerId/{farmerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getFarmerByAadhar(@PathParam("farmerId") int farmerId){
		ProductDAO productDao = new ProductDAO();
		List<Product> product = productDao.getProducts(farmerId);		
		return product;		
	}

	@Path("deleteproduct/{productId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProduct(@PathParam("productId") int productId){
		ProductDAO productDao = new ProductDAO();
		int result = productDao.deleteProduct(Product.class,productId);
		System.out.println("delete product " + result);

	}


	@Path("message/{manufacturerName}/{mobile}/{productName}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String get(@PathParam("manufacturerName") String manufacturerName , @PathParam("mobile") String mobile ,@PathParam("productName") String productName) {
		try {
			System.out.println("date");
			String apiKey = "apikey=" + "Bo3nqmO0QzQ-NkaUdX6OSWvYxibKXPnXUrDtm1NTPW";
			String msg = "Dear customer , your product " + productName + " has been purchased by "+ manufacturerName + " .Thanks for choosing our service.";
			System.out.println(msg);
			String message = "&message=" + msg;
			String sender = "&sender=" + "TXTLCL";
			mobile = "91"+mobile;
			String numbers = "&numbers=" + mobile;

			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
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

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
	
	@Path("sendOTP/{number}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int sendOtp(@PathParam("number") String number) {
		int otp = (int)(Math.random() * 9000) + 1000;
		System.out.println(number + "-" + otp);
		/*Twilio.init("AC094a89e7c0864d27268f9dd9715943df", "SKc9a036d28a0fc2eb1b6c09b5fd7e6e9d");
		Message message = Message.creator(new PhoneNumber("+91"+ number), new PhoneNumber("+9515185232"), "\n\nHello, Your otp - " + otp).create();
		System.out.println(message.getSid());*/
		try {
			System.out.println("date");
			String apiKey = "apikey=" + "Bo3nqmO0QzQ-NkaUdX6OSWvYxibKXPnXUrDtm1NTPW";
			String msg = "Dear customer , Hello, Your otp - " + otp + "\n\nFrom Stubble Management";
			System.out.println(msg);
			String message = "&message=" + msg;
			String sender = "&sender=" + "TXTLCL";
			number = "91"+ number;
			String numbers = "&numbers=" + number;

			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
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
			return otp;
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return 0;
		}
	}
	
	
}
