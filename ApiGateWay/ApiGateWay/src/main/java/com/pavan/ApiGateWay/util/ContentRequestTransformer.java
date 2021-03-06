package com.pavan.ApiGateWay.util;

import com.pavan.ApiGateWay.exception.NoSuchRequestHandlingMethodException;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class ContentRequestTransformer extends ProxyRequestTransformer{
    @Override
    public RequestBuilder transform(HttpServletRequest request) throws NoSuchRequestHandlingMethodException, URISyntaxException, IOException, IOException, URISyntaxException {
        RequestBuilder requestBuilder = predecessor.transform(request);

        String requestContent = request.getReader().lines().collect(Collectors.joining(""));
        if (!requestContent.isEmpty()) {
            StringEntity entity = new StringEntity(requestContent, ContentType.APPLICATION_JSON);
            requestBuilder.setEntity(entity);
        }

        return requestBuilder;
    }
}
