import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignacionViewComponent } from './asignacion-view.component';

describe('AsignacionViewComponent', () => {
  let component: AsignacionViewComponent;
  let fixture: ComponentFixture<AsignacionViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AsignacionViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AsignacionViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
