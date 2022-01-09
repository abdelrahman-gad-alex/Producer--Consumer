package com.example.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class GreetingController {
    Board B =new Board();
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(9000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @GetMapping("/input")
     public void getmessag(@RequestParam HashMap<String,HashMap<String,String[]>> frontq,@RequestParam HashMap<String,HashMap<String,String[]>> frontm ,@RequestParam int products) throws InterruptedException {
        B.makequeue(frontq);
        B.makemachine(frontm);
        B.n=products;
    }
    trying t;
    public GreetingController (SimpMessagingTemplate messagingTemplate)
    {
        this.t= new trying(messagingTemplate);
    }

}
