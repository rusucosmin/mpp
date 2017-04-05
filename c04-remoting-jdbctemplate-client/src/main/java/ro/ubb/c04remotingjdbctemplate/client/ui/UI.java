package ro.ubb.c04remotingjdbctemplate.client.ui;

import org.springframework.context.ApplicationContext;
import ro.ubb.c04remotingjdbctemplate.client.service.BookServiceClient;
import ro.ubb.c04remotingjdbctemplate.client.service.ClientServiceClient;
import ro.ubb.c04remotingjdbctemplate.common.Book;
import ro.ubb.c04remotingjdbctemplate.common.Client;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Petru on 4/5/2017.
 */
public class UI {
    private Scanner stdin;
    private BookServiceClient bookServiceClient;
    private ClientServiceClient clientServiceClient;
    private ApplicationContext context;
    public UI(ApplicationContext context) {
        System.out.println("Hello from ui");
        this.context = context;
        this.bookServiceClient = context.getBean(BookServiceClient.class);
        this.clientServiceClient = context.getBean(ClientServiceClient.class);
        this.stdin = new Scanner(System.in);
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

    /**
     * Method to add a new book from the user
     */
    public void addBook() {
        int id = this.readInt("book ID: ", "book ID must be an integer");
        String author = this.readString("Author: ");
        String title = this.readString("Title: ");
        int year = this.readInt("Year: ", "Publish year must be an integer");
        int cnt = this.readInt("Quantity: ", "book quantity must be an integer");

        Book book = new Book(id, author, title, year, cnt);
        bookServiceClient.addBook(book);
    }

    private void addClient() {
        int id = this.readInt("client ID: ", "client ID must be an integer");
        String name = this.readString("Name: ");
        String email = this.readString("Email: ");
        String address = this.readString("Address: ");

        Client client = new Client(id, name, email, address);
        clientServiceClient.addClient(client);
    }

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

            case "5":
                System.out.println("command: print all clients");
                this.printAllClients();
                break;
            case "6":
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



    private void printAllClients() {
        List<Client> clients = this.clientServiceClient.getClients();
        clients.stream().forEach(System.out::println);
    }

    private void printAllBooks() {
        List<Book> books = bookServiceClient.getBooks();
        books.stream().forEach(System.out::println);
    }

    public void showMenu() {
        System.out.println("BOOKS");
        System.out.println("-----");
        System.out.println("1. Print all");
        System.out.println("2. Add");


        System.out.println("CLIENTS");
        System.out.println("-------");
        System.out.println("5. Print all");
        System.out.println("6. Add");

        System.out.println("x. Exit");
    }

    public void start() {
        System.out.println(">-----book Store-----<");

        while(true) {
            this.showMenu();
            this.readAndProcessCommand();
        }
    }
}
