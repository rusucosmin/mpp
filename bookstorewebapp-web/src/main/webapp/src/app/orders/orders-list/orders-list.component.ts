import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {Order} from "../shared/order.model";
import {OrdersService} from "../shared/orders.service";


@Component({
  moduleId: module.id,
  selector: 'orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css'],
})
export class OrdersListComponent implements OnInit {
  errorMessage: string;
  orders: Order[];
  selectedOrder: Order;

  constructor(private ordersService: OrdersService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders() {
    this.ordersService.getOrders()
      .subscribe(
        orders => this.orders = orders,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(order: Order): void {
    this.selectedOrder = order;
  }

  gotoDetail(): void {
    this.router.navigate(['/order/detail', this.selectedOrder.id]);
  }

  edit(order: Order): void {
    this.router.navigate(['/order/detail', order.id]);
  }

  delete(order: Order): void {
    console.log("deleting order " + order.id);
    this.ordersService.delete(order.id)
      .subscribe(_ => window.location.reload());
  }
}
