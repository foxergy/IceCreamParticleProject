package de.thb.saqs.model.service.websocket;

import lombok.Data;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Data
public class WebSocketService {

    private MyStompSessionHandler stompSessionHandler;

    public WebSocketService(){
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        stompSessionHandler = new MyStompSessionHandler();
        stompClient.connect("ws://localhost:8080/stationSocket", stompSessionHandler);
    }
}
