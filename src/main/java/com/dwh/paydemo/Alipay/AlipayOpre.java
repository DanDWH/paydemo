package com.dwh.paydemo.Alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.dwh.paydemo.Response.AliTradePrecreatePay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayOpre {

    @Autowired
    private AlipayConfig alipayConfig;
    /**
     * ***********************
     * 扫码支付
     * ***********************
     */
    /**
     * 预下单接口
     */
    public String tradePrecreatePay(AliTradePrecreatePay a){
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setSubject(a.getSubject());
        model.setTotalAmount(a.getTotal_amount());
        model.setOutTradeNo("20150320010101001");
        model.setTimeoutExpress("30m");
        model.setStoreId("test");
        try {
            String resultStr = alipayConfig.tradePrecreatePay_2(model);
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            String qr_code = jsonObject.getJSONObject("alipay_trade_precreate_response").getString("qr_code");
            return qr_code;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
