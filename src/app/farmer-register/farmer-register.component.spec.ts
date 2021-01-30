import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmerRegisterComponent } from './farmer-register.component';

describe('FarmerRegisterComponent', () => {
  let component: FarmerRegisterComponent;
  let fixture: ComponentFixture<FarmerRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FarmerRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmerRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
