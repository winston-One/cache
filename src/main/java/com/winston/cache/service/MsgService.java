package com.winston.cache.service;

public interface MsgService {
    public String sendCodeToSMS(String tele);
    public boolean check(String tele, String code);
}
