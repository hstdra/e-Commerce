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
    private static final Environment env = Environment.selectEnv(Environment.EnvTarget.DEV, Environment.ProcessType.PAY_GATE);
    @Autowired
    OrderRepository orderRepository;

    public CaptureMoMoResponse captureMoMoResponse(Order order, String returnUrl) throws Exception {
        String notifyURL = "https://c99dd512.ngrok.io/momo/notifyUrl";
        String amount = String.valueOf(order.getTotalOrderPrice());
        String orderId = String.valueOf(order.getId());
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderInfo = "Thanh toán đơn hàng #" + orderId;

        return CaptureMoMo.process(env, orderId, requestId, amount, orderInfo, returnUrl, notifyURL, "");
    }

    public QueryStatusTransactionResponse transactionResponse(String orderId, String requestId) throws Exception {
        return QueryStatusTransaction.process(env, orderId, requestId);
    }
}
