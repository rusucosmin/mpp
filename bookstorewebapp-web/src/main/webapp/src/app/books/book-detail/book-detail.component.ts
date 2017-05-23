import {Component, Input, OnInit} from '@angular/core';

import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';


import 'rxjs/add/operator/switchMap';
import {Book} from "../shared/book.model";
import {BooksService} from "../shared/books.service";


@Component({
  selector: 'book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css'],
})

export class BookDetailComponent implements OnInit {

  @Input()
  book: Book;

  constructor(private booksService: BooksService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.booksService.getBook(+params['id']))
      .subscribe(book => this.book = book);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.booksService.update(this.book)
      .subscribe(_ => this.goBack());
  }

  delete(): void {
    this.booksService.delete(this.book.id)
      .subscribe(_ => this.goBack());
  }
}
