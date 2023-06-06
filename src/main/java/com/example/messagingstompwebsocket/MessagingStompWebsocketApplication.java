package com.example.messagingstompwebsocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Scanner;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class MessagingStompWebsocketApplication implements CommandLineRunner {

	private final MyController myController;

	public static void main(String[] args) {
		SpringApplication.run(MessagingStompWebsocketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String message = sc.nextLine();
			myController.sendMessage(message);
			log.error("SEND MESSAGE FROM SERVER: {}", message);
		}
	}
}
