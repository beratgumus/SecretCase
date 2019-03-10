package modals;

public class Product {
    private String title;
    private Double price;


    private Category category;

    public Product(ProductBuilder builder) {
        this.title = builder.title;
        this.price = builder.price;
        this.category = builder.category;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public static class ProductBuilder {
        private String title;
        private Double price;
        private Category category;

        private ProductBuilder() {
        }

        public static ProductBuilder newInstance() {
            return new ProductBuilder();
        }

        public ProductBuilder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public ProductBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ProductBuilder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}