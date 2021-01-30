import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { NgForOf } from '@angular/common';
import { NotificationService } from '../notification.service';

declare var jQuery: any;
//declare var toaster: any;
let date: Date = new Date();
@Component({
  selector: 'app-cart-items',
  templateUrl: './cart-items.component.html',
  styleUrls: ['./cart-items.component.scss']
})
export class CartItemsComponent implements OnInit {
  cartItems = [];
  item : any;
  TotalPrice : number =0;
  x: any;
 newQuantity : any;
 y: any;
  //res : any;
  //retrievedData : any;
  purchaseOrder : any;
  retrievedData: any;
  loggedManufacturer:any;
  constructor(private service : UserService, private notifyService : NotificationService) {
    this.cartItems = this.service.cartItems;
    this.purchaseOrder = { product:{} ,manufacturer:{}};
    for(var items of this.cartItems ){
      this.TotalPrice += (items.price * items.quantity);  
    }
    
   }

  ngOnInit(): void {
    this.retrievedData = localStorage.getItem('manufacturer');
    this.loggedManufacturer = JSON.parse(this.retrievedData);

    this.y = {};

    
    //return this.service.getAllPurchaseOrders().subscribe((data : any) => {console.log(data); this.purchaseOrders = data });
   /* this.service.getPurchaseOrderByManId(this.loggedManufacturer.manufacturerId).subscribe((result: any) => {console.log("table : " + result);  this.purchaseOrders = result});*/
  }

  showToasterSuccess(){
    this.notifyService.showSuccess("Added to cart !!", "CART ITEMS")
}

showToasterError(){
    this.notifyService.showError("Invalid Ceredentials", "FAILED")
}


  deleteCartItems(item: any):void {
    const i = this.cartItems.findIndex((element) => { return element.productId === item.productId;
      
    });
  this.cartItems.splice(i, 1);
  this.TotalPrice = 0;
  for(var items of this.cartItems ){
    this.TotalPrice += (items.price * items.quantity);  
  }
}

confirmOrder() : any{
 // this.service.confirmOrder(this.cartItems, this.loggedManufacturer).subscribe((result: any) => console.log(result));
 for(this.x of this.cartItems) {
   this.purchaseOrder.price = this.x.price;
   this.purchaseOrder.quantity = this.x.quantity;
   this.purchaseOrder.dateOfPurchase = date;
   this.purchaseOrder.dateOfDelivery = date.setDate(date.getDate() + 7);
   this.purchaseOrder.product = this.x;
   this.purchaseOrder.manufacturer =this.loggedManufacturer;
   this.service.confirmOrder(this.purchaseOrder).subscribe((result: any) => console.log(result));

   this.service.message(this.loggedManufacturer.manufacturerName,this.purchaseOrder.product.farmer.mobile,this.purchaseOrder.product.productName).subscribe((result: any) => console.log(result));
 }
 this.cartItems = [];
 this.service.clearCart();
 this.TotalPrice = 0;
 this.showToasterSuccess();
 
}
 
updateProduct(RegisterForm: any) {
  console.log(RegisterForm.newQuantity)
  for(this.x of this.cartItems){
    if(this.x.productId === this.y.productId ){
      this.x.quantity = this.newQuantity;
    }
  }
}


}
