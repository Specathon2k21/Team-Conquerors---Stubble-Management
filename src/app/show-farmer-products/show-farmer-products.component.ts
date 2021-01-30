import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-show-farmer-products',
  templateUrl: './show-farmer-products.component.html',
  styleUrls: ['./show-farmer-products.component.scss']
})
export class ShowFarmerProductsComponent implements OnInit {
  AddedProducts : any;
  retrievedData: any;
  loggedFarmer:any;
  products:any;
  public searchString : string;
  constructor(private service : UserService) { }

  ngOnInit(): void {
    this.retrievedData = localStorage.getItem('farmer');
    this.loggedFarmer = JSON.parse(this.retrievedData);
    this.service.getProductsByFarmerId(this.loggedFarmer.farmerId).subscribe((result: any) => {console.log("table : " + result); this.AddedProducts = result});
  }

  deleteProduct(product: any) {
    if(product.status == 'available') {
        this.service.deleteproduct(product).subscribe((result: any) => {
        const i = this.products.findIndex((element) => { return element.productId === product.productId;});
  this.products.splice(i, 1);
    //console.log(employee);
  });
  } else {
    alert("Cannot delete since product is already sold");
  } 
  }
}
