import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { FarmerLoginComponent } from './farmer-login/farmer-login.component';
import { FarmerRegisterComponent } from './farmer-register/farmer-register.component';
import { AuthGuard } from './auth.guard';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AddStubbleComponent } from './add-stubble/add-stubble.component';
import { GoogleauthenicationComponent } from './googleauthenication/googleauthenication.component';
import { DisplayStubbleComponent } from './display-stubble/display-stubble.component';
import { CartItemsComponent } from './cart-items/cart-items.component';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { VendorloginComponent } from './vendorlogin/vendorlogin.component';
import { AboutComponent } from './about/about.component';
import { MainhomeComponent } from './mainhome/mainhome.component';
import { DemoComponent } from './demo/demo.component';
import { ShowFarmerProductsComponent } from './show-farmer-products/show-farmer-products.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ReactiveFormsModule } from '@angular/forms';
import { FilterPipe } from './filter.pipe';
import { ConfirmedordersComponent } from './confirmedorders/confirmedorders.component';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}


const appRoot: Routes = [
  { path: '', component:  DemoComponent },
  { path: 'FarmerLogin', component: FarmerLoginComponent },
  { path: 'FarmerLogin/FarmerRegister', component: FarmerRegisterComponent },
  { path: 'VendorLogin', component: VendorloginComponent }, 
  { path: 'Addproducts', component: AddStubbleComponent },
  { path: 'Displayproducts', component: DisplayStubbleComponent },
  { path: 'cartItems', component: CartItemsComponent },
  { path: 'moreInfo', component: AboutComponent },
  { path: 'home', component:  HomeComponent },
  {path: 'demo', component:  DemoComponent},
  {path : 'showfarmerprducts', component: ShowFarmerProductsComponent },
  {path : 'comfirmedOrders', component: ConfirmedordersComponent}

];
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FarmerLoginComponent,
    FarmerRegisterComponent,
    AddStubbleComponent,
    GoogleauthenicationComponent,
    DisplayStubbleComponent,
    CartItemsComponent,
    VendorloginComponent,
    AboutComponent,
    MainhomeComponent,
    DemoComponent,
    ShowFarmerProductsComponent,
    FilterPipe,
    ConfirmedordersComponent,
   


  ],
  imports: [
    BrowserModule, HttpClientModule,
    FormsModule, AppRoutingModule, RouterModule.forChild(appRoot),
    BrowserAnimationsModule, ReactiveFormsModule,
    ToastrModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }
    )

  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
