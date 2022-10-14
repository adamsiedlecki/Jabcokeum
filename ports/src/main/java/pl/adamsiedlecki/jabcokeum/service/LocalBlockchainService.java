package pl.adamsiedlecki.jabcokeum.service;

import pl.adamsiedlecki.jabcokeum.model.Block;
import pl.adamsiedlecki.jabcokeum.model.transactions.Transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public interface LocalBlockchainService {

    String GENESIS_MESSAGE = """
            Sasin:
             Podatek od nadzwyczajnych zysków nie zmniejszy inwestycji
            
            Business insider: 14 października 2022
             Ceny dóbr i usług konsumpcyjnych były we wrześniu średnio o 17,2 proc. wyższe niż rok wcześniej — wynika z najnowszych danych opublikowanych przez GUS.
             Takiej drożyzny w Polsce nie było w XXI wieku.
            """;

    String GENESIS_MINER_ADDRESS = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE1JJK+nb2u+qfFyU2Lsi/x0Fe/4jdc3ee5f4ePgNNYCWHlNcEEpK/Tyni+XFpR9zIEtFvH09nikmgZ9H8j2rF2Q==";
    Transaction GENESIS_TRANSACTION = Transaction.builder()
            .declaredFee(BigDecimal.ZERO)
            .receiver(GENESIS_MINER_ADDRESS)
            .textMessage(GENESIS_MESSAGE)
            .fundsToSend(BigDecimal.valueOf(50))
            .build();
    OffsetDateTime GENESIS_TIME = OffsetDateTime.of(2022, 10, 14, 20, 10, 0, 0, ZoneOffset.ofHours(2));

    Block GENESIS_BLOCK = new Block(
            List.of(GENESIS_TRANSACTION),
            1,
            null,
            BigInteger.ZERO,
            GENESIS_MINER_ADDRESS, GENESIS_TIME);

    List<Block> getBlocks();

    Block getLastBlock();

    void pushNewBlock(Block block);

    default BigInteger getHeight() {
        return getLastBlock().getHeight();
    }
}
