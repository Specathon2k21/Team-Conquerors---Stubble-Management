import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';
import { NotificationService } from '../notification.service';


@Component({
  selector: 'app-add-stubble',
  templateUrl: './add-stubble.component.html',
  styleUrls: ['./add-stubble.component.scss'],
 
})
export class AddStubbleComponent implements OnInit {
  product: any;
  retrievedData: any;
  loggedFarmer:any;
  

  constructor(private service : UserService,private notifyService : NotificationService) {
    this.product = {farmer : {}};
  
     }
     showToasterSuccess(){
      this.notifyService.showSuccess("stubble added successfull !!", "STUBBLE LOGIN")
  }
  
  showToasterError(){
      this.notifyService.showError("Invalid Ceredentials", "FAILED")
  }

  ngOnInit(): void {
  
   this.retrievedData = localStorage.getItem('farmer');
   this.loggedFarmer = JSON.parse(this.retrievedData);
   console.log(this.loggedFarmer.farmerId);
   

   
  }

  StubbleSubmit(): void {    
    this.product.farmer.farmerId = this.loggedFarmer.farmerId;
    this.service.registerProduct(this.product).subscribe((result: any) => {console.log("resutl : " + result);});
    this.showToasterSuccess();
    
 }
  
  
}
