package com.dwh.paydemo.Alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeCancelModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//支付宝配置类
@Configuration
@PropertySource({"classpath:zfbinfo.properties"})
public class AlipayConfig {

    //参数
    @Value("${open_api_domain}")
    private String serverUrl;

    @Value("${appid}")
    private String appId;

    @Value("${private_key}")
    private  String privateKey ;

    private String format = "json";

    private String charset = "utf-8";

    @Value("${alipay_public_key}")
    private  String alipayPulicKey;

    @Value("${sign_type}")
    private String signType;

    private AlipayClient alipayClient;

    @Bean
   public void initparam(){
       alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey,format, charset, alipayPulicKey, signType);
   }

    /**
     * 扫码支付，预下单接口，带异步通知信号
     * @param notifyUrl（异步通知地址）
     * @return
     * @throws AlipayApiException
     */
    public String tradePrecreatePay_1(AlipayTradePrecreateModel model, String notifyUrl) throws AlipayApiException {
        AlipayTradePrecreateResponse response = tradePrecreatePayToResponse(model,notifyUrl);
        return response.getBody();
    }
    public AlipayTradePrecreateResponse tradePrecreatePayToResponse(AlipayTradePrecreateModel model, String notifyUrl) throws AlipayApiException{
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return alipayClient.execute(request);
    }

    /**
     * 扫码支付，预下单接口，不带异步通知信号
     * @param
     * @return
     * @throws AlipayApiException
     */
    public String tradePrecreatePay_2(AlipayTradePrecreateModel model) throws AlipayApiException {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizModel(model);
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        return response.getBody();
    }

    /**
     * 交易查询接口
     * @param
     * @return
     * @throws AlipayApiException
     */
    public boolean isTradeQuery(AlipayTradeQueryModel model) throws AlipayApiException{
        AlipayTradeQueryResponse response = tradeQuery(model);
        if(response.isSuccess()){
            return true;
        }
        return false;
    }

    public AlipayTradeQueryResponse  tradeQuery(AlipayTradeQueryModel model) throws AlipayApiException{
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);
        return alipayClient.execute(request);
    }

    /**
     * 交易撤销接口
     * @param
     * @return
     * @throws AlipayApiException
     */
    public boolean isTradeCancel(AlipayTradeCancelModel model) throws AlipayApiException{
        AlipayTradeCancelResponse response = tradeCancel(model);
        if(response.isSuccess()){
            return true;
        }
        return false;
    }

    public AlipayTradeCancelResponse tradeCancel(AlipayTradeCancelModel model) throws AlipayApiException{
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizModel(model);
        AlipayTradeCancelResponse response = alipayClient.execute(request);
        return response;
    }
}
