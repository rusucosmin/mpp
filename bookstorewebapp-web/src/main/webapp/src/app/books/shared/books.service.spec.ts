import { TestBed, inject } from '@angular/core/testing';

import { BooksService } from './books.service';

describe('BooksService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BooksService]
    });
  });

  it('should ...', inject([BooksService], (service: BooksService) => {
    expect(service).toBeTruthy();
  }));
});
