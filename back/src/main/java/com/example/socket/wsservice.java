package com.example.socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.GsonMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
@Service
public class wsservice {
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    public wsservice (SimpMessagingTemplate messagingTemplate)
    {
        this.messagingTemplate=messagingTemplate;
        this.g= new GsonBuilder().create();
    }
    public void notifymess(final HelloMessage message )
    {
        GsonMessageConverter M  = new GsonMessageConverter(g);
        messagingTemplate.setMessageConverter(M);
        messagingTemplate.convertAndSend("/topic/greetings",message);
    }
    private Gson g;
}
