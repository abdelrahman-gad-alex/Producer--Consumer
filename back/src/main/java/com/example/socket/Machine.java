package com.example.socket;
import java.util.*;

public class Machine implements Observable, Runnable
{

    Thread thread;
    HashMap<String,String> sent= new HashMap<String,String>();
    trying tg;
    private Product currentProduct;
    private String id;
    private LinkedList<Queue> queuesBefore;
    private Queue queueAfter;
    private int time;
    private boolean isEmpty = true;
    private int min = 1000;
    private int max = 5000;
    Random random = new Random();
    public Machine(String id, Queue queueAfter, LinkedList<Queue> queueBefore)
    {
        System.out.println("id");
        System.out.println(id);
        System.out.println("after");
        System.out.println(queueAfter.id);
        for (int i = 0; i < queueBefore.size(); i++) {
            System.out.println("qbefor");
            System.out.print(queueBefore.get(i).id + " ");
        }
       // System.out.println(queueBefore);
        this.id = id;
        this.queueAfter = queueAfter;
        this.queuesBefore = queueBefore;
        this.addToQueues(this);
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
//        System.out.println(Thread.getAllStackTraces().keySet().size());
        this.thread = new Thread(this, "Thread " + this.id);
        this.thread.start();
//        System.out.println(Thread.getAllStackTraces().keySet().size());
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
        System.out.println("machine  " +this.id +"   " +this.currentProduct.getId());
        this.queueAfter.addProduct(this.currentProduct);
//        String in ;
//        String out;
//        String product;
//        out=getId();
//        product= this.currentProduct.getId();
//        in=this.currentProduct.getLastQueueIn();
       // System.out.println(out);
       // System.out.println(product);
        //System.out.println(in);
//       sent.put("product",product);
//        sent.put("in",in);
//        sent.put("out",out);
//        System.out.println("machine" +this.id +this.currentProduct.getId());
       // for (Map.Entry<String,String> set : sent.entrySet()) {
           //   System.out.println(set.getValue());
              //System.out.println("warena");
        //}
      // this.tg.send2(sent);
        this.thread.join();
//        System.out.println(this.thread.isAlive());
        this.notifyAllObservers();
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
