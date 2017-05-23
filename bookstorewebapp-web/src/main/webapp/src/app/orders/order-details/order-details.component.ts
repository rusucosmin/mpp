import {Component, Input, OnInit} from '@angular/core';

import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';


import 'rxjs/add/operator/switchMap';
import {Order} from "../shared/order.model";
import {OrdersService} from "../shared/orders.service";
import {BooksService} from "../../books/shared/books.service";
import {ClientsService} from "../../clients/shared/clients.service";
import {Book} from "../../books/shared/book.model";
import {Client} from "../../clients/shared/client.model"


@Component({
  selector: 'order-detail',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css'],
})

export class OrderDetailsComponent implements OnInit {
  errorMessage: string;
  @Input()
  order: Order;
  clients: Client[];
  books: Book[];

  constructor(private ordersService: OrdersService,
              private clientsService: ClientsService,
              private booksService: BooksService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.ordersService.getOrder(+params['id']))
      .subscribe(order => {
        console.log("got order: " + JSON.stringify({"order": order}));
        this.order = order;
      });
    this.clientsService.getClients()
      .subscribe(
        clients => this.clients = clients,
        error => this.errorMessage = <any>error
      );
    this.booksService.getBooks()
      .subscribe(
        books => this.books = books,
        error => this.errorMessage = <any>error
      );
  }

  goBack(): void {
    this.location.back();
  }


  delete(): void {
    this.ordersService.delete(this.order.id)
      .subscribe(_ => this.goBack());
  }

  update(client, book): void {
    console.log("saving order: client=" + client + ", book=" + book);
    if(!this.isValid(client.id, book.id)) {
      console.log("Invalid input");
      alert("Invalid input");
      return ;
    }
    this.ordersService.update(this.order.id, client.id, book.id)
      .subscribe(_ => this.goBack());
  }

  private isValid(clientId, bookId): boolean {
    if(!clientId || !bookId)
      return false;
    return true;
  }

}
