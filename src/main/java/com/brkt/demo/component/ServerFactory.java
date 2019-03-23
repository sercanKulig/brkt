package com.brkt.demo.component;

import com.brkt.demo.enumaretion.EnumClient;
import com.brkt.demo.enumaretion.EnumServer;
import com.brkt.demo.services.client.ClientService;
import com.brkt.demo.services.server.ServerServiceImpl;
import com.brkt.demo.services.server.ServerService;
import org.springframework.stereotype.Component;

@Component
public class ServerFactory extends ServiceFactory {

    @Override
    public ServerService getServerService(EnumServer serviceType) {
        if(serviceType == null)
        return null;

        if(serviceType.equals(EnumServer.POOL)) {
            return new ServerServiceImpl();
        }
        return null;
    }

    @Override
    public ClientService getClientService(EnumClient serviceType) {
        return null;
    }
}
