package com.brkt.demo.controller;

import com.brkt.demo.component.FactoryProducer;
import com.brkt.demo.component.ServiceFactory;
import com.brkt.demo.enumaretion.EnumFactory;
import com.brkt.demo.enumaretion.EnumServer;
import com.brkt.demo.services.server.ServerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/serverApi")
public class ServerController {

    @RequestMapping(method = RequestMethod.POST, value = "/doPoolClientRequest", produces = MediaType.APPLICATION_XML, consumes = MediaType.APPLICATION_XML)
    public Response doCallTaxiiService(@Context HttpServletRequest request) {
        ServiceFactory serviceFactory = FactoryProducer.getFactory(EnumFactory.SERVER);
        ServerService serverService = serviceFactory.getServerService(EnumServer.POOL);
        return serverService.initializePoolRequest(request);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doDiscoveryClientRequest", produces = MediaType.APPLICATION_XML, consumes = MediaType.APPLICATION_XML)
    public Response doDiscoveryServerRequest(@Context HttpServletRequest request) {
        ServiceFactory serviceFactory = FactoryProducer.getFactory(EnumFactory.SERVER);
        ServerService serverService = serviceFactory.getServerService(EnumServer.POOL);
        return serverService.initializeDiscoveryRequest(request);
    }

//        //Clientın gerekli hazırlık kısımları
//        HttpClientBuilder cb = HttpClientBuilder.create();
//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials(
//                AuthScope.ANY,
//                new UsernamePasswordCredentials("taxii", "taxii"));
//        cb.setDefaultCredentialsProvider(credsProvider);
//        CloseableHttpClient httpClient = cb.build();
//
//        //Clienti yaratma
//        HttpClient taxiiClient = new HttpClient(httpClient);
//
//        ObjectFactory factory = new ObjectFactory();
//        //Gönderilecek mesajı ekleme
//        DiscoveryRequest dr = factory.createDiscoveryRequest()
//                .withMessageId(MessageHelper.generateMessageId());
//
//        //Servisi çağırıyoruz
//        final String serverUrl = "http://127.0.0.1:8100/services/discovery/";
//        try {
//            Object responseObj = taxiiClient.callTaxiiService(new URI(serverUrl), dr);
//        } catch (JAXBException | IOException | URISyntaxException e) {
//            e.printStackTrace();
//        } finally {
//            taxiiClient.getHttpclient().close();
//        }

}
