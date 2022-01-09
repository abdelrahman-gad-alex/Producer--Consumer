package com.example.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class trying {
    wsservice not;
    @Autowired
    public trying (SimpMessagingTemplate messagingTemplate)
    {
        this.not= new wsservice(messagingTemplate);

    }
    public void send2 (HashMap<String,String>send ) throws InterruptedException {
        System.out.println("yes");
            not.notifymess(send);
    }
}
