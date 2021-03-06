package com.pavan.ApiGateWay.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Configuration
@ConfigurationProperties(prefix = "api.gateway")
public class ApiGatewayProperties {
    private List<Endpoint> endpoints;

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public static class Endpoint{
        private String path;
        private RequestMethod method;
        private String location;

        public Endpoint(String path, RequestMethod method, String location) {
            this.path = path;
            this.method = method;
            this.location = location;
        }

        public Endpoint() {
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public RequestMethod getMethod() {
            return method;
        }

        public void setMethod(RequestMethod method) {
            this.method = method;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
