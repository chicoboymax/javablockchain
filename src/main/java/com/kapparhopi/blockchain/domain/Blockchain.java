package com.kapparhopi.blockchain.domain;

import com.kapparhopi.blockchain.common.StringUtil;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Blockchain {

    public List<Block> chain;
    private List<Transaction> pendingTransactions;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        this.createNewBlock((long) 100,"0","0");
    }

    private Block createNewBlock(Long nonce, String hash, String previousBlockHash) {
        Block newBlock = new Block(nonce, hash, previousBlockHash);

        this.pendingTransactions.clear();
        this.chain.add(newBlock);
        return newBlock;
    }

    private Block getLastBlock() {
        return this.chain.get(this.chain.size() - 1);
    }

    private int createNewTransaction(long amount, String sender, String recipient) {
        Transaction transaction = new Transaction(amount, sender, recipient);

        this.pendingTransactions.add(transaction);
        return this.getLastBlock().getIndex() + 1;
    }

    public String hashBlock(String previousBlockHash, List<Transaction> currentBlockData, Long nonce) {
        String dataAsString = previousBlockHash + nonce.toString() + currentBlockData;

        return StringUtil.applySha256(dataAsString);

    }

    public long proofOfWorkk(String previousBlockHash, List<Transaction> currentBlockData) {
        Long nonce = 0L;
        String hash = this.hashBlock(previousBlockHash, currentBlockData, nonce);

        while (!hash.substring(0, 4).equals("0000")) {
            nonce++;
            hash = this.hashBlock(previousBlockHash, currentBlockData, nonce);
        }
        return nonce;


    }

    @Data
    private class Block {
        private int index;
        private LocalDateTime timestamp;
        private List<Transaction> transactions;
        private Long nonce;
        private String hash;
        private String previousBlockHash;

        Block(Long nonce, String hash, String previousBlockHash) {
            this.index = Blockchain.this.getChain().size() + 1;
            this.timestamp = LocalDateTime.now();
            this.transactions = Blockchain.this.getPendingTransactions();
            this.nonce = nonce;
            this.hash = hash;
            this.previousBlockHash = previousBlockHash;
        }
    }


}
