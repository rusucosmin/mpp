package view;

import model.Book;
import model.Client;
import model.validators.ValidatorException;

import service.BookService;
import service.ClientService;


import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.*;

public class Console {
    private BookService bookService;
    private ClientService clientService;
    private Scanner stdin;

    public Console(BookService bookService, ClientService clientService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.stdin = new Scanner(System.in);
    }

    public void printAllBooks() {
        StreamSupport.stream(this.bookService.readAll().spliterator(), false)
                     .forEach(x -> System.out.println(x));
    }

    public void printAllClients() {
        StreamSupport.stream(this.clientService.readAll().spliterator(), false)
                     .forEach(x -> System.out.println(x));
    }

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

    public String readString(String prompt) {
        System.out.print(prompt);
        return this.stdin.nextLine();
    }


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

    public void showMenu() {
        System.out.println("1. Print all books");
        System.out.println("2. Print all clients");
        System.out.println("3. Add book");
        System.out.println("4. Add client");
        System.out.println("x. Exit");
    }

    public void readAndProcessCommand() {
        System.out.print("> ");
        String commandID = this.stdin.nextLine();

        switch(commandID) {
            case "1":
                System.out.println("command: print all books");
                this.printAllBooks();
                break;
            case "2":
                System.out.println("command: print all clients");
                this.printAllClients();
                break;
            case "3":
                System.out.println("command: add book");
                this.addBook();
                break;
            case "4":
                System.out.println("command: add client");
                this.addClient();
                break;
            case "x":
                System.exit(0);
                break;
            default:
                System.out.println("bad command");
        }
        System.out.println();
    }


    public void run() {

        System.out.println(">-----Book Store-----<");

        while(true) {
            this.showMenu();
            this.readAndProcessCommand();
        }

    }
}
