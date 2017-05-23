import { Component, OnInit, Input } from '@angular/core';
import { ClientsService } from "../shared/clients.service";
import { Client } from "../shared/client.model"
import { Location } from '@angular/common'

@Component({
  selector: 'app-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent implements OnInit {
  @Input() client: Client;

  constructor(private clientService: ClientsService,
              private location: Location) { }

  ngOnInit() {

  }

  goBack(): void {
    this.location.back();
  }

  save(name): void {
    console.log("saving client: " + name);
    if(!this.isValid(name)) {
      console.log("invalid input");
      alert("invalid input");
      return;
    }
    this.clientService.create(name)
          .subscribe(_ => this.goBack());
  }

  private isValid(name): boolean {
    //TODO: check if all whitespace
    if(!name)
      return false;
    return true;
  }
}
