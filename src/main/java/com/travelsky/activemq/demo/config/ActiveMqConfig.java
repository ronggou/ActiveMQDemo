package com.travelsky.activemq.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

public class ActiveMqConfig {

/*    public static final String PRODUCT_BROKER_URL = "failover:(tcp://127.0.0.1:61617," +
            "tcp://127.0.0.1:61618)";

    public static final String CONSUME_BROKER_URL = "failover:(tcp://127.0.0.1:61616," +
            "tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)?randomize=true";

    public static final Integer SESSION_CACHE_SIZE = 100;

    public static final Integer RECEIVE_TIMEOUT = 1000;

    public static final Boolean PUB_SUB_DOMAIN = false;

    public static final String ACTIVEMQ_QUEUE_NAME = "myspringqueue";*/

    /*
    创建连接 activeMQ 连接工厂
     */
/*    @Bean(name = "productActiveMQConnectionFactory")
    public ActiveMQConnectionFactory productActiveMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(PRODUCT_BROKER_URL);
        return connectionFactory;
    }

    @Bean(name = "consumeActiveMQConnectionFactory")
    public ActiveMQConnectionFactory consumeActiveMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(CONSUME_BROKER_URL);
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory productCachingConnectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(productActiveMQConnectionFactory());
        connectionFactory.setSessionCacheSize(SESSION_CACHE_SIZE);
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory consumeCachingConnectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(consumeActiveMQConnectionFactory());
        connectionFactory.setSessionCacheSize(SESSION_CACHE_SIZE);
        return connectionFactory;
    }*/

/*    @Bean(name = "demoQueueDestination")
    public ActiveMQQueue demoQueueDestination() {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(ACTIVEMQ_QUEUE_NAME);
        return activeMQQueue;
    }*/

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

/*    @Bean(name = "productJmsTemplate")
    public JmsTemplate productJMSTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(productCachingConnectionFactory());
        jmsTemplate.setDefaultDestination(demoQueueDestination());
        jmsTemplate.setReceiveTimeout(RECEIVE_TIMEOUT);
        jmsTemplate.setPubSubDomain(PUB_SUB_DOMAIN);
        return jmsTemplate;
    }

    @Bean(name = "consumeJmsTemplate")
    public JmsTemplate consumeJMSTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(consumeCachingConnectionFactory());
        jmsTemplate.setDefaultDestination(demoQueueDestination());
        jmsTemplate.setReceiveTimeout(RECEIVE_TIMEOUT);
        jmsTemplate.setPubSubDomain(PUB_SUB_DOMAIN);
        return jmsTemplate;
    }


    *//**
     * 自定义监听 Container 配置在 MessageListener.onMessage 方法上
     * @return
     *//*
    @Bean(name = "jmsListenerContainerFactory")
    public JmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory =
                new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(consumeCachingConnectionFactory());
        jmsListenerContainerFactory.setReceiveTimeout(Long.valueOf(RECEIVE_TIMEOUT));
        jmsListenerContainerFactory.setPubSubDomain(PUB_SUB_DOMAIN);
        return jmsListenerContainerFactory;
    }*/
}
