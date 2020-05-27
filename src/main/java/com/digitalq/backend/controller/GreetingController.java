package com.digitalq.backend.controller;

import com.digitalq.backend.model.Greeting;
import com.digitalq.backend.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    public GreetingController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(1000);

        for(int i = 0 ; i < 10 ; i++) {
            message("foobar");
        }
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName())+ "!");
    }

    private static SimpMessageSendingOperations messagingTemplate;

    public static void message(String message){
        GreetingController.messagingTemplate.convertAndSend("/topic/greetings", new Greeting(message));
    }
}
