import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-confirmedorders',
  templateUrl: './confirmedorders.component.html',
  styleUrls: ['./confirmedorders.component.scss']
})
export class ConfirmedordersComponent implements OnInit {
  confirmedOrders: any;
  retrievedData: any;
  loggedManufacturer:any;
  
  constructor(private service : UserService) {
    this.confirmedOrders = {farmer: {}, product: {}, manufacturer:{}}
   }

  ngOnInit(): void {
    this.retrievedData = localStorage.getItem('manufacturer');
    this.loggedManufacturer = JSON.parse(this.retrievedData);
    this.service.getAllPurchaseOrders(this.loggedManufacturer.manufacturerId).subscribe((data : any) => {console.log(data); this.confirmedOrders = data });
  }

  formattingDate(date: any) {
   return formatDate(date , 'yyyy-MM-dd hh:mm:ss a', 'en-US', '+0530');
  }

}
