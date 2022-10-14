package pl.adamsiedlecki.jabcokeum.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.adamsiedlecki.jabcokeum.model.transactions.Transaction;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Block {

    private List<Transaction> transactions;
    private long nonce;
    private String lastBlockHash;
    private BigInteger height;
    private String minerAddress;
    private OffsetDateTime blockTimeDeclaredByMiner;

}
