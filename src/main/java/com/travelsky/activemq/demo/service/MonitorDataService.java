package com.travelsky.activemq.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Service
public class MonitorDataService {

    @Autowired
    private RestTemplate template;

    private HttpEntity getHeader() {
        String postBody = "data";

        HttpHeaders headers = new HttpHeaders();
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String( encodedAuth );
        headers.set("Authorization", authHeader );
        HttpEntity<String> entity = new HttpEntity<String>(postBody, headers);
        return entity;
    }

    public String getCurrentStatus(String brokerName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache" +
                ".activemq:type=Broker,brokerName=" + brokerName + ",service=Health/CurrentStatus";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }

    public String getMemoryPercentUsage(String brokerName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache.activemq:type=Broker,brokerName=" + brokerName + "/MemoryPercentUsage";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }

    public String getStorePercentUsage(String brokerName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache.activemq:type=Broker,brokerName=" + brokerName + "/StorePercentUsage";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }

    public String getTempPercentUsage(String brokerName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache.activemq:type=Broker,brokerName=" + brokerName + "/TempPercentUsage";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }

    public String getQueueSize(String brokerName, String destinationName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache.activemq:type=Broker,brokerName=" + brokerName +
                ",destinationType=Queue,destinationName=" + destinationName + "/QueueSize";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }

    public String getConsumerCount(String brokerName, String destinationName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache.activemq:type=Broker,brokerName=" + brokerName +
                ",destinationType=Queue,destinationName=" + destinationName + "/ConsumerCount";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }

    public String getEnqueueCount(String brokerName, String destinationName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache.activemq:type=Broker,brokerName=" + brokerName +
                ",destinationType=Queue,destinationName=" + destinationName + "/EnqueueCount";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }

    public String getDequeueCount(String brokerName, String destinationName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache.activemq:type=Broker,brokerName=" + brokerName +
                ",destinationType=Queue,destinationName=" + destinationName + "/DequeueCount";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }

    public String getProducerCount(String brokerName, String destinationName) {
        String url="http://localhost:8161/api/jolokia/read/org.apache.activemq:type=Broker,brokerName=" + brokerName +
                ",destinationType=Queue,destinationName=" + destinationName + "/ProducerCount";
        ResponseEntity<String> results = template.exchange(url, HttpMethod.GET, getHeader(), String.class);
        JSONObject object = JSONObject.parseObject(results.getBody());
        return object.getString("value");
    }
}
