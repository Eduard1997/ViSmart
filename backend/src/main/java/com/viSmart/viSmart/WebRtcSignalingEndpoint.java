package com.viSmart.viSmart;


import com.viSmart.viSmart.Repository.UserInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

public class WebRtcSignalingEndpoint {

    private Map<String,VideoRest> videoConferences;

    private UserInventory userInventory;

    private UserAuthServiceFilter userAuthServiceFilter;

    @Autowired
    public void setUserInventory(UserInventory userInventory) {
        this.userInventory = userInventory;
    }

    @Autowired
    public void setUserAuthServiceFilter(UserAuthServiceFilter userAuthServiceFilter) {
        this.userAuthServiceFilter = userAuthServiceFilter;
    }

    @Autowired
    public void setVideoConference(Map<String,VideoRest> videoConferences){
        this.videoConferences = videoConferences;
    }

    public WebRtcSignalingEndpoint() {
    }

    @OnOpen
    public void whenOpening(Session session,@PathParam("classname") String classname,@PathParam("token") String token) throws IOException, EncodeException {
        // Add websocket session to a global set to use in OnMessage.
        Authentication authentication = userAuthServiceFilter.getAuthentication(token);
        String username = (String)  authentication.getPrincipal();
        User user = userInventory.findByEmail(username);
        if (!videoConferences.containsKey(classname)) {
            VideoRest videoRest = new VideoRest(user);
            //get the info about the Vi Coordinator user?!
            videoConferences.put(classname,videoRest);
        }
        session.getUserProperties().put("classname", classname);
        videoConferences.get(classname).attach(user,session);
    }

    @OnMessage
    public void process(String data,@PathParam("classname") String classname, Session session,@PathParam("token") String token) throws IOException {
        /*
         * When signal is received, send it to other participants other than self. In
         * real world, signal should be sent to only participant's who belong to
         * particular video conference.
         */
        Authentication authentication = userAuthServiceFilter.getAuthentication(token);
        String username = (String)  authentication.getPrincipal();
        User user = userInventory.findByEmail(username);
        if (videoConferences.containsKey(classname)) {
            VideoRest videoRest = videoConferences.get(classname);
            videoRest.setSettings(user,new Settings(),data);
        }
    }

    @OnClose
    public void whenClosing(Session session,@PathParam("classname") String classname,@PathParam("token") String token) {
        Authentication authentication = userAuthServiceFilter.getAuthentication(token);
        String username = (String)  authentication.getPrincipal();
        User user = userInventory.findByEmail(username);
        if (videoConferences.containsKey(classname)) {
            VideoRest videoRest = videoConferences.get(classname);
            if (videoRest.isViCoordinator(user))
                videoConferences.remove(videoRest);
            else
                videoRest.removeVideo(user);
        }
    }
}
