import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { ViewChild,ElementRef } from '@angular/core';
import { NotificationService } from '../notification.service';

declare var toastr: any;
@Component({
  selector: 'app-farmer-login',
  templateUrl: './farmer-login.component.html',
  styleUrls: ['./farmer-login.component.scss'],
  template: '<div>Farmer Login</div>',
})
export class FarmerLoginComponent implements OnInit {
  @ViewChild('loginRef', {static: true }) loginElement: ElementRef;
  auth2 : any;
  user : any;
  farmer : any;
  loggedFarmer : any;
  loggedManufacturer : any;
  passmatch : any;

  constructor(private service : UserService , private router : Router, private notifyService : NotificationService) { 
    this.user = {aadhar : '' , password : ''};
    }
    showToasterSuccess(){
      this.notifyService.showSuccess("Login Successfull !!", "LOGIN")
  }
  
  showToasterError(){
      this.notifyService.showError("Invalid Ceredentials", "FAILED")
  }
  
  showToasterInfo(){
      this.notifyService.showInfo("This is info", "ItSolutionStuff.com")
  }
  
  showToasterWarning(){
      this.notifyService.showWarning("This is warning", "ItSolutionStuff.com")
  }
  showToasterError1(){
    this.notifyService.showError("User doesn't exist", "Register")
  }

  ngOnInit(): void {
    
  }
  async loginSubmit(loginForm: any){
    
    await this.service.loginfarmer(this.user.aadhar).then((farmer) => {console.log(farmer); this.loggedFarmer = farmer} );
    
    localStorage.setItem('farmer' , JSON.stringify(this.loggedFarmer));
    
    if(this.loggedFarmer != null) {
      //this.passmatch = this.service.checkHashedPassword(this.user.password);
      if(this.user.password === this.loggedFarmer.password) {
        this.showToasterSuccess();
        this.service.setFarmerLoggedIn();
        this.router.navigate(['Addproducts']);
      } else {
        this.showToasterError();
      }
    } else {
    this.showToasterError1();
    }

  }
 


  /*googleInitialize() {
    window['googleSDKLoaded'] = () => {
      window['gapi'].load('auth2', () => {
        this.auth2 = window['gapi'].auth2.init({
          client_id: '631867203803-gfnbuj33563dmuorhmfm6cv2prqasulq.apps.googleusercontent.com',
          cookie_policy: 'single_host_origin',
          scope: 'profile email'
        });
        this.prepareLogin();
       
      });
    }
    (function(d, s, id){
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) {return;}
      js = d.createElement(s); js.id = id;
      js.src = "https://apis.google.com/js/platform.js?onload=googleSDKLoaded";
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'google-jssdk'));
  }

   prepareLogin() {
    this.auth2.attachClickHandler(this.loginElement.nativeElement, {},
      (googleUser) => {
        let profile = googleUser.getBasicProfile();
        console.log('Token || ' + googleUser.getAuthResponse().id_token);
        //this.show = true;
        //this.Name =  profile.getName();
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        alert('login succesfull!!');
        this.router.navigate(['Displayproducts']);
        alert('wait');
      }, (error) => {
        alert(JSON.stringify(error, undefined, 2));
      });
  }*/
}


