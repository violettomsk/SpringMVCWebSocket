package com.lynas.service;

import com.lynas.model.LocationData;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserLocationService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserLocationService.class);
    private ArrayList<LocationData> locations;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/loc")
    private void sendLocation() {
        simpMessagingTemplate.convertAndSend("/topic/location", this.getLatestLocation());
    }


    UserLocationService() {
        this.locations = new ArrayList<LocationData>();
    }

    public void insert(Float lat, Float lon) {
        logger.info("insert - lat - lon:[ " + lat.toString() + "][" + lon.toString() + "]");
        this.locations.add(new LocationData(lat, lon));
    }

    public void insert(LocationData d) {
        logger.info("insert LocationData:[" + d.getLat() + "][" + d.getLon() + "]");
        this.locations.add(d);
        sendLocation();
    }

    public LocationData getLatestLocation() {
        this.logger.info("latestLocation: " + locations.size());
        return this.locations.get(this.locations.size()-1);
    }



}
