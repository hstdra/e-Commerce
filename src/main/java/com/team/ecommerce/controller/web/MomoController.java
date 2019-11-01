package com.team.ecommerce.controller.web;

import com.mservice.allinone.models.QueryStatusTransactionResponse;
import com.team.ecommerce.entity.Order;
import com.team.ecommerce.service.MoMoService;
import com.team.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private OrderService orderService;

    @Autowired
    private MoMoService moMoService;


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
            @RequestParam(defaultValue = "") String signature,
            @RequestParam(defaultValue = "") String extraData
    ) {
        Order order = orderService.getOrder(Integer.parseInt(extraData.trim().split("=")[1]));
        try {
            QueryStatusTransactionResponse transactionResponse = moMoService.transactionResponse(orderId, requestId);
            if (transactionResponse.getErrorCode() == 0) {
                order.setStatus(11);
                orderService.saveOrder(order);
            }
        } catch (Exception ignored) {
        }


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
