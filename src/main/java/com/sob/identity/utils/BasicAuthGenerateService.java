package com.sob.identity.utils;

import java.util.Base64;

public class BasicAuthGenerateService {

    public String getBasicAuthToken(String clientId,String clientSecret) {
        byte[] encode = Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes());
        return "Basic " + new String(encode);
    }

    public static void main(String[] args) {
        System.out.println( new BasicAuthGenerateService().getBasicAuthToken("admin", "password"));
    }


}
