package com.brkt.demo.component;

import com.brkt.demo.enumaretion.EnumClient;
import com.brkt.demo.enumaretion.EnumServer;
import com.brkt.demo.services.client.ClientService;
import com.brkt.demo.services.client.ClientServiceImpl;
import com.brkt.demo.services.server.ServerService;

public class ClientFactory extends ServiceFactory {
    @Override
    public ServerService getServerService(EnumServer serviceType) {
        return null;
    }

    @Override
    public ClientService getClientService(EnumClient serviceType) {
        if(serviceType == null)
            return null;

        if(serviceType.equals(EnumClient.POOL)) {
            return new ClientServiceImpl();
        }
        return null;
    }
}
