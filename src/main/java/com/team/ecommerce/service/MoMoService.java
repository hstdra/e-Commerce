package com.team.ecommerce.service;

import com.mservice.allinone.models.CaptureMoMoResponse;
import com.mservice.allinone.models.QueryStatusTransactionResponse;
import com.mservice.allinone.processor.allinone.CaptureMoMo;
import com.mservice.allinone.processor.allinone.QueryStatusTransaction;
import com.mservice.shared.sharedmodels.Environment;
import com.team.ecommerce.entity.Order;
import com.team.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoMoService {
    private final Environment ENV_MOMO = Environment.selectEnv(Environment.EnvTarget.DEV, Environment.ProcessType.PAY_GATE);
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    org.springframework.core.env.Environment env;

    public synchronized CaptureMoMoResponse captureMoMoResponse(Order order, String returnUrl) throws Exception {
        String notifyURL = env.getProperty("hostname") + "/momo/notifyUrl";
        String amount = String.valueOf(order.getTotalOrderPrice());
        String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderInfo = "Thanh toán đơn hàng #" + order.getId();

        return CaptureMoMo.process(ENV_MOMO, orderId, requestId, amount, orderInfo, returnUrl, notifyURL, "localOrderId=" + order.getId());
    }

    public String getMoMoPayUrl(Order order) {
        if (order.getStatus() == 10) {
            try {
                String returnUrl = env.getProperty("hostname") + "/web/order/" + order.getId();
                return "redirect:" + captureMoMoResponse(order, returnUrl).getPayUrl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public QueryStatusTransactionResponse transactionResponse(String orderId, String requestId) throws Exception {
        return QueryStatusTransaction.process(ENV_MOMO, orderId, requestId);
    }
}
