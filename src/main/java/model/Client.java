package model;

public class Client extends BaseEntity<Integer> {
    private  String name, email, adress;
    public Client() {
    }
    public Client(int id,String name, String email, String adress) {
        this.setID(id);
        this.name = name;
        this.email = email;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + "'" +
                ", email='" + email + "'" +
                ", adress='" + adress + "'" +
                "} " + super.toString();
    }
}
