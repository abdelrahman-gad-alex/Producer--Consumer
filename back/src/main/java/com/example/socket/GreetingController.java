package com.example.socket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.HashMap;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class GreetingController {
    Gson g = new Gson();
    HashMap<String,HashMap<String,String[]>>Frontq;
    HashMap<String,HashMap<String,String[]>>Frontm;

    //OjectMapper mapper = new ObjectMapper();
    Board B =new Board();
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(9000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    @GetMapping("/input")
     public void getmessag(@RequestParam String frontq,@RequestParam String frontm ,@RequestParam String products) throws InterruptedException, IOException, JSONException {

       Frontq = new Gson().fromJson(
                String.valueOf(frontq), new TypeToken<HashMap<String, String>>() {}.getType()
        );
        Frontm = new Gson().fromJson(
                String.valueOf(frontm), new TypeToken<HashMap<String, String>>() {}.getType()
        );
        System.out.println(Frontq);
        System.out.println(Frontm);
        for(String i:Frontq.keySet())
        {
            Frontq.put(i, new Gson().fromJson(
                    String.valueOf(Frontq.get(i)), new TypeToken<HashMap<String, String[]>>() {}.getType()
            ));
        }
        for(String i:Frontm.keySet())
        {
            Frontq.put(i, new Gson().fromJson(
                    String.valueOf(Frontm.get(i)), new TypeToken<HashMap<String,String[]>>() {}.getType()
            ));
        }
        B.makequeue(Frontq);
        B.makemachine(Frontm);
        B.n=Integer.parseInt(products);
    }
    trying t;
    public GreetingController (SimpMessagingTemplate messagingTemplate)
    {
        this.t= new trying(messagingTemplate);
    }
}
