package com.travelsky.activemq.demo.service;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 监听指定队列
 */

/*@Component
@EnableJms*/
public class ConsumerListener implements MessageListener {

    @Override
    @JmsListener(destination = "myspringqueue")
    public void onMessage(Message message) {
        try {
            String msg = ((TextMessage)message).getText();
            System.out.println("listen:"+msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
