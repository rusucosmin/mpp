package ro.ubb.c04remotingjdbctemplate.common;

/**
 * Created by Petru on 4/5/2017.
 */
public class Order extends BaseEntity<Integer> {
    private static int nextID = 0;
    private Integer clientID;
    private Integer bookID;
    int cnt;

    public Order() {

    }

    public Order(Integer orderID, Integer clientID, Integer bookID, Integer cnt) {
        this.setID(orderID);
        this.clientID = clientID;
        this.bookID = bookID;
        this.cnt = cnt;
    }


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
