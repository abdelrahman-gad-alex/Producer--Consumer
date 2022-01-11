package com.example.socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class Board
{
    CareTaker careTaker;
    Originator originator;
    HashMap<String, Queue> Queues = new HashMap<String,Queue>();
    HashMap<String,String[]> store;
    HashMap<String,String>replay=new HashMap<String,String>();
    HashMap<String,String> ff=new HashMap<String,String>();
    Thread thread;
    int n;
    Queue first;
    trying ty;
    Machine m ;
    @Autowired
    public Board(trying messagingTemplate)
    {
        this.ty=messagingTemplate;
    }
      void makequeue(HashMap<String,HashMap<String,String[]>>queuefront )
      {
          for (Map.Entry<String, HashMap<String, String[]>> set : queuefront.entrySet()) {

              Queue q = new Queue(set.getKey());
             // System.out.println(set.getKey());
               if(set.getKey().equals("q0"))
               {
                   System.out.println("a7a");
                   first=q;
               }
              Queues.put(set.getKey(),q);
          }
      }
      void makemachine(HashMap<String,HashMap<String,String[]>>machinefront)
      {

          String[] VAL1;
          String[] VAL2;
          String[][] val3 = new String[0][];
          LinkedList<Queue> w =new LinkedList<Queue>();
          String[][] val4 = new String[0][];
          int j=0;
          for (Map.Entry<String, HashMap<String, String[]>> set : machinefront.entrySet()) {
             store =set.getValue();
             VAL2=store.get("in");
             VAL1=store.get("out");
              m = new Machine(set.getKey(), Queues.get(VAL1[0]),ty);
             for(int h=0;h< VAL2.length;h++)
             {
                 System.out.println("lol");
                 System.out.println(Queues.get(VAL2[h]).id);
                 m.addQueueBefore(Queues.get(VAL2[h]));
             }
              m.addToQueues(m);
          }
          simulate();
      }
      void clear()
      {
          store.clear();
          Queues.clear();
          m.clear();
      }

    void simulate()
    {
        Queue first = this.first;
        thread = new Thread(() -> {
            originator = new Originator();
            careTaker = new CareTaker();
            for (int i = 0; i < n; i++)
            {
                Product tempProduct = originator.makeProduct("p" + Integer.toString(i ));
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        first.addProduct(tempProduct);
                        careTaker.addProduct(tempProduct);
                        try
                        {
                            ff.put("product",tempProduct.getId());
                            ff.put("in","q0");
                            ty.send2(ff);
                                first.sendProduct();

                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, tempProduct.getTimeRate());
            }
            System.out.println("END");
        });
        thread.start();
    }
    void replay()
    {
        thread = new Thread(() ->
        {
            first = this.first;
            first.removeProducts();
            for (int i = 0; i < n ; i++) {

                Product product = careTaker.getProduct();
                first.addProduct(product);

                Product tempProduct = careTaker.getProduct();
                System.out.println(tempProduct.getId() + "  here");

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        first.addProduct(tempProduct);
                        try
                        {
                            replay.put("product",tempProduct.getId());
                            replay.put("in","q0");
                            ty.send2(replay);
                            first.sendProduct();

                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, tempProduct.getTimeRate());
            }
            System.out.println("END");
        });
        thread.start();
    }
}
