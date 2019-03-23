package com.brkt.demo.helper;

import org.mitre.taxii.Versions;
import org.mitre.taxii.client.HttpClient;
import org.mitre.taxii.messages.xml11.TaxiiXml;
import org.mitre.taxii.messages.xml11.TaxiiXmlFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Service
public class ResponseHelperImpl implements ResponseHelper {

    private TaxiiXmlFactory txf = new TaxiiXmlFactory();
    private TaxiiXml taxiiXml = txf.createTaxiiXml();

    public Response getResponse(Object discoveryResponse) throws Exception {
        String responseString = toXml(discoveryResponse);

        //response oluşturuluyor
        return Response.ok(responseString)
                .header(HttpClient.HEADER_X_TAXII_PROTOCOL, Versions.VID_TAXII_HTTP_10)
                .header(HttpClient.HEADER_X_TAXII_CONTENT_TYPE, Versions.VID_TAXII_XML_11)
                .header(HttpClient.HEADER_X_TAXII_SERVICES, Versions.VID_TAXII_SERVICES_11)
                .build();
    }

    private String toXml(Object discoveryResponse) throws Exception {
        //oluşturulan requesti xml'e çeviriyoruz
        final Marshaller m = taxiiXml.createMarshaller(false);
        m.setProperty(Marshaller.JAXB_FRAGMENT, true);
        final StringWriter sw = new StringWriter();
        m.marshal(discoveryResponse, sw);
        return sw.toString();
    }

}
