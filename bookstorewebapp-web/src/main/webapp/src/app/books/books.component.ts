import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  add(): void {
    this.router.navigate(["/book/new"]);
  }

}
