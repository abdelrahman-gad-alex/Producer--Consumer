package com.example.socket;

import java.util.Random;

public class Originator
{
    private int min = 1000;
    private int max = 5000;
    private Random random = new Random();
    private int timeRate;
    public Product makeProduct(String id)
    {
        Product product = new Product(id, this.random.nextInt((this.max - this.min) + 1) + this.min);
        return product;
    }
}
