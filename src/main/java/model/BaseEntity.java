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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity<?> that = (BaseEntity<?>) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
