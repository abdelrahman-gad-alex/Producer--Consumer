package com.example.socket;

public class Product
{
    private String id;
    private int timeRate;
    Product(String id, int timeRate)
    {
        this.id = id;
        this.timeRate = timeRate;
    }
    public String getId()
    {
        return id;
    }
    public int getTimeRate()
    {
        return timeRate;
    }
}
