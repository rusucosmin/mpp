package model;

public class Book extends BaseEntity<Integer> {
    private String title, author;
    private int year, cnt;

    public Book() {
    }

    public Book(int id, String author, String title, int year, int cnt) {
        this.setID(id);
        this.title = title;
        this.author = author;
        this.year = year;
        this.cnt = cnt;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getCnt() {
        return cnt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", cnt=" + cnt +
                "} " + super.toString();
    }
}