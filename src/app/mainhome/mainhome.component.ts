import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mainhome',
  templateUrl: './mainhome.component.html',
  styleUrls: ['./mainhome.component.scss']
})
export class MainhomeComponent implements OnInit {
  url = 'assets/custom.js';
  loadAPI: Promise<any>;

  constructor() { }

 
    ngOnInit() {
      this.loadAPI = new Promise(resolve => {
        this.loadScript();
      });
    }
  
  public loadScript() {
      let node = document.createElement('script');
      node.src = this.url;
      node.type = 'text/javascript';
      node.async = true;
      node.charset = 'utf-8';
      document.getElementsByTagName('head')[0].appendChild(node);
  }
  
  }
  