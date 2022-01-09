package com.example.socket;

import java.util.LinkedList;

public class CareTaker
{
    LinkedList<Product> products;
    public CareTaker()
    {
        products = new LinkedList<Product>();
    }
    public void addProduct(Product product)
    {
        products.add(product);
    }
    public Product getProduct()
    {
        Product product = products.getLast();
        products.removeLast();
        return product;
    }
}
