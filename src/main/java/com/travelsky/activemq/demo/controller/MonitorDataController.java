package com.travelsky.activemq.demo.controller;

import com.travelsky.activemq.demo.service.MonitorDataService;
import org.apache.activemq.broker.jmx.HealthStatus;
import org.apache.activemq.broker.jmx.HealthViewMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.openmbean.TabularData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MonitorDataController {
    @Autowired
    private MonitorDataService monitorDataService;
    public static final String BROKER_NAME = "localhost";
    public static final String DESTINATION_NAME = "myspringqueue";

    /**
     * 获取 broker 的状态
     * @return
     */
    @GetMapping("getBrokerCurrentStatus")
    public Object getBrokerCurrentStatus() {
        
        String currentStatus = monitorDataService.getCurrentStatus(BROKER_NAME);
        return currentStatus;
    }

    /**
     * 获取内存使用量、硬盘空间使用量、临时文件硬盘空间使用量
     * @return
     */
    @GetMapping("getMonitorBrokerData")
    public Object getMonitorData() {
        
        String memoryPercentUsage = monitorDataService.getMemoryPercentUsage(BROKER_NAME);
        String storePercentUsage = monitorDataService.getStorePercentUsage(BROKER_NAME);
        String tempPercentUsage = monitorDataService.getTempPercentUsage(BROKER_NAME);
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("memoryPercentUsage",Integer.valueOf(memoryPercentUsage));
        map.put("storePercentUsage",Integer.valueOf(storePercentUsage));
        map.put("tempPercentUsage",Integer.valueOf(tempPercentUsage));
        return map;
    }

    /**
     * 获取生产者数量、消费者数量、已消费消息数量、已接收消息数量
     * @return
     */
    @GetMapping("getMonitorDestinationData")
    public Object getMonitorDestinationData() {
        String producerCount = monitorDataService.getProducerCount(BROKER_NAME, DESTINATION_NAME);
        String consumerCount = monitorDataService.getConsumerCount(BROKER_NAME, DESTINATION_NAME);
        // 已消费消息数量
        String dequeueCount = monitorDataService.getDequeueCount(BROKER_NAME, DESTINATION_NAME);
        // 已接收消息数量
        String enqueueCount = monitorDataService.getEnqueueCount(BROKER_NAME, DESTINATION_NAME);
        String queueSize = monitorDataService.getQueueSize(BROKER_NAME, DESTINATION_NAME);
        Map<String,String> map = new HashMap<String, String>();
        map.put("producerCount",producerCount);
        map.put("consumerCount",consumerCount);
        map.put("dequeueCount",dequeueCount);
        map.put("enqueueCount",enqueueCount);
        map.put("queueSize",queueSize);
        return map;
    }
}
