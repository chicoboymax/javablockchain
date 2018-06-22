package com.kapparhopi.blockchain.domain;

import com.kapparhopi.blockchain.StringUtil;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Blockchain {

    public List<Block> chain;
    private List<Transaction> pendingTransactions;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
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

    public String hashBlock(String previousBlockHash, Long nonce, List<Transaction> currentBlockData) {
        String dataAsString = previousBlockHash + nonce.toString() + currentBlockData;

        return StringUtil.applySha256(dataAsString);

    }

    @Getter
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
