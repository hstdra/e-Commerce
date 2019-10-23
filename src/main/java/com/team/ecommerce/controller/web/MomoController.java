package com.team.ecommerce.controller.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin
public class MomoController {
    @PostMapping("momo/notifyUrl")
    public HashMap notifyUrl(
            @RequestParam(defaultValue = "") String partnerCode,
            @RequestParam(defaultValue = "") String accessKey,
            @RequestParam(defaultValue = "") String requestId,
            @RequestParam(defaultValue = "") String amount,
            @RequestParam(defaultValue = "") String orderId,
            @RequestParam(defaultValue = "") String orderInfo,
            @RequestParam(defaultValue = "") String orderType,
            @RequestParam(defaultValue = "") String transId,
            @RequestParam(defaultValue = "") String errorCode,
            @RequestParam(defaultValue = "") String message,
            @RequestParam(defaultValue = "") String localMessage,
            @RequestParam(defaultValue = "") String payType,
            @RequestParam(defaultValue = "") String signature
    ) {
        System.out.println("Co response");
        System.out.println(requestId);
        System.out.println(message);
        System.out.println(errorCode);

        return new HashMap<String, String>() {{
            put("partnerCode", partnerCode);
            put("accessKey", accessKey);
            put("requestId", requestId);
            put("orderId", orderId);
            put("errorCode", errorCode);
            put("message", message);
            put("responseTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            put("extraData", partnerCode);
            put("signature", partnerCode);
        }};
    }
}
