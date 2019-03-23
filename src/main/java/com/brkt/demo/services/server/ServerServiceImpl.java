package com.brkt.demo.services.server;

import com.brkt.demo.helper.ResponseHelper;
import org.mitre.taxii.ContentBindings;
import org.mitre.taxii.Versions;
import org.mitre.taxii.messages.xml11.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {

    private ObjectFactory factory = new ObjectFactory();

    private ResponseHelper responseHelper;

    @Autowired
    public ServerServiceImpl(ResponseHelper responseHelper) {
        this.responseHelper = responseHelper;
    }

    public ServerServiceImpl() {

    }

    @Override
    public Response initializePoolRequest(HttpServletRequest request) {
        try {
            //pool request oluşturuyoruz
            PollRequest pollRequest = new PollRequest();

            /*
                response oluşturup içerisine
                gerekli parametreleri ekliyoruz
                contentBlock içerisine STRIX verisi ve diğer gerekli parametreleri ekliyoruz

             */
            PollResponse pollResponse = factory.createPollResponse()
                    .withInResponseTo(pollRequest.getMessageId())
                    .withMessageId(MessageHelper.generateMessageId())
                    .withCollectionName(pollRequest.getCollectionName())
                    .withRecordCount(factory.createRecordCountType().withValue(BigInteger.valueOf(9999)).withPartialCount(false))
                    .withExclusiveBeginTimestamp(pollRequest.getExclusiveBeginTimestamp())
                    .withInclusiveEndTimestamp(pollRequest.getInclusiveEndTimestamp())
                    .withContentBlocks(
                            factory.createContentBlock()
                                    .withContentBinding(factory.createContentInstanceType().withBindingId(ContentBindings.CB_STIX_XML_111))
                                    .withContent(factory.createAnyMixedContentType().withContent("STIX"))
                                    .withMessage("Data")
                    );

            //response dönülüyor
            return responseHelper.getResponse(pollResponse);
        } catch (Exception ex) {
            //hata durumunda hata atıyoruz
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public Response initializeDiscoveryRequest(HttpServletRequest request) {
        try {
            // request oluşturuyoruz
            DiscoveryRequest discoveryRequest = new DiscoveryRequest();

            //servis paramatreleri ekleniyor
            List services = new ArrayList<>();
            services.add(factory.createServiceInstanceType()
                    .withServiceType(ServiceTypeEnum.DISCOVERY)
                    .withAddress("/discovery")
                    .withAvailable(true)
                    .withProtocolBinding(Versions.VID_TAXII_HTTP_10)
                    .withServiceVersion(Versions.VID_TAXII_SERVICES_11)
                    .withMessageBindings(Versions.VID_TAXII_XML_11)
                    .withMessage("Data")
                    .withContentBindings(factory.createContentBindingIDType().withBindingId(ContentBindings.CB_STIX_XML_111))
            );

            //response ekleniyor yukarıdaki veya gerekli parametreler eklenebilir
            DiscoveryResponse discoveryResponse = factory.createDiscoveryResponse()
                    .withInResponseTo(discoveryRequest.getMessageId())
                    .withMessageId(MessageHelper.generateMessageId())
                    .withServiceInstances(services);

            //response dönülüyor
            return responseHelper.getResponse(discoveryResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
