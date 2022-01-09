package com.example.socket;

import java.util.*;

public class Board
{
    LinkedList<Machine> Machines = new LinkedList<Machine>();
//<<<<<<< Updated upstream
    HashMap<String, Queue> Queues = new HashMap<String,Queue>();
//    LinkedList<String> Products = new LinkedList<String>();
//=======
//    LinkedList<Queue> Queues = new LinkedList<Queue>();
//    LinkedList<String> Products = new LinkedList<String>();
//>>>>>>> Stashed changes
    Thread thread;
    int n;
    Queue first;
      void makequeue(HashMap<String,HashMap<String,String[]>>queuefront )
      {
          for (Map.Entry<String, HashMap<String, String[]>> set : queuefront.entrySet()) {

              Queue q = new Queue(set.getKey());
               if(set.getKey()=="Q0")
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
                for (int i = 0; i < n; i++)
                {
                    //snapshot should be implemented here
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
                    }, 10);
                }
            });
        thread.start();
    }

}
