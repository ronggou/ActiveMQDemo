package com.travelsky.activemq.demo.controller;

import com.travelsky.activemq.demo.service.ConsumerService ;
import com.travelsky.activemq.demo.service.ProducerService ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/1/5.
 */
@Controller
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

/*    @Resource(name = "demoQueueDestination")
    private Destination destination;*/

    //队列消息生产者
    @Autowired
    private ProducerService producer;

    //队列消息消费者
    @Autowired
    private ConsumerService consumer;

    @RequestMapping(value = "/SendMessage", method = RequestMethod.GET)
    @ResponseBody
    public void send(String msg) {
        logger.info(Thread.currentThread().getName()+"------------send to jms Start");
        producer.sendMessage(msg);
        logger.info(Thread.currentThread().getName()+"------------send to jms End");
    }

    @RequestMapping(value= "/ReceiveMessage",method = RequestMethod.GET)
    @ResponseBody
    public void receive(){
        logger.info(Thread.currentThread().getName()+"------------receive from jms Start");
        TextMessage tm = consumer.receive();

        logger.info(Thread.currentThread().getName()+"------------receive from jms End");
    }

}
