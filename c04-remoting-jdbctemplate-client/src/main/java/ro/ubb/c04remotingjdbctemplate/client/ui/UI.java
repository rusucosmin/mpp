package ro.ubb.c04remotingjdbctemplate.client.ui;

import org.springframework.context.ApplicationContext;
import ro.ubb.c04remotingjdbctemplate.client.service.BookServiceClient;
import ro.ubb.c04remotingjdbctemplate.common.Book;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Petru on 4/5/2017.
 */
public class UI {
    private Scanner stdin;
    private BookServiceClient bookServiceClient;
    private ApplicationContext context;
    public UI(ApplicationContext context) {
        System.out.println("Hello from ui");
        this.context = context;
        this.bookServiceClient = context.getBean(BookServiceClient.class);
        this.stdin = new Scanner(System.in);
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

    public void readAndProcessCommand() {
        String commandID = this.readString("> ");

        switch(commandID) {
            case "1":
                System.out.println("command: print all books");
                this.printAllBooks();
                break;

            case "x":
                System.exit(0);
                break;
            default:
                System.out.println("bad command");
        }
        System.out.println();
    }

    private void printAllBooks() {
        List<Book> books = bookServiceClient.getBooks();
        books.stream().forEach(System.out::println);
    }

    public void showMenu() {
        System.out.println("BOOKS");
        System.out.println("-----");
        System.out.println("1. Print all");

        System.out.println("x. Exit");
    }

    public void start() {
        System.out.println(">-----Book Store-----<");

        while(true) {
            this.showMenu();
            this.readAndProcessCommand();
        }
    }
}
