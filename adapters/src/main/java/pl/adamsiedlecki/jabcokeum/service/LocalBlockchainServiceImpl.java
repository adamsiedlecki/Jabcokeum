package pl.adamsiedlecki.jabcokeum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.adamsiedlecki.jabcokeum.model.Block;
import pl.adamsiedlecki.jabcokeum.util.FileUtil;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalBlockchainServiceImpl implements LocalBlockchainService {

    private final ObjectMapper objectMapper;

    @Value("${this.miner.local.blockchain.path}")
    private String blockchainPath;

    private List<Block> blocks = new ArrayList<>();
    private Block lastBlock;

    @PostConstruct
    public void initFromFiles() {
        blocks = new ArrayList<>();
        lastBlock = null;
        List<File> files = FileUtil.listBlockFilesForFolder(new File(blockchainPath));
        files.forEach(file->{
            var fileContent = FileUtil.readFile(file);
            try {
                Block block = objectMapper.readValue(fileContent, Block.class);
                blocks.add(block);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        if(!blocks.isEmpty()) {
            lastBlock = blocks.get(blocks.size()-1);
        } else {
            pushNewBlock(GENESIS_BLOCK);
        }
    }

    @Override
    public List<Block> getBlocks() {
        return null;
    }

    @Override
    public Block getLastBlock() {
        return lastBlock;
    }

    @Override
    public void pushNewBlock(Block block) {
        lastBlock = block;
        blocks.add(block);

        File file = new File(blockchainPath + "block_" + block.getHeight() + ".jabcokeum");
        try {
            FileUtil.writeToFile(file, objectMapper.writeValueAsString(block));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
