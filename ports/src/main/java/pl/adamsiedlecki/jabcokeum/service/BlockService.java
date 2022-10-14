package pl.adamsiedlecki.jabcokeum.service;

import pl.adamsiedlecki.jabcokeum.model.Block;

public interface BlockService {

    public String hash(Block block);

    public boolean areTransactionsValid(Block block);
}
