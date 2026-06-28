import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCategoriesPageComponent } from './create-categories-page.component';

describe('CreateCategoriesPageComponent', () => {
  let component: CreateCategoriesPageComponent;
  let fixture: ComponentFixture<CreateCategoriesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateCategoriesPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCategoriesPageComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
