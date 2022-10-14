package pl.adamsiedlecki.jabcokeum.service;

import java.math.BigDecimal;

public interface RulesCheckerService {

    boolean addressHasEnoughFundForFee(String sender, BigDecimal declaredFee);

    boolean ifJabcokTransactionThenCheckIfAdressHasEnoughFundsToSendThem(String sender, BigDecimal declaredFee);
}
