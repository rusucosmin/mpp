import {Book} from "../../books/shared/book.model";
import {Client} from "../../clients/shared/client.model";

export class Order {
  clientId: number;
  bookId: number;
  clientName: string;
  bookName: string;
}
