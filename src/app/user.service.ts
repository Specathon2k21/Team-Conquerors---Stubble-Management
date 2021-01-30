import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  updateproduct(editObject: any) {
    throw new Error("Method not implemented.");
  }
  productToBeAdded: Subject<any>;
  cartItems = [];
  private isFarmerLogged: any;

  constructor(private httpClient: HttpClient) {
    this.isFarmerLogged = false;
    this.productToBeAdded = new Subject();
  }
  /*setUserLoggedIn() : void { //login success
    this.isUserLogged = true;
  }*/
  setFarmerLoggedIn() : void {
    this.isFarmerLogged = true;
  }
 
  getFamerLogged(): any{ //call this in AuthGuard
    return this.isFarmerLogged;
  }

  setVendorLoggedIn() : void {
    this.isFarmerLogged = true;
  }
 
  getVendorLogged(): any{ //call this in AuthGuard
    return this.isFarmerLogged;
  }
  registerFarmer(farmer: any) {
    console.log('Inside service',farmer);
    return this.httpClient.post('StubbleManagement/webapi/myresource/regFarmer', farmer);
  }
  registerManufacturer(manufacturer: any) {
    console.log('Inside service',manufacturer);
    return this.httpClient.post('StubbleManagement/webapi/myresource/regManufacturer', manufacturer);
  }
  
  registerProduct(product: any) {
    console.log("Inside Service : ", product);
    return this.httpClient.post('StubbleManagement/webapi/myresource/regProduct', product);
  }
  
  /* getAllFarmers(): any {
     return this.httpClient.get('StubbleManagement/webapi/myresource/getAllFarmers');
   }*/
  loginfarmer(aadhar: any): any {
    return this.httpClient.get('StubbleManagement/webapi/myresource/farmerLogin/' + aadhar ).toPromise();
  }
  loginmanufacturer(mailId: any): any {
    return this.httpClient.get('StubbleManagement/webapi/myresource/manufacturerLogin/' + mailId).toPromise();
  }
  getallproducts(): any {
    return this.httpClient.get('StubbleManagement/webapi/myresource/getAllProducts');
  }
  AddToCart(product: any) {
    this.productToBeAdded.next(product);
    this.cartItems.push(product);
   /* console.log('Inside service ',product);
    return this.httpClient.post('StubbleManagement/webapi/myresource/purchaseOrder', product);*/
  }
  /*deleteCartItems(item:any){
    this.cartItems.splice(item);
  }*/
  getForCart() {
    return this.productToBeAdded.asObservable();
  }
  getAllPurchaseOrders(manufacturerId : any): any {
    return this.httpClient.get('StubbleManagement/webapi/myresource/getAllOrders/'+ manufacturerId);
  }

  /* getloggedFarmer(farmer : any){
     return farmer;
     
   }
   sendToLoggedFarmer() : any{
     return this.getloggedFarmer;
   }*/

   getProductsByFarmerId(farmerId : any){
     return this.httpClient.get('StubbleManagement/webapi/myresource/getProductByFarmerId/'+ farmerId);
   }
   deleteproduct(product : any){
     return this.httpClient.delete('StubbleManagement/webapi/myresource/deleteproduct/'+ product.productId);
   }
   getPurchaseOrderByManId(manufacturerId : any) {
    return this.httpClient.get('StubbleManagement/webapi/myresource/getAllOrders/'+ manufacturerId);
     
  }
 /* Email(manufacturer: any){
    return this.httpClient.post('StubbleManagement/webapi/myresource/Email',manufacturer);
  }*/

  confirmOrder(cartItems: any) {
    return this.httpClient.post('StubbleManagement/webapi/myresource/purchaseOrder', cartItems);
  }

  clearCart() {
    this.cartItems = [];
  }
  message(manufacturerName:any, mobile:any , productName : any ) {
    return this.httpClient.get('StubbleManagement/webapi/myresource/message/'+ manufacturerName + '/' +mobile + '/' + productName );
  }
 checkHashedPassword(password : any) : any{
  return this.httpClient.get('StubbleManagement/webapi/myresource/checkHashPassword/'+ password);
 }

 getOtp(number: any) : any {
   return this.httpClient.get('StubbleManagement/webapi/myresource/sendOTP/'+ number)
 }

}