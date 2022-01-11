package com.example.socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class Board
{
    LinkedList<Machine> Machines = new LinkedList<Machine>();
    CareTaker careTaker;
    Originator originator;
    HashMap<String, Queue> Queues = new HashMap<String,Queue>();
    LinkedList<Product> Products = new LinkedList<Product>();
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
          HashMap<String,String[]> store;
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
             //System.out.println(store.get("in"));
             //System.out.println(Queues.get(VAL2));
              //System.out.println("YES");
              m = new Machine(set.getKey(), Queues.get(VAL1[0]),ty);
             for(int h=0;h< VAL2.length;h++)
             {
                // System.out.println(VAL2[h]);
                 System.out.println("lol");
                 System.out.println(Queues.get(VAL2[h]).id);
                 m.addQueueBefore(Queues.get(VAL2[h]));
             }
              m.addToQueues(m);
          }
          simulate();
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
//                System.out.println("Hiiiiiiiiiiii!!!!");
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
//                        first.print();
                        first.addProduct(tempProduct);
                        careTaker.addProduct(tempProduct);
                        try
                        {
                            first.addProduct(tempProduct);
                            careTaker.addProduct(tempProduct);
//                            if(tempProduct.getId().equals("p2"))
//                            {
                                first.sendProduct();
//                            }
//                            first.sendProduct();
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
