package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.UserInventory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.websocket.server.ServerEndpointConfig;

@Component
public class CustomSpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {
    private static volatile BeanFactory context;

    @Autowired
    WebRtcSignalingEndpoint webRtcSignalingEndpoint;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        if (clazz.getTypeName().equals("com.viSmart.viSmart.WebRtcSignalingEndpoint"))
            super.getEndpointInstance(clazz);
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomSpringConfigurator.context = applicationContext;
    }
}
