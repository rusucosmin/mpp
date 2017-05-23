import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { BooksComponent } from './books/books.component';
import { AppRoutingModule } from "./app-routing.module";
import { ClientsComponent } from './clients/clients.component';
import { BooksListComponent } from './books/books-list/books-list.component';
import { BookDetailComponent } from './books/book-detail/book-detail.component';
import { ClientsListComponent } from './clients/clients-list/clients-list.component';
import { ClientDetailsComponent } from './clients/client-details/client-details.component';

import { BooksService } from './books/shared/books.service';
import { ClientsService } from './clients/shared/clients.service';
import { OrdersComponent } from './orders/orders.component';
import { OrdersListComponent } from './orders/orders-list/orders-list.component';
import { OrderDetailsComponent } from './orders/order-details/order-details.component';
import { OrdersService } from "./orders/shared/orders.service";
import { BookNewComponent } from './books/book-new/book-new.component';
import { ClientNewComponent } from './clients/client-new/client-new.component';
import { OrderNewComponent } from './orders/order-new/order-new.component';

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    ClientsComponent,
    BooksListComponent,
    BookDetailComponent,
    ClientsListComponent,
    ClientDetailsComponent,
    OrdersComponent,
    OrdersListComponent,
    OrderDetailsComponent,
    BookNewComponent,
    ClientNewComponent,
    OrderNewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [BooksService, ClientsService, OrdersService],
  bootstrap: [AppComponent]
})
export class AppModule { }
