import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Subject } from 'rxjs';
import { NotificationService } from '../notification.service';
//import { toBase64String } from '@angular/compiler/src/output/source_map';

declare var toaster: any;
let date: Date = new Date();
//var newdate = new Date();
@Component({
  selector: 'app-display-stubble',
  templateUrl: './display-stubble.component.html',
  styleUrls: ['./display-stubble.component.scss']
})
export class DisplayStubbleComponent implements OnInit {
 //products : any;
 retrievedData: any;
 loggedManufacturer:any;
 purchaseOrder: any;
 lproducts: any;
 purchaseOrders : any;
 cartItems = [];
 public searchString : string;
 productToBeAdded: Subject<any>;

  constructor(private service : UserService,private notifyService : NotificationService) {
    
    this.purchaseOrder = { product:{} ,manufacturer:{}};
    this.display(); 
    this.productToBeAdded = new Subject();
  }

  showToasterSuccess(){
    this.notifyService.showSuccess("Added to cart !!", "CART ITEMS")
}

showToasterError(){
    this.notifyService.showError("Invalid Ceredentials", "FAILED")
}

  ngOnInit(): void {
   this.retrievedData = localStorage.getItem('manufacturer');
   this.loggedManufacturer = JSON.parse(this.retrievedData);
  // console.log(this.loggedManufacturer);
  //return this.service.getAllPurchaseOrders().subscribe((data : any) => {console.log(data); this.purchaseOrders = data });
  /*if(this.purchaseOrders.manufacturerId === this.loggedManufacturer.manufacturerId) {
    this.productToBeAdded.next(this.purchaseOrders);
    this.cartItems.push(this.purchaseOrders);
  }*/
    }
  
  display() {  
  this.service.getallproducts().subscribe((data : any) => {console.log(data) ; this.lproducts = data });
  
   console.log('Inside stubble: '+ JSON.stringify(this.lproducts));
  }
 
  addToCartItems(product : any) {
    alert("Added to cart");
    this.service.AddToCart(product);
 // alert('check data in console.');
   
  /* console.log("product : " + product);
   //console.log("Farmer : " + product.farmer);
   console.log("logged manufacturer : " + this.loggedManufacturer);

   this.purchaseOrder.quantity = product.quantity;
   this.purchaseOrder.price = product.price;
   this.purchaseOrder.dateOfPurchase = date;
   this.purchaseOrder.dateOfDelivery = date.setDate(date.getDate() + 7);

   this.purchaseOrder.product.productId = product.productId;
   //this.purchaseOrder.farmer = this.products.farmer;
   this.purchaseOrder.manufacturer.manufacturerId=this.loggedManufacturer.manufacturerId;
   
    this.service.AddToCart(this.purchaseOrder).subscribe((result: any) => console.log(result));
    console.log(this.purchaseOrder);
    this.showToasterSuccess();
    //toaster.success('PurchaseOrder', 'Added Product');*/
  }

  
}
