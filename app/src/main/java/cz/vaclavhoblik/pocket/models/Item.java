package cz.vaclavhoblik.pocket.models;


/**
 * Item entity.
 * Represents value object (model).
 */
public class Item {

    private long id;

    private Float value;

    private Integer date;

    private Integer categoryId;

    // ==== Setters and getters ====

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

    public Integer getDate() {
        return this.date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer id) {
        this.categoryId = id;
    }

    @Override
    public String toString() {
        return this.getValue().toString() +  ";" + this.getDate().toString() ;
    }
}