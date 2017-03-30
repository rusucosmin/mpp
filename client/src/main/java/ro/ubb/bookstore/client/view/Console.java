package ro.ubb.bookstore.client.view;

import ro.ubb.bookstore.common.model.BaseEntity;
import ro.ubb.bookstore.common.model.Book;
import ro.ubb.bookstore.common.model.Client;
import ro.ubb.bookstore.common.model.Order;
import ro.ubb.bookstore.common.service.IBookService;
import ro.ubb.bookstore.common.service.IClientService;
import ro.ubb.bookstore.common.service.IOrderService;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.stream.StreamSupport;

public class Console {
    private IBookService bookService;
    private IClientService clientService;
    private IOrderService orderService;
    private Scanner stdin;

    /**
     * Console - class that executes the commands that the user dictates
     * @param bookService - to handle book operations
     * @param clientService - to handle client operations
     * @param orderService - to handle order operations
     */
    public Console(IBookService bookService, IClientService clientService, IOrderService orderService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.orderService = orderService;
        this.stdin = new Scanner(System.in);
    }

    private void waitForFuture(CompletableFuture t) throws InterruptedException {
        System.out.println("Waiting for response...");
        while (!t.isDone()) {
            Thread.sleep(2);
        }
    }

    private <ID, T extends BaseEntity<ID>> void print(CompletableFuture<Iterable<T>> future) {
        try {
            this.waitForFuture(future);
            Iterable<T> iter = future.get();
            StreamSupport.stream(iter.spliterator(), false)
                         .forEach(x -> System.out.println(x));
        } catch(Exception e) {
            System.out.println("There was an exception while getting all the entities");
            e.printStackTrace();
        }
    }

    /**
     *  Method prints all the available books
     */
    public void printAllBooks() {
        CompletableFuture<Iterable<Book>> future = this.bookService.readAll();
        this.print(future);
    }

    /**
     *  Method prints all the available clients
     */
    public void printAllClients() {
        CompletableFuture<Iterable<Client>> future = this.clientService.readAll();
        this.print(future);
    }

    /**
     *  Method prints all the available orders
     */
    public void printAllOrders() {
        CompletableFuture<Iterable<Order>> future = this.orderService.readAll();
        this.print(future);
    }

    /**
     * Method reads an integer from stdin
     * @param prompt - message to show before input
     * @param errorMessage - message to show in case of an error (a non integer number inputted)
     * @return the number that was read
     */
    public int readInt(String prompt, String errorMessage) {
        int ret;
        while(true) {
            System.out.print(prompt);
            try {
                ret = Integer.parseInt(this.stdin.nextLine());
                return ret;
            } catch(Exception e) {
                System.out.printf("Error: %s\n", errorMessage);
            }
        }
    }

    /**
     * Method reads a string from stdin
     * @param prompt - message to show before input
     * @return the string that was read
     */
    public String readString(String prompt) {
        System.out.print(prompt);
        String ret = this.stdin.nextLine();

        System.out.println();
        return ret;
    }

    private<ID, T extends BaseEntity<ID>> void handleSave(CompletableFuture<Optional<T>> future) {
        try {
            this.waitForFuture(future);
            Optional<T> opt = future.get();
            if(opt.isPresent())
                System.out.println("There is already an entity with the same id: " + opt.get().toString());
            else
                System.out.println("Successfully added");
        } catch(Exception e) {
            System.out.println("Error while saving entity");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to add a new book from the user
     */
    public void addBook() {
        int id = this.readInt("Book ID: ", "Book ID must be an integer");
        String author = this.readString("Author: ");
        String title = this.readString("Title: ");
        int year = this.readInt("Year: ", "Publish year must be an integer");
        int cnt = this.readInt("Quantity: ", "Book quantity must be an integer");

        Book book = new Book(id, author, title, year, cnt);
        CompletableFuture<Optional<Book>> future = bookService.create(book);
        this.handleSave(future);
    }

    /**
     * Method adds a new client from the user
     */
    public void addClient() {
        int id = this.readInt("Client ID: ", "Client ID must be an integer");
        String name = this.readString("Name: ");
        String email = this.readString("Email: ");
        String address = this.readString("Address: ");

        Client client = new Client(id, name, email, address);
        CompletableFuture<Optional<Client>> future = clientService.create(client);
        this.handleSave(future);
    }

    /**
     * Method adds a new order from the user
     */
    public void addOrder() {
        int id = this.readInt("Order ID: ", "Order ID must be an integer");
        int clientID = this.readInt("Client ID: ", "Client ID must be an integer");
        int bookID = this.readInt("Book ID: ", "Book ID must be an integer");
        int cnt = this.readInt("Quantity: ", "Quantity must be an integer");

        Order order = new Order(id, clientID, bookID, cnt);
        CompletableFuture<Optional<Order>> future = this.orderService.create(order);
        this.handleSave(future);
    }

    /**
     * Method shows the main menu
     */
    public void showMenu() {
        System.out.println("BOOKS");
        System.out.println("-----");
        System.out.println("1. Print all");
        System.out.println("2. Add");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("sb. Search");
        System.out.println();

        System.out.println("CLIENTS");
        System.out.println("-------");
        System.out.println("5. Print all");
        System.out.println("6. Add");
        System.out.println("7. Update");
        System.out.println("8. Delete");
        System.out.println("sc. Search");
        System.out.println();

        System.out.println("ORDERS");
        System.out.println("-------");
        System.out.println("9.  Print all");
        System.out.println("10. Add");
        System.out.println("11. Update");
        System.out.println("12. Delete");
        System.out.println();

        System.out.println("MISCELLANEOUS");
        System.out.println("-------");
        System.out.println("13. Sort clients by number of ordered books");

        System.out.println("x. Exit");
    }

    /**
     * Method read and process command
     */
    public void readAndProcessCommand() {
        String commandID = this.readString("> ");

        switch(commandID) {
            case "1":
                System.out.println("command: print all books");
                this.printAllBooks();
                break;
            case "2":
                System.out.println("command: add book");
                this.addBook();
                break;
            case "3":
                System.out.println("command: update book");
                this.updateBook();
                break;
            case "4":
                System.out.println("command: delete book");
                this.deleteBook();
                break;
            case "sb":
                System.out.println("command: search book");
                this.searchBook();
                break;
            case "5":
                System.out.println("command: print all clients");
                this.printAllClients();
                break;
            case "6":
                System.out.println("command: add client");
                this.addClient();
                break;
            case "7":
                System.out.println("command: update client");
                this.updateClient();
                break;
            case "8":
                System.out.println("command: delete client");
                this.deleteClient();
                break;
            case "sc":
                System.out.println("command: search client");
                this.searchClient();
                break;
            case "9":
                System.out.println("command: print all orders");
                this.printAllOrders();
                break;
            case "10":
                System.out.println("command: add order");
                this.addOrder();
                break;
            case "11":
                System.out.println("command: update order");
                this.updateOrder();
                break;
            case "12":
                System.out.println("command: delete order");
                this.deleteOrder();
                break;
            case "13":
                System.out.println("command: sort clients");
                this.sortClients();
                break;
            case "x":
                System.exit(0);
                break;
            default:
                System.out.println("bad command");
        }
        System.out.println();
    }

    private void sortClients() {
        /*
        List<Map.Entry<Client, Integer>> list =
                orderService.getStatistics();
        list.stream().forEach(x -> System.out.println(x.getKey() + " total: " + x.getValue()));
        */
    }

    private <ID, T extends BaseEntity<ID>> void handleDelete(CompletableFuture<Optional<T>> future) {
        try {
            this.waitForFuture(future);
            Optional<T> opt = future.get();
            if(opt.isPresent())
                System.out.println("Successfully deleted item: " + opt.get().toString());
            else
                System.out.println("No such item.");
        }catch(Exception e) {
            System.out.println("There was an error while trying to delete an entity");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    private void deleteBook() {
        int id = this.readInt("Book ID: ", "Book ID must be an integer");
        CompletableFuture<Optional<Book>> future = this.bookService.delete(id);
        this.handleDelete(future);
    }

    private void deleteClient() {
        int id = this.readInt("Client ID: ", "Client ID must be an integer");
        CompletableFuture<Optional<Client>> future = this.clientService.delete(id);
        this.handleDelete(future);
    }

    private void deleteOrder() {
        int orderID = this.readInt("Order ID: ", "Order ID must be an integer");
        CompletableFuture<Optional<Order>> future = this.orderService.delete(orderID);
        this.handleDelete(future);
    }

    private <T extends BaseEntity<Integer>> void handleUpdate(CompletableFuture<Optional<T>> future) {
        try {
            this.waitForFuture(future);
            Optional<T> opt = future.get();
            if(!opt.isPresent())
                System.out.println("Successfully updated item.");
            else
                System.out.println("No such item " + opt.get().toString());
        } catch(Exception e) {
            System.out.println("There was an error while updating the entity");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    private void updateBook() {
        int id = this.readInt("Book ID: ", "Book ID must be an integer");
        String author = this.readString("Author: ");
        String title = this.readString("Title: ");
        int year = this.readInt("Year: ", "Publish year must be an integer");
        int cnt = this.readInt("Quantity: ", "Book quantity must be an integer");
        Book b = new Book(id, author, title, year, cnt);
        CompletableFuture<Optional<Book>> future = bookService.update(b);
        this.handleUpdate(future);
    }

    private void updateClient() {
        int id = this.readInt("Client ID: ", "Client ID must be an integer");
        String name = this.readString("Name: ");
        String email = this.readString("Email: ");
        String address = this.readString("Address: ");

        Client c = new Client(id, name, email, address);
        CompletableFuture<Optional<Client>> future = clientService.update(c);
        this.handleUpdate(future);
    }

    private void updateOrder() {
        int orderID = this.readInt("Order ID: ", "Order ID must be an integer");
        int clientID = this.readInt("Client ID: ", "Client ID must be an integer");
        int bookID = this.readInt("Book ID: ", "Book ID must be an integer");
        int cnt = this.readInt("Quantity: ", "Quantity must be an integer");

        Order x = new Order(orderID, clientID, bookID, cnt);
        CompletableFuture<Optional<Order>> future = orderService.update(x);
        this.handleUpdate(future);
    }

    private void searchBook() {
        String searchTerm = this.readString("Search term: ");
        try {
            CompletableFuture<Iterable<Book>> future = this.bookService.readAll();
            future.thenAccept((iterable) -> {
                StreamSupport.stream(iterable.spliterator(), false)
                    .filter(x -> x.getTitle().contains(searchTerm) ||
                                 x.getAuthor().contains(searchTerm))
                    .forEach(x -> System.out.println(x));
            });
            this.waitForFuture(future);
        } catch (Exception e) {
            System.out.println("There was an erroe while searching...");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    private void searchClient() {
        String searchTerm = this.readString("Search term: ");

        try {
            CompletableFuture<Iterable<Client>> future = this.clientService.readAll();
            future.thenAccept((iterable) -> {
            StreamSupport.stream(iterable.spliterator(), false)
                    .filter(x -> x.getName().contains(searchTerm) ||
                             x.getAddress().contains(searchTerm) ||
                             x.getEmail().contains(searchTerm))
                    .forEach(x -> System.out.println(x));
            });
            this.waitForFuture(future);
        } catch (Exception e) {
            System.out.println("There was an error while searching...");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    /**
     * Main event loop
     */
    public void run() {
        System.out.println(">-----Book Store-----<");

        while(true) {
            this.showMenu();
            this.readAndProcessCommand();
        }

    }
}
