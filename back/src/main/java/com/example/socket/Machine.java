package com.example.socket;
import java.util.*;

public class Machine implements Observable, Runnable
{
    Thread thread;
    HashMap<String,String> sent= new HashMap<String,String>();
    trying tg;
    HashMap<String,String> rec=new HashMap<String,String>();
    private Product currentProduct;
    private String id;
    private LinkedList<Queue> queuesBefore;
    private Queue queueAfter;
    private int time;
    private boolean isEmpty = true;
    private int min = 3000;
    private int max = 8000;
    Random random = new Random();
    public Machine(String id, Queue queueAfter, trying tt)
    {
        this.tg=tt;
        this.id = id;
        this.queuesBefore = new LinkedList<Queue>();
        this.queueAfter = queueAfter;
        this.isEmpty = true;
        time = this.random.nextInt((max - min) + 1) + min;

    }
    public void notifyAllObservers() throws InterruptedException
    {
        for (Queue queue : this.queuesBefore) {

            queue.update();
        }

        this.queueAfter.update();
    }
    public String getId()
    {
        return this.id;
    }
    public void addQueueBefore(Queue q)
    {
        this.queuesBefore.add(q);
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

        this.thread = new Thread(this, "Thread " + this.id);
        this.thread.start();

        this.produce();
    }
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
        rec.put("product",this.currentProduct.getId());
        rec.put("out",this.currentProduct.getLastQueueIn());
        rec.put("in",this.id);
        this.tg.send2(rec);
        this.isEmpty=false;
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
        String in ;
        String out;
        String product;
        out=getId();
        product= this.currentProduct.getId();
        in=this.currentProduct.getLastQueueIn();

       sent.put("product",product);
        sent.put("in",in);
        sent.put("out",out);
      this.tg.send2(sent);
        this.thread.join();
        this.notifyAllObservers();
    }
    public void clear()
    {
        rec.clear();
        sent.clear();
    }
    public void run()
    {
        try
        {
            this.notifyAllObservers();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
