package com.digitalq.backend.controller;

import com.digitalq.backend.model.Greeting;
import com.digitalq.backend.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(1000);
        send();
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName())+ "!");
    }

    private void send(){
        try {
            int times = 0;
            while (times<10){

                send2(times +" ");
                times ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SendTo("/topic/greetings")
    public Greeting send2(String msg)throws Exception{
        return new Greeting(msg + " hej");
    }

}
