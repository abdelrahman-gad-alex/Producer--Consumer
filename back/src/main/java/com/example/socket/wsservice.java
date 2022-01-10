package com.example.socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.GsonMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class wsservice {
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    public wsservice (SimpMessagingTemplate messagingTemplate)
    {
        this.messagingTemplate=messagingTemplate;

    }
    public void notifymess(final HashMap<String,String> send)
    {
        messagingTemplate.convertAndSend("/topic/greetings",send);
    }
}
