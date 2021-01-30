package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Farmer;
import com.rest.dto.Product;
import com.rest.dto.PurchaseOrder;
import com.ts.db.HibernateTemplate;

public class PurchaseOrderDAO {
	private SessionFactory factory = null;

	public int register(PurchaseOrder purchaseorder) {		
		return HibernateTemplate.addObject(purchaseorder);
	}

	public PurchaseOrder getPurchaseOrder(int purchaseId) {
		return (PurchaseOrder)HibernateTemplate.getObject(Product.class,purchaseId);
	}

	public List<PurchaseOrder> getAllPurchaseOrders() {
		List<PurchaseOrder> purchases=(List)HibernateTemplate.getObjectListByQuery("From PurchaseOrder");
		return purchases;	
	}
	public List<PurchaseOrder> getPurchaseorders(int manufacturerId) {
		String query= "from PurchaseOrder where manufacturerId = manufacturerId";
		System.out.println("get purchaseorders is called..." + manufacturerId);
		
		List<PurchaseOrder> obj = (List<PurchaseOrder>) HibernateTemplate.getObjectListByQuery1(query);
		ArrayList<PurchaseOrder> pro = new ArrayList<>();
		
		
		for(PurchaseOrder p1: obj){
			if(p1.getManufacturer().getManufacturerId() == manufacturerId) {
				
				pro.add(p1);
				
			}
		}
		
		return pro;
	}

	/*public List<Product> getProductByName(String productName) {	
		return (List)HibernateTemplate.getObjectListByName(Product.class,"productName",productName);
	}*/

}
