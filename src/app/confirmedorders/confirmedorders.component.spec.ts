import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmedordersComponent } from './confirmedorders.component';

describe('ConfirmedordersComponent', () => {
  let component: ConfirmedordersComponent;
  let fixture: ComponentFixture<ConfirmedordersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmedordersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmedordersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
