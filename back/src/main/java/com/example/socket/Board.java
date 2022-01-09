package com.example.socket;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Board
{
    LinkedList<Machine> Machines = new LinkedList<Machine>();
    LinkedList<Queue> Queues = new LinkedList<Queue>();
    LinkedList<String> Products = new LinkedList<String>();
    Thread thread;
    int n;
    Queue first;

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
