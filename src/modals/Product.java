package modals;

public class Product {
    private String title;
    private Double price;



    private Category category;

    public Category getCategory() {
        return category;
    }
    public Double getPrice() {
        return price;
    }
    public Product(ProductBuilder builder) {
        this.title = builder.title;
        this.price = builder.price;
        this.category = builder.category;
    }

    public static class ProductBuilder {
        private String title;
        private Double price;
        private Category category;

        public static ProductBuilder newInstance() {
            return new ProductBuilder();
        }

        private ProductBuilder() {
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