package cz.vaclavhoblik.pocket.models;


/**
 * Category entity.
 * Represents value object (model).
 */
public class Category {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName().toString();
    }
}