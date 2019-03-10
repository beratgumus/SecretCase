package services;


import modals.Product;

public interface ShoppingCartService {
    void addItem(Product product, int quantity);

}