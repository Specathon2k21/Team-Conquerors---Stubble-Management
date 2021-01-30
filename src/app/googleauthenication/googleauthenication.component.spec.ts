import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoogleauthenicationComponent } from './googleauthenication.component';

describe('GoogleauthenicationComponent', () => {
  let component: GoogleauthenicationComponent;
  let fixture: ComponentFixture<GoogleauthenicationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoogleauthenicationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoogleauthenicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
