package com.example.socket;

import java.util.LinkedList;

public class Queue implements Observer
{
    String id;
    private LinkedList<Machine> machines;
    private LinkedList<String> products;
    public Queue(String id)
    {
        machines = new LinkedList<Machine>();
        products = new LinkedList<String>();
        this.id = id;
    }
    @Override
    public void update() throws InterruptedException
    {
        sendProduct();
    }
    public void setProducts(LinkedList<String> products)
    {
        this.products = products;
    }
    public void setMachines(LinkedList<Machine> machines)
    {
        this.machines = machines;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public void addProduct(String product)
    {
        products.add(product);
    }
    public void addMachine(Machine machine)
    {
        machines.add(machine);
    }
    public LinkedList<Machine> getMachines()
    {
        return machines;
    }
    public LinkedList<String> getProducts()
    {
        return products;
    }
    public String getId()
    {
        return id;
    }
    public void removeProducts()
    {
        this.products.clear();
    }
    public void print()
    {
        System.out.println(products.getLast());
    }
    public void sendProduct() throws InterruptedException
    {
        for (Machine machine : machines)
        {
            if (machine.getIsEmpty())
            {
                if (products.isEmpty())
                {
                    return;
                }
                String product = products.removeLast();
                machine.setIsEmpty(false);
                machine.setCurrentProduct(product);
            }
        }
    }
}
