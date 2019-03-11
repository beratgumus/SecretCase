package models;

import java.util.Objects;

public class Category {
    private Category parent;
    private String title;

    public Category(String title) {
        this.title = title;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getParent(), category.getParent()) &&
                Objects.equals(getTitle(), category.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParent(), getTitle());
    }
}