package com.dwh.paydemo.Controller;

import com.dwh.paydemo.Alipay.AlipayOpre;
import com.dwh.paydemo.Response.AliTradePrecreatePay;


@RestController
@RequestMapping("/test")
public class TestAlipay {

    @Autowired
    private AlipayOpre alipayOpre;

    @PostMapping("/")
    public String test(@RequestBody AliTradePrecreatePay a){
        return alipayOpre.tradePrecreatePay(a);
    }
}
