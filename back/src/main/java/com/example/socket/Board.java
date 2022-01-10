package com.example.socket;

import java.util.*;

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
      void makequeue(HashMap<String,HashMap<String,String[]>>queuefront )
      {
          for (Map.Entry<String, HashMap<String, String[]>> set : queuefront.entrySet()) {

              Queue q = new Queue(set.getKey());
               if(set.getKey()=="q0")
               {
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
          LinkedList<Queue> w =new LinkedList<Queue>();
          for (Map.Entry<String, HashMap<String, String[]>> set : machinefront.entrySet()) {
             store =set.getValue();
             VAL1=store.get("out");
             VAL2=store.get("in");
             for(int i=0;i< VAL2.length;i++)
             {
               w.push(Queues.get(VAL2[i]));
             }
             Machine m = new Machine(set.getKey(), Queues.get(VAL1[0]),w);
          }
      }
    void simulate()
    {
        Queue first = this.first;
        thread = new Thread(() -> {
            originator = new Originator();
            careTaker = new CareTaker();
            for (int i = 0; i < n; i++)
            {
                Product tempProduct = originator.makeProduct("p" + Integer.toString(i));
                first.addProduct(tempProduct);
                careTaker.addProduct(tempProduct);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        first.print();
                        try
                        {
                            first.sendProduct();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, tempProduct.getTimeRate());
            }
        });
        thread.start();
    }

}
