package com.ts.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Product;
import com.ts.db.HibernateTemplate;

public class ProductDAO {
	private SessionFactory factory = null;
	
	public int register(Product product) {		
		return HibernateTemplate.addObject(product);
	}

	public Product getProduct(int productId) {
		return (Product)HibernateTemplate.getObject(Product.class,productId);
	}

	public List<Product> getAllProducts() {
		List<Product> products=(List)HibernateTemplate.getObjectListByQuery("From Product where status = 'available' ");
		
		System.out.println("Inside get all products");
		for(Product product : products) {
			System.out.println(product.getProductName());
			System.out.println(product.getFarmer().getFarmerName());	
		}
		
		return products;	
	}

	public List<Product> getProductByFamerId(int farmerId) {	
		return (List)HibernateTemplate.getObjectListByName(Product.class, "farmerId" , farmerId);
	}
	
	public int updateProduct(Product product){
		product.setStatus("sold");
		return HibernateTemplate.updateObject(product);
	}
	
	//trial
	public List<Product> getProducts(int farmerId) {
		String query= "from Product where farmerId = farmerId";
		System.out.println("get products is called..." + farmerId);
		//ArrayList<Product> product = new ArrayList<Product>(); 
		List<Product> obj = (List<Product>) HibernateTemplate.getObjectListByQuery(query);
		ArrayList<Product> pro = new ArrayList<>();
		
		//System.out.println("Testing get products :" + obj); 
		for(Product p: obj){
			if(p.getFarmer().getFarmerId() == farmerId) {
				//System.out.println(p.getFarmer().getFarmerId());
				pro.add(p);
				//System.out.println(pro);
			}
		}
		
		return pro;
	}
	public int deleteProduct(Class Product, int productId) {		
		return HibernateTemplate.deleteObject(Product.class, productId);
	}


}