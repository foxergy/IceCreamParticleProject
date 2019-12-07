package de.thb.saqs.model.service.websocket;


import de.thb.saqs.model.domain.Station;
import de.thb.saqs.model.domain.WebSocketMessage;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;
import java.util.*;

public class MyStompSessionHandler extends Observable implements StompSessionHandler {

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        stompSession.subscribe("/topic/observable", this);
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        System.out.println("exceptionhandler");

    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        System.out.println("Transporterror");
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return WebSocketMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
        System.out.println("got Notification");
        setChanged();
        notifyObservers(o);
        clearChanged();
    }
}

