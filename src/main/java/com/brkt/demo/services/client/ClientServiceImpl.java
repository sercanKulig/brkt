package com.brkt.demo.services.client;

import com.brkt.demo.helper.ResponseHelper;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.mitre.taxii.client.HttpClient;
import org.mitre.taxii.messages.xml11.DiscoveryRequest;
import org.mitre.taxii.messages.xml11.MessageHelper;
import org.mitre.taxii.messages.xml11.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.net.URI;

@Service
public class ClientServiceImpl implements ClientService {

    private ObjectFactory factory = new ObjectFactory();

    private ResponseHelper responseHelper;

    @Autowired
    public ClientServiceImpl(ResponseHelper responseHelper) {
        this.responseHelper = responseHelper;
    }

    public ClientServiceImpl() {

    }

    @Override
    public Response initializePoolRequest() {
        try {
            HttpClientBuilder cb = HttpClientBuilder.create();
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    AuthScope.ANY,
                    new UsernamePasswordCredentials("taxii", "taxii"));
            cb.setDefaultCredentialsProvider(credsProvider);
            CloseableHttpClient httpClient = cb.build();

            HttpClient taxiiClient = new HttpClient(httpClient);

            DiscoveryRequest dr = factory.createDiscoveryRequest()
                    .withMessageId(MessageHelper.generateMessageId());

            return responseHelper.getResponse(taxiiClient.callTaxiiService(new URI(System.getProperty("serverUrl")), dr));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
