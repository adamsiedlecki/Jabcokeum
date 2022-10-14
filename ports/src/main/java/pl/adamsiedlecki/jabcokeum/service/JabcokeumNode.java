package pl.adamsiedlecki.jabcokeum.service;

import pl.adamsiedlecki.jabcokeum.model.Block;

import java.math.BigInteger;
import java.util.List;

public interface JabcokeumNode {

    String getNetAddress();

    BigInteger getBlockHeight();

    Block getBlock(BigInteger height);

    List<Block> getBlocksAfter(BigInteger height);
}
