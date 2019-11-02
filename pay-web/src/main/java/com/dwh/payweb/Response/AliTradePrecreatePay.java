package com.dwh.payweb.Response;


import lombok.Data;

@Data
public class AliTradePrecreatePay {
    //订单金额
    private String total_amount;
    //订单描述
    private String subject;

}
