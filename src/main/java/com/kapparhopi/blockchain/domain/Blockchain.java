package com.kapparhopi.blockchain.domain;

import com.kapparhopi.blockchain.common.StringUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Data
public class Blockchain {

    public List<Block> chain;
    private List<Transaction> pendingTransactions;

    public Blockchain() {
        this.chain = new ArrayList<>();
        chain.add(new Block(100L, "0", "0", 1));
        this.pendingTransactions = new ArrayList<>();
    }

    public Block createNewBlock(Long nonce, String hash, String previousBlockHash) {
        Block newBlock = new Block(nonce, hash, previousBlockHash, chain, pendingTransactions);

        this.pendingTransactions.clear();
        this.chain.add(newBlock);
        return newBlock;
    }

    public Block getLastBlock() {
        return this.chain.get(this.chain.size() - 1);
    }

    public int createNewTransaction(long amount, String sender, String recipient) {
        Transaction transaction = new Transaction(amount, sender, recipient);

        this.pendingTransactions.add(transaction);
        return this.getLastBlock().getIndex() + 1;
    }

    public String hashBlock(String previousBlockHash, Map<Long,List<Transaction>> currentBlockData, Long nonce) {
        String dataAsString = previousBlockHash + nonce.toString() + currentBlockData;

        return StringUtil.applySha256(dataAsString);

    }

    public long proofOfWork(String previousBlockHash, Map<Long,List<Transaction>> currentBlockData) {
        Long nonce = 0L;
        String hash = this.hashBlock(previousBlockHash, currentBlockData, nonce);

        while (!hash.substring(0, 4).equals("0000")) {
            nonce++;
            hash = this.hashBlock(previousBlockHash, currentBlockData, nonce);
        }
        return nonce;


    }


}
