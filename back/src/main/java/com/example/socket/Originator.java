package com.example.socket;

import java.util.Random;

public class Originator
{
    private int min = 3000;
    private int max = 15000;
    private Random random = new Random();
    private int timeRate;
    public Product makeProduct(String id)
    {

       int n =this.random.nextInt((this.max - this.min) + 1) + this.min;
       System.out.println("time");
       System.out.println(n);
        Product product = new Product(id, n);
        return product;
    }
}
