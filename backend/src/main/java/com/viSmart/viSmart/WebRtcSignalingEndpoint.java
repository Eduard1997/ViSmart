package com.viSmart.viSmart;


import com.viSmart.viSmart.Repository.UserInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/signal/{classname}")
@Component
public class WebRtcSignalingEndpoint {

    private static final Map<String,VideoRest> videoConferences = Collections.synchronizedMap(new HashMap<String,VideoRest>());
    @Autowired
    UserInventory userInventory;

    @OnOpen
    public void whenOpening(Session session,@PathParam("classname") String classname) throws IOException, EncodeException {
        System.out.println("Open!");
        // Add websocket session to a global set to use in OnMessage.
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userInventory.findByUsername(principal.getName());
        if (!videoConferences.containsKey(classname)) {
            VideoRest videoRest = new VideoRest(user);
            //get the info about the Vi Coordinator user?!
            videoConferences.put(classname,videoRest);
        }
        session.getUserProperties().put("classname", classname);
        videoConferences.get(classname).attach(user,session);
    }

    @OnMessage
    public void process(String data,@PathParam("classname") String classname, Session session) throws IOException {
        System.out.println("Got signal - " + data);
        /*
         * When signal is received, send it to other participants other than self. In
         * real world, signal should be sent to only participant's who belong to
         * particular video conference.
         */
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userInventory.findByUsername(principal.getName());
        if (videoConferences.containsKey(classname)) {
            VideoRest videoRest = videoConferences.get(classname);
            videoRest.setSettings(user,new Settings(),data);
        }
    }

    @OnClose
    public void whenClosing(Session session,@PathParam("classname") String classname) {
        System.out.println("Close!");
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userInventory.findByUsername(principal.getName());
        if (videoConferences.containsKey(classname)) {
            VideoRest videoRest = videoConferences.get(classname);
            if (videoRest.isViCoordinator(user))
                videoConferences.remove(videoRest);
            else
                videoRest.removeVideo(user);
        }
    }
}
