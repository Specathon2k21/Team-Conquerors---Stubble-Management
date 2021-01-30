import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStubbleComponent } from './add-stubble.component';

describe('AddStubbleComponent', () => {
  let component: AddStubbleComponent;
  let fixture: ComponentFixture<AddStubbleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddStubbleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStubbleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
