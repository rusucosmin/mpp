import { Injectable } from '@angular/core';
import { Http, Response, Headers } from "@angular/http";

import { Book } from "./book.model";

import { Observable } from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class BooksService {

  private booksUrl = 'http://localhost:8080/api/books';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  getBooks(): Observable<Book[]> {
    return this.http.get(this.booksUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.books || {};
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

  getBook(id: number): Observable<Book> {
    return this.getBooks()
      .map(books => books.find(book => book.id === id));
  }

  update(book: Book) {
    const url = `${this.booksUrl}/${book.id}`;
    return this.http
      .put(url, JSON.stringify({"book": book}), {headers: this.headers})
      .map(this.extractBookData)
      .catch(this.handleError);
  }

  private extractBookData(res: Response) {
    let body = res.json();
    return body.book || {}
  }

  create(title: string, author: string, price: number) {
    let book = {title, author, price};
    console.log("createRequest: " + JSON.stringify({"book": book}));
    return this.http
      .post(this.booksUrl, JSON.stringify({"book": book}), {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  delete(bookId: number) {
    console.log("service: delete: " + bookId);
    const url = `${this.booksUrl}/${bookId}`;
    return this.http
      .delete(url, {headers: this.headers})
      .map(() => null)
      .catch(this.handleError);
  }

}
