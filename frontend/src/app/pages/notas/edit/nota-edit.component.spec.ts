import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotaEditComponent } from './nota-edit.component';

describe('CursoEditComponent', () => {
  let component: NotaEditComponent;
  let fixture: ComponentFixture<NotaEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotaEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotaEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
