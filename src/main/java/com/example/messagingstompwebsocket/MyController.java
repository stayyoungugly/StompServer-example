package com.example.messagingstompwebsocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ObjectMapper objectMapper;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MessageResponse greeting(MessageRequest message) {
        log.error("GOT MESSAGE FROM CLIENT: {}", message.getName());
        return MessageResponse.builder().content(message.getName().toUpperCase()).build();
    }

    @SneakyThrows
    public void sendMessage(String message) {
        simpMessagingTemplate.send("/topic/greetings", new GenericMessage<>(
                objectMapper.writeValueAsString(new MessageResponse(message)).getBytes()));
    }

}
