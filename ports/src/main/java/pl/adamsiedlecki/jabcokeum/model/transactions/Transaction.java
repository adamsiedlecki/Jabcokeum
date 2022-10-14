package pl.adamsiedlecki.jabcokeum.model.transactions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Transaction {

    private BigDecimal declaredFee;
    private BigDecimal fundsToSend;
    private String receiver;
    private String signature;
    private String sender;
    private String textMessage;
}
