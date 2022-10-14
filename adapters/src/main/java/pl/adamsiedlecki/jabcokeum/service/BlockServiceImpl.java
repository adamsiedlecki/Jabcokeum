package pl.adamsiedlecki.jabcokeum.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.adamsiedlecki.jabcokeum.model.Block;

@Getter
@Setter
@EqualsAndHashCode
@Component
public class BlockServiceImpl implements BlockService {

    private Sha256Service sha256Service;
    private RulesCheckerService rulesCheckerService;

    public String hash(Block block) {
        return sha256Service.hashToString(block.hashCode()+""); //TODO better hashing
    }

    public boolean areTransactionsValid(Block block) {
        return block.getTransactions().stream().allMatch(t ->
                rulesCheckerService.addressHasEnoughFundForFee(t.getSender(), t.getDeclaredFee()) &&
                rulesCheckerService.ifJabcokTransactionThenCheckIfAdressHasEnoughFundsToSendThem(t.getSender(), t.getDeclaredFee()));
    }

}
