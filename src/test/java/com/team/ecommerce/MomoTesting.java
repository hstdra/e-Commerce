package com.team.ecommerce;

import com.mservice.allinone.models.CaptureMoMoResponse;
import com.mservice.allinone.processor.allinone.CaptureMoMo;
import com.mservice.shared.sharedmodels.Environment;
import org.junit.Test;

public class MomoTesting {
    @Test
    public void Test() throws Exception {
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        long amount = 50000;

        String orderInfo = "Pay With MoMo";
        String returnURL = "https://google.com.vn";
        String notifyURL = "http://83332fda.ngrok.io/momo/notifyUrl";
        String extraData = "";
        String bankCode = "SML";
        String customerNumber = "0963181714";

        Environment environment = Environment.selectEnv(Environment.EnvTarget.DEV, Environment.ProcessType.PAY_GATE);

        CaptureMoMoResponse captureMoMoResponse = CaptureMoMo.process(environment, orderId, requestId, Long.toString(amount), "", returnURL, notifyURL, "");
//        QueryStatusTransactionResponse queryStatusTransactionResponse = QueryStatusTransaction.process(environment, "1561972787557", "1562135830002");


    }
}
