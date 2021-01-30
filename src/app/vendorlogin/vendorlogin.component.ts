import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { NotificationService } from '../notification.service';
import { EmailValidator } from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-vendorlogin',
  templateUrl: './vendorlogin.component.html',
  styleUrls: ['./vendorlogin.component.scss']
})
export class VendorloginComponent implements OnInit {
  manufacturer:any;
  loggedManufacturer:any;
  user:any;
  res :any;

  constructor(private service : UserService,private router : Router , private notifyService : NotificationService) {
    this.user = {mailId : '' , password : ''};
    this.manufacturer={};
  }
  showToasterSuccess(){
    this.notifyService.showSuccess("Login Successfull !!", "VENDOR LOGIN")
}
showToasterSuccess1(){
  this.notifyService.showSuccess("Registration Successfull !!", "VENDOR REGISTRATION")
}

showToasterError(){
    this.notifyService.showError("Invalid Ceredentials", "FAILED")
}
showToasterError1(){
  this.notifyService.showError("User doesn't exist", "Register")
}
  
  ngOnInit(): void {
  }
  RegisterSubmit(RegisterForm : any): void {
    this.service.registerManufacturer(this.manufacturer).subscribe((result: any) =>  {console.log(result); } ) ;
    //this.res =  this.loggedManufacturer.mailId;
   // this.service.Email(this.res).subscribe((result:any) => {console.log(this.res)});
    this.showToasterSuccess1();
    this.router.navigate(['/VendorLogin']);
    
  }

  async loginSubmit1(loginForm:any) {
    await this.service.loginmanufacturer(this.user.mailId).then((manufacturer) => {console.log(manufacturer); this.loggedManufacturer = manufacturer} );
    localStorage.setItem('manufacturer' , JSON.stringify(this.loggedManufacturer));
    if(this.loggedManufacturer != null){
      if(this.loggedManufacturer.password == this.user.password){
          this.showToasterSuccess();
          this.router.navigate(['Displayproducts']);
      }else {
        this.showToasterError();

      }
    } else {
      this.showToasterError1();
    }

  }
  get mailId(){
    return this.userEmails.get('mailId');
    }
    userEmails = new FormGroup({
      mailId: new FormControl('',[
        Validators.required,
        Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
      
      });  
    

}
