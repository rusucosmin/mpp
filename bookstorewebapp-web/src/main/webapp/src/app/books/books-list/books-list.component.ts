import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {Book} from "../shared/book.model";
import {BooksService} from "../shared/books.service";

import {Location} from '@angular/common';

@Component({
  moduleId: module.id,
  selector: 'books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css'],
})
export class BooksListComponent implements OnInit {
  errorMessage: string;
  books: Book[];
  selectedBook: Book;

  constructor(private booksService: BooksService,
              private router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks() {
    this.booksService.getBooks()
      .subscribe(
        books => this.books = books,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(book: Book): void {
    this.selectedBook = book;
  }

  gotoDetail(): void {
    this.router.navigate(['/book/detail', this.selectedBook.id]);
  }

  delete(book: Book): void {
    console.log("delete: " + book.id);
    this.booksService.delete(book.id)
      .subscribe(_ => window.location.reload())
  }

  edit(book: Book): void {
    this.router.navigate(['/book/detail', book.id]);
  }
}
