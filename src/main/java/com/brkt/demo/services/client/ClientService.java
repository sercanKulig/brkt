package com.brkt.demo.services.client;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ClientService {
    Response initializePoolRequest() throws URISyntaxException, JAXBException, IOException;
}
