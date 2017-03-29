package ro.ubb.bookstore.common.model;

/**
 * Book Class
 */
public class Book extends BaseEntity<Integer> {
    private String title, author;
    private Integer year, cnt;

    public Book() {
    }

    public Book(Integer id, String author, String title, Integer year, Integer cnt) {
        this.setID(id);
        this.title = title;
        this.author = author;
        this.year = year;
        this.cnt = cnt;
    }

    /**
     * Method returns the book title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method return the book author
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Method return the book year
     * @return
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Method return the number of copies of each book
     * @return
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * Method sets the title of the book
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method sets the author of the book
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Method sets the year when the book was written
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Method sets the number of copies of the book
     * @param cnt
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     * Utils method to pretty-print a book
     * @return
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", cnt=" + cnt +
                "} " + super.toString();
    }
}
