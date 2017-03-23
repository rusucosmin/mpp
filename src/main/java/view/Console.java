package view;

import model.Book;
import model.Client;
import model.Order;
import model.validators.BookException;
import model.validators.ClientException;
import model.validators.ValidatorException;

import service.BookService;
import service.ClientService;
import service.OrderService;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.*;

public class Console {
    private BookService bookService;
    private ClientService clientService;
    private OrderService orderService;
    private Scanner stdin;

    /**
     * Console - class that executes the commands that the user dictates
     * @param bookService - to handle book operations
     * @param clientService - to handle client operations
     * @param orderService - to handle order operations
     */
    public Console(BookService bookService, ClientService clientService, OrderService orderService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.orderService = orderService;
        this.stdin = new Scanner(System.in);
    }

    /**
     *  Method prints all the available books
     */
    public void printAllBooks() {
        StreamSupport.stream(this.bookService.readAll().spliterator(), false)
                     .forEach(x -> System.out.println(x));
    }

    /**
     *  Method prints all the available clients
     */
    public void printAllClients() {
        StreamSupport.stream(this.clientService.readAll().spliterator(), false)
                     .forEach(x -> System.out.println(x));
    }

    /**
     *  Method prints all the available orders
     */
    public void printAllOrders() {
        StreamSupport.stream(this.orderService.readAll().spliterator(), false)
                     .forEach(x -> System.out.println(x));
    }

    /**
     *  Method reads an integer from stdin
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
        return this.stdin.nextLine();
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

        try {
            Book b = new Book(id, author, title, year, cnt);
            this.bookService.create(b);
        } catch(ValidatorException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Method adds a new client from the user
     */
    public void addClient() {
        int id = this.readInt("Client ID: ", "Client ID must be an integer");
        String name = this.readString("Name: ");
        String email = this.readString("Email: ");
        String address = this.readString("Address: ");

        try {
            Client c = new Client(id, name, email, address);
            this.clientService.create(c);
        } catch(ValidatorException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Method adds a new order from the user
     */
    public void addOrder() {
        // int id = this.readInt("Client ID: ", "Client ID must be an integer");
        int clientID = this.readInt("Client ID: ", "Client ID must be an integer");
        int bookID = this.readInt("Book ID: ", "Book ID must be an integer");
        int cnt = this.readInt("Quantity: ", "Quantity must be an integer");

        try {
            Order x = new Order(clientID, bookID, cnt);
            this.orderService.create(x);
        } catch(ValidatorException e) {
            e.printStackTrace(System.out);
        }
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

        System.out.println("CLIENTS");
        System.out.println("-------");
        System.out.println("9.  Print all");
        System.out.println("10. Add");
        System.out.println("11. Update");
        System.out.println("12. Delete");
        System.out.println();

        System.out.println("x. Exit");
    }

    /**
     * Method read and process command
     */
    public void readAndProcessCommand() {
        System.out.print("> ");
        String commandID = this.stdin.nextLine();

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
            case "x":
                System.exit(0);
                break;
            default:
                System.out.println("bad command");
        }
        System.out.println();
    }

    private void deleteBook() {
        int id = this.readInt("Book ID: ", "Book ID must be an integer");
        Optional<Book> opt = this.bookService.delete(id);
        if(opt.isPresent())
            System.out.println("Successfully removed Book:\n" + opt.get().toString());
        else
            System.out.println("Book ID not present");
    }

    private void deleteClient() {
        int id = this.readInt("Client ID: ", "Client ID must be an integer");
        Optional<Client> opt = this.clientService.delete(id);
        if(opt.isPresent())
            System.out.println("Successfully removed Client:\n" + opt.get().toString());
        else
            System.out.println("Client ID not present");
    }

    private void deleteOrder() {
        int orderID = this.readInt("Order ID: ", "Order ID must be an integer");
        Optional<Order> opt = this.orderService.delete(orderID);
        if(opt.isPresent())
            System.out.println("Successfully removed Order:\n" + opt.get().toString());
        else
            System.out.println("Order ID not present");
    }

    private void updateBook() {
        int id = this.readInt("Book ID: ", "Book ID must be an integer");
        Optional<Book> opt = this.bookService.read(id);
        if(opt.isPresent()) {
            String author = this.readString("Author: ");
            String title = this.readString("Title: ");
            int year = this.readInt("Year: ", "Publish year must be an integer");
            int cnt = this.readInt("Quantity: ", "Book quantity must be an integer");
            try {
                Book b = new Book(id, author, title, year, cnt);
                this.bookService.update(b);
            } catch(BookException e) {
                e.printStackTrace(System.out);
            }
        }
        else {
            System.out.println("Book ID not present");
        }
    }

    private void updateClient() {
        int id = this.readInt("Client ID: ", "Client ID must be an integer");
        Optional<Client> opt = this.clientService.read(id);
        if(opt.isPresent()) {
            String name = this.readString("Name: ");
            String email = this.readString("Email: ");
            String address = this.readString("Address: ");

            try {
                Client c = new Client(id, name, email, address);
                this.clientService.update(c);
            } catch(ClientException e) {
                e.printStackTrace(System.out);
            }
        }
        else {
            System.out.println("Client ID not present");
        }
    }

    private void updateOrder() {
        int orderID = this.readInt("Order ID: ", "Order ID must be an integer");
        Optional<Order> opt = this.orderService.read(orderID);
        if(opt.isPresent()) {

            int clientID = this.readInt("Client ID: ", "Client ID must be an integer");
            int bookID = this.readInt("Book ID: ", "Book ID must be an integer");
            int cnt = this.readInt("Quantity: ", "Quantity must be an integer");

            try {
                Order x = new Order(orderID, clientID, bookID, cnt);
                this.orderService.update(x);
            } catch(ClientException e) {
                e.printStackTrace(System.out);
            }
        }
        else {
            System.out.println("Order ID not present");
        }
    }

    private void searchBook() {
        String searchTerm = this.readString("Search term: ");

        StreamSupport.stream(this.bookService.readAll().spliterator(), false)
            .filter(x -> x.getTitle().contains(searchTerm) ||
                         x.getAuthor().contains(searchTerm))
            .forEach(x -> System.out.println(x));
    }

    private void searchClient() {
        String searchTerm = this.readString("Search term: ");

        StreamSupport.stream(this.clientService.readAll().spliterator(), false)
            .filter(x -> x.getName().contains(searchTerm) ||
                         x.getAddress().contains(searchTerm) ||
                         x.getEmail().contains(searchTerm))
            .forEach(x -> System.out.println(x));
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
