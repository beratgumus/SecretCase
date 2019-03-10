package modals;

public class Category {
    private Category parent;
    private String title;

    public Category(CategoryBuilder builder) {
        this.title = builder.title;
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

    public static class CategoryBuilder {
        private Category parent;
        private String title;

        public CategoryBuilder setParent(Category parent) {
            this.parent = parent;
            return this;
        }

        public CategoryBuilder setTitle(String title) {
            this.title = title;
            return this;
        }


    }
}