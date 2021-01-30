import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowFarmerProductsComponent } from './show-farmer-products.component';

describe('ShowFarmerProductsComponent', () => {
  let component: ShowFarmerProductsComponent;
  let fixture: ComponentFixture<ShowFarmerProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowFarmerProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowFarmerProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
