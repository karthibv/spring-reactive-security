
package com.example.security.service;

import org.springframework.stereotype.Component;

import com.example.security.domain.FormattedMessage;

import reactor.core.publisher.Flux;

@Component
public class MessageService {

    public Flux<FormattedMessage> getDefaultMessage(){
    	System.out.println("MessageService >>>getDefaultMessage ");

        return Flux.just(new FormattedMessage());
    }

    public Flux<FormattedMessage> getCustomMessage(String name){
    	System.out.println("MessageService >>>getCustomMessage ");

        return Flux.just(new FormattedMessage(name));
    }
}
