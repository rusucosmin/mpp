import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {Client} from "../shared/client.model";
import {ClientsService} from "../shared/clients.service";


@Component({
    moduleId: module.id,
    selector: 'clients-list',
    templateUrl: './clients-list.component.html',
    styleUrls: ['./clients-list.component.css'],
})
export class ClientsListComponent implements OnInit {
    errorMessage: string;
    clients: Client[];
    selectedClient: Client;

    constructor(private clientsService: ClientsService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getClients();
    }

    getClients() {
        this.clientsService.getClients()
            .subscribe(
                clients => this.clients = clients,
                error => this.errorMessage = <any>error
            );
    }

    onSelect(client: Client): void {
        this.selectedClient = client;
    }

    gotoDetail(): void {
        this.router.navigate(['/client/detail', this.selectedClient.id]);
    }

    delete(client: Client): void {
      this.clientsService.delete(client.id)
        .subscribe(_ => window.location.reload());
    }

    edit(client: Client): void {
      this.router.navigate(["/client/detail", client.id]);
    }
}
