package com.example.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class trying {
    wsservice not;
    @Autowired
    public trying (SimpMessagingTemplate messagingTemplate)
    {
        this.not= new wsservice(messagingTemplate);

    }
    public void send ( int i ) throws InterruptedException {
        System.out.println("yes");
        while (i<5)
        {
            i++;
            Thread.sleep(6000);

            not.notifymess(new HelloMessage("ahmed",i));
        }
    }
}
