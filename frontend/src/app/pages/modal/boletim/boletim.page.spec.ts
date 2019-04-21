import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoletimPage } from './boletim.page';

describe('ImagePage', () => {
  let component: BoletimPage;
  let fixture: ComponentFixture<BoletimPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoletimPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoletimPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
