package model;

public class Client extends BaseEntity<Integer> {
    private String name, email, address;
    public Client() {
    }
    public Client(int id, String name, String email, String address) {
        this.setID(id);
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + "'" +
                ", email='" + email + "'" +
                ", address='" + address + "'" +
                "} " + super.toString();
    }
}
