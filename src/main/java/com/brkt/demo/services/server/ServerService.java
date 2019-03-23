package com.brkt.demo.services.server;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

public interface ServerService {
    Response initializePoolRequest(HttpServletRequest request);
    Response initializeDiscoveryRequest(HttpServletRequest request);
}
