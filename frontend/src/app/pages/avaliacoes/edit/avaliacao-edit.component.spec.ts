import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliacaoEditComponent } from './avaliacao-edit.component';

describe('AvaliacaoEditComponent', () => {
  let component: AvaliacaoEditComponent;
  let fixture: ComponentFixture<AvaliacaoEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvaliacaoEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvaliacaoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
