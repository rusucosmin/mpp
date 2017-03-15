package model;

/**
 * BaseEntity - class with a unique id field
 * @param <ID> = type of the ID
 */
public class BaseEntity<ID> {
    private ID id;

    /**
     *  Returns the id of the current entity
     * @return
     */
    public ID getID() {
        return id;
    }

    /**
     * Sets the id of the current entity
     * @param id
     */
    public void setID(ID id) {
        this.id = id;
    }

    /**
     * Pretty print the object
     * @return
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                "}";
    }
}
