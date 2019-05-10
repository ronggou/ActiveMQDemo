package com.travelsky.activemq.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/1/5.
 */
@Service
public class ConsumerService {
//    @Resource(name="consumeJmsTemplate")
    @Autowired
    private JmsTemplate jmsTemplate;

    public TextMessage receive(){
        TextMessage textMessage = (TextMessage) jmsTemplate.receive();
        try{
            System.out.println("从队列" + jmsTemplate.getDefaultDestinationName() + "收到了消息：\t"
                    + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return textMessage;
    }
}
