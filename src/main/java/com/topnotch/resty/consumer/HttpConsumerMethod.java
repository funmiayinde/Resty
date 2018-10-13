package com.topnotch.resty.consumer;

/**
 * @author funmiayinde
 */
public class HttpConsumerMethod {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String PATCH = "PATCH";
    public static final String DELETE = "DELETE";
    public static final String OPTIONS = "OPTIONS";
    public static final String HEAD = "HEAD";

    public static boolean support(String method) {
        return GET.equals(method) || POST.equals(method) || PUT.equals(method)
                || PATCH.equals(method) || DELETE.equals(method);
    }

}
