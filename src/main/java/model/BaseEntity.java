package model;

public class BaseEntity<ID> {
    private ID id;

    public ID getID() {
        return id;
    }

    public void setID(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                "}";
    }
}
