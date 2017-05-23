import {Component, OnInit, Input} from '@angular/core';
import {Book} from "../shared/book.model";
import {BooksService} from "../shared/books.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-book-new',
  templateUrl: './book-new.component.html',
  styleUrls: ['./book-new.component.css']
})
export class BookNewComponent implements OnInit {
  @Input() book: Book;

  constructor(private bookService: BooksService,
              private location: Location) { }

  ngOnInit() {}

  goBack(): void {
    this.location.back();
  }

  save(title, author, price): void {
    console.log("saving book: " + title + ", " + author + ", " + price)
    if(!this.isValid(title, author, price)) {
      console.log("Invalid input");
      alert("Invalid input");
      return ;
    }
    this.bookService.create(title, author, price)
          .subscribe(_ => this.goBack());
  }

  private isNumber(obj) {
    return !isNaN(parseFloat(obj));
  }

  private isValid(title, author, price): boolean {
    if (!title || !author || !price) {
      console.log("empty shit");
      return false;
    }
    if(!this.isNumber(price))
      return false;
    return true;
  }

}
