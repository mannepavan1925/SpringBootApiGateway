package com.pavan.ApiGateWay.util;

import com.pavan.ApiGateWay.beans.ApiGatewayProperties;
import com.pavan.ApiGateWay.exception.NoSuchRequestHandlingMethodException;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

public class URLRequestTransformer extends ProxyRequestTransformer {
    private ApiGatewayProperties apiGatewayProperties;

    public URLRequestTransformer(ApiGatewayProperties apiGatewayProperties) {
        this.apiGatewayProperties = apiGatewayProperties;
    }

    @Override
    public RequestBuilder transform(HttpServletRequest request) throws NoSuchRequestHandlingMethodException, URISyntaxException {
        String requestURI = request.getRequestURI();
        URI uri;
        if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
            uri = new URI(getServiceUrl(requestURI, request) + "?" + request.getQueryString());
        } else {
            uri = new URI(getServiceUrl(requestURI, request));
        }

        RequestBuilder rb = RequestBuilder.create(request.getMethod());
        rb.setUri(uri);
        return rb;
    }

    private String getServiceUrl(String requestURI, HttpServletRequest httpServletRequest) throws NoSuchRequestHandlingMethodException {

        ApiGatewayProperties.Endpoint endpoint =
                apiGatewayProperties.getEndpoints().stream()
                        .filter(e ->
                                requestURI.matches(e.getPath()) && e.getMethod() == RequestMethod.valueOf(httpServletRequest.getMethod())
                        )
                        .findFirst().orElseThrow(() -> new NoSuchRequestHandlingMethodException(httpServletRequest));
        return endpoint.getLocation() + requestURI;
    }
}
