package com.brkt.demo.component;

import com.brkt.demo.enumaretion.EnumClient;
import com.brkt.demo.enumaretion.EnumServer;
import com.brkt.demo.services.client.ClientService;
import com.brkt.demo.services.server.ServerService;
import org.springframework.stereotype.Component;

@Component
public abstract class ServiceFactory {
    public abstract ServerService getServerService(EnumServer serviceType);
    public abstract ClientService getClientService(EnumClient serviceType);
}
