package com.example.socket;

public class Product
{
    private String id;
    private int timeRate;
    private String lastQueueIn;
    Product(String id, int timeRate)
    {
        this.id = id;
        this.timeRate = timeRate;
    }
    public void setLastQueueIn(String lastQueueIn)
    {
        this.lastQueueIn = lastQueueIn;
    }
    public String getLastQueueIn()
    {
        return this.lastQueueIn;
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
