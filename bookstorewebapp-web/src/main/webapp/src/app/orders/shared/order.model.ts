import {Book} from "../../books/shared/book.model";
import {Client} from "../../clients/shared/client.model";

export class Order {
  id: number;
  client: Client;
  book: Book;
}
