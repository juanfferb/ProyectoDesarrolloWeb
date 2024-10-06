import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConductorSearchComponent } from './conductor-search.component';

describe('ConductorSearchComponent', () => {
  let component: ConductorSearchComponent;
  let fixture: ComponentFixture<ConductorSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConductorSearchComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConductorSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
