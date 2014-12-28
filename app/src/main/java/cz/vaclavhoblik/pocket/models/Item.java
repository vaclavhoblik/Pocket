package cz.vaclavhoblik.pocket.models;


/**
 * Item entity.
 * Represents value object (model).
 */
public class Item {

    private long id;

    private Float value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}