package view;

import model.Book;
import model.Client;

import service.BookService;
import service.ClientService;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.*;

public class Console {
    private BookService bookService;
    private ClientService clientService;

    public Console(BookService bookService, ClientService clientService) {
        this.bookService = bookService;
        this.clientService = clientService;
    }

    public void printAllBooks() {
        StreamSupport.stream(this.bookService.readAll().spliterator(), false)
                     .forEach(x -> System.out.println(x));
    }

    public void addBook() {
        //TODO
    }

    public void showMenu() {
        System.out.println("1. Print all books");
        System.out.println("2. Add book (not implemented)");
        System.out.println("x. Exit");
    }

    public void readAndProcessCommand(Scanner in) {
        System.out.print("> ");
        String commandID = in.nextLine();

        switch(commandID) {
            case "1":
                System.out.println("command: print all books");
                this.printAllBooks();
                break;
            case "2":
                System.out.println("add book (not implemented");
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
        Scanner in = new Scanner(System.in);

        System.out.println(">-----Book Store-----<");

        while(true) {
            this.showMenu();
            this.readAndProcessCommand(in);
        }

    }
}
