package ro.ubb.bookstore.common.model;

/**
 * Client Class to represent a Client
 */
public class Client extends BaseEntity<Integer> {
    private String name, email, address;
    public Client() {
    }
    public Client(Integer id, String name, String email, String address) {
        this.setID(id);
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Returns the name of the Client
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the client email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the client address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *  Method sets the name of the client
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method sets the email of the client
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method sets the client address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * ToString util method
     * @return
     */
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + "'" +
                ", email='" + email + "'" +
                ", address='" + address + "'" +
                "} " + super.toString();
    }
}
