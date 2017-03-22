package model;

/**
 * Class to store an Order (that is a client-book pair)
 */
public class Order extends BaseEntity<Integer> {
    private Integer clientID;
    private Integer bookID;
    int cnt;

    public Order(Integer clientID, Integer bookID, Integer cnt) {
        this.clientID = clientID;
        this.bookID = bookID;
        this.cnt = cnt;
    }

    /*Getters and setters*/
    public Integer getClientID() {
        return clientID;
    }

    public Integer getBookID() {
        return bookID;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientID=" + clientID +
                ", bookID=" + bookID +
                ", cnt=" + cnt +
                "} " + super.toString();
    }
}