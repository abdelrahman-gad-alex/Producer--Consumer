package com.example.socket;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Machine implements Observable, Runnable
{

    Thread thread;
    private Product currentProduct;
    private String id;
    private LinkedList<Queue> queuesBefore;
    private Queue queueAfter;
    private int time;
    private boolean isEmpty = true;
    private int min = 1000;
    private int max = 9000;
    public Machine(String id, Queue queueAfter, LinkedList<Queue> queueBefore)
    {
        Random r = new Random();
        time = r.nextInt((max - min) + 1) + min;
        this.id = id;
        this.queueAfter = queueAfter;
        this.queuesBefore = queueBefore;
        this.addToQueues(this);
        this.isEmpty = true;
    }
    public void notifyAllObservers() throws InterruptedException
    {
        for (Queue queue : this.queuesBefore) {
            queue.update();
        }
        this.queueAfter.update();
    }
    public void addToQueues(Machine machine)
    {
        for (Queue q : this.queuesBefore)
        {
            q.addMachine(machine);
        }
    }
    public Product getCurrentProduct()
    {
        return this.currentProduct;
    }
    public Queue getQueueAfter()
    {
        return this.queueAfter;
    }
    public void setCurrentProduct(Product currentProduct) throws InterruptedException
    {
        this.currentProduct = currentProduct;
        System.out.println(Thread.getAllStackTraces().keySet().size());
        this.thread = new Thread(this, "Thread " + this.id);
        this.thread.start();
        System.out.println(Thread.getAllStackTraces().keySet().size());
        this.produce();
    }
//        public LinkedList<Queue> getQueues() {
//            return this.queues;
//        }
    public void setIsEmpty(boolean isEmpty)
    {
        this.isEmpty = isEmpty;
    }
    public boolean getIsEmpty()
    {
        return this.isEmpty;
    }
    public void produce() throws InterruptedException
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run() {
                try
                {
                    consume();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }, time);
    }
    public void consume() throws InterruptedException
    {
        this.isEmpty = true;
        this.queueAfter.addProduct(this.currentProduct);
        this.thread.join();
        System.out.println(this.thread.isAlive());
        this.notifyAllObservers();
    }
    public void run()
    {
        try
        {
            this.notifyAllObservers();
        }
        catch (InterruptedException var2)
        {
            var2.printStackTrace();
        }
    }
}
