package com.lynas.controller;

import com.lynas.model.LocationData;
import com.lynas.model.User;
import com.lynas.service.GreeterService;
import com.lynas.service.UserLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by sazzad on 2/11/16
 */
@Controller()
@ResponseStatus(value = HttpStatus.ACCEPTED)
public class HomeController {

    private static final Logger logger	= LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private UserLocationService userLocationService;

    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreeterService greeting(User message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new GreeterService("Hello, " + message.getName() + "!");
    }

    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public void updateData(@RequestBody Map<String, String> params, HttpServletRequest req) throws Exception {
        float lat =  Float.parseFloat(params.get("lat"));
        float lon = Float.parseFloat(params.get("lon"));
        logger.info(String.valueOf(params.get("lat")));
        logger.info(String.valueOf(params.get("lon")));
//        logger.debug(String.valueOf(loc.getLat()));
//        logger.debug(String.valueOf(loc.getLon()));
        userLocationService.insert(new LocationData(lat, lon));
    }

    @RequestMapping(value = "/simMessage")
    public String greeting() throws Exception {
        template.convertAndSend("/topic/greetings",
                new GreeterService("Hello, Other!"));
        return "sample";
    }
}
