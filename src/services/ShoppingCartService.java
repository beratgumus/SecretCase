package services;


import models.Product;

public interface ShoppingCartService {
    void addItem(Product product, int quantity);

}