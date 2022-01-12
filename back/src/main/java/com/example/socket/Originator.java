package com.example.socket;

import java.util.Random;

public class Originator
{
    private int min = 2000;
    private int max = 5000;
    private Random random = new Random();
    private int timeRate;
    public Product makeProduct(String id)
    {
       int n =this.random.nextInt((this.max - this.min) + 1) + this.min;
        Product product = new Product(id, n);
        return product;
    }
}
