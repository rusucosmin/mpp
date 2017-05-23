import { Injectable } from '@angular/core';
import { Http, Response, Headers } from "@angular/http";

import { Order } from "./order.model";

import { Observable } from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class OrdersService {

  private ordersUrl = 'http://localhost:8080/api/orders';
  private headers = new Headers({"Content-Type": 'application/json'});

  constructor(private http: Http) {
  }

  getOrders(): Observable<Order[]> {
    return this.http.get(this.ordersUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.orders || {};
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

  getOrder(id: number): Observable<Order> {
    return this.getOrders()
      .map(orders => orders.find(order => order.id === id));
  }

  update(orderid, clientid, bookid) {
    let order = {clientid, bookid};
    const url = `${this.ordersUrl}/${orderid}`;
    return this.http
      .put(url, JSON.stringify({"order": order}), {headers: this.headers})
      .map(this.extractOrderData)
      .catch(this.handleError);
  }

  private extractOrderData(res: Response) {
    let body = res.json();
    return body.order || {};
  }

  create(clientid, bookid) {
    let order = {clientid, bookid};
    console.log("createRequest: " + JSON.stringify({"order": order}))
    return this.http
      .post(this.ordersUrl, JSON.stringify({"order": order}), {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  delete(orderId: number) {
    console.log('service delete: ' + orderId);
    const url = `${this.ordersUrl}/${orderId}`;
    return this.http
      .delete(url, {headers: this.headers})
      .map(() => null)
      .catch(this.handleError);
  }

}

