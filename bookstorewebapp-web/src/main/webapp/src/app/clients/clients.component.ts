import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  add(): void {
    this.router.navigate(["/client/new"]);
  }

}
