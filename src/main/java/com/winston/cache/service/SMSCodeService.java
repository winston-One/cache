package com.winston.cache.service;

import com.winston.cache.domin.SMSCode;

public interface SMSCodeService {
    public String sendCodeToSMS(String tele);
    public boolean checkCode(SMSCode smsCode);
}
