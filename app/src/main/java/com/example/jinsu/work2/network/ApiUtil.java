package com.example.jinsu.work2.network;

public class ApiUtil {
    public static RetroClient<RetroService> retroClient = new RetroClient<>();
    public static RetroClient<RetroService> tokenClient = new RetroClient<>();

    public static RetroService getRetroService()
    {
        return retroClient.getClient(RetroService.class);
    }

    public static RetroService getTokenService()
    {
        return tokenClient.getClient(RetroService.class);
    }
}
