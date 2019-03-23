package com.brkt.demo.controller;

import com.brkt.demo.component.FactoryProducer;
import com.brkt.demo.component.ServiceFactory;
import com.brkt.demo.enumaretion.EnumClient;
import com.brkt.demo.enumaretion.EnumFactory;
import com.brkt.demo.services.client.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/clientApi")
public class ClientController {

    @RequestMapping(method = RequestMethod.GET, value = "/doPoolClientRequest", produces = MediaType.APPLICATION_XML, consumes = MediaType.APPLICATION_XML)
    public Response doCallTaxiiPoolClientRequest() throws JAXBException, IOException, URISyntaxException {
        ServiceFactory serviceFactory = FactoryProducer.getFactory(EnumFactory.CLIENT);
        ClientService clientService = serviceFactory.getClientService(EnumClient.POOL);
        return clientService.initializePoolRequest();
    }
}
