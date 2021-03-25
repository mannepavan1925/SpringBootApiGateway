package com.pavan.ApiGateWay.exception;

import com.pavan.ApiGateWay.util.RestUtil;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.CoderResult;

public class RestTemplateErrorHandler implements ResponseErrorHandler {


    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.print( "response.getStatusCode(), response.getStatusText()");
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }
}
