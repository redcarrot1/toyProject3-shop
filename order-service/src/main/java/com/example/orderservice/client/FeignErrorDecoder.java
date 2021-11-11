package com.example.orderservice.client;

import com.example.orderservice.error.ApiException;
import com.example.orderservice.error.ExceptionEnum;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        JSONParser jsonParser = new JSONParser(response.body().toString());
        String code="";
        try {
             code = (String)jsonParser.object().get("errorCode");
        } catch (ParseException e) {
            e.printStackTrace();
            return new ParseException("JSON Parse Fail");
        }

        switch (code){
            case "I01":
                return new ApiException(ExceptionEnum.NO_ITEM);
            case "I02":
                return new ApiException(ExceptionEnum.NO_STOCK_ITEM);
            default:
                return new Exception(response.reason());
        }
    }
}
