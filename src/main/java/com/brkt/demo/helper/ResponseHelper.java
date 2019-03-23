package com.brkt.demo.helper;

import javax.ws.rs.core.Response;

public interface ResponseHelper {
    Response getResponse(Object discoveryResponse) throws Exception;
}
