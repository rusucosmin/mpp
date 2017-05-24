import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BooksComponent }       from "./books/books.component";
import { BookDetailComponent }  from "./books/book-detail/book-detail.component";
import { BookNewComponent } from "./books/book-new/book-new.component";

import { ClientsComponent }       from "./clients/clients.component";
import { ClientDetailsComponent }  from "./clients/client-details/client-details.component";

import { OrdersComponent }       from "./orders/orders.component";
import { OrderDetailsComponent }  from "./orders/order-details/order-details.component";
import {ClientNewComponent} from "./clients/client-new/client-new.component";
import {OrderNewComponent} from "./orders/order-new/order-new.component";


const routes: Routes = [
    // { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'books',     component: BooksComponent },
    { path: 'book/detail/:id', component: BookDetailComponent},
    { path: 'book/new', component: BookNewComponent},

    { path: 'clients',     component: ClientsComponent },
    { path: 'client/detail/:id', component: ClientDetailsComponent},
    { path: 'client/new', component: ClientNewComponent},

    { path: 'orders',     component: OrdersComponent },
    { path: 'order/detail/:clientId/:bookId', component: OrderDetailsComponent},
    { path: 'order/new', component: OrderNewComponent},
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
