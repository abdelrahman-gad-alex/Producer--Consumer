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
        products.addLast(product);
    }
    public Product getProduct()
    {
        Product product = products.getFirst();
        products.removeFirst();
        products.addLast(product);
        return product;
    }
}
