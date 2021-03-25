package com.pavan.ApiGateWay.exception;

import javax.servlet.http.HttpServletRequest;

public class NoSuchRequestHandlingMethodException extends Exception {

    public NoSuchRequestHandlingMethodException(HttpServletRequest httpServletRequest) {
    }
}
