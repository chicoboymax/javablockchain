package com.kapparhopi.blockchain.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Block {
    private int index;
    private LocalDateTime timestamp;
    private List<Transaction> transactions;
    private Long nonce;
    private String hash;
    private String previousBlockHash;

    Block(Long nonce, String hash, String previousBlockHash, List<Block> chain,
          List<Transaction> pendingTransactions) {
        this.index = chain.size() + 1;
        this.timestamp = LocalDateTime.now();
        this.transactions = new ArrayList<>();
        this.transactions.addAll(pendingTransactions);
        this.nonce = nonce;
        this.hash = hash;
        this.previousBlockHash = previousBlockHash;
    }

    Block(Long nonce, String hash, String previousBlockHash, int index) {
        this.index = index;
        this.timestamp = LocalDateTime.now();
        this.transactions = null;
        this.nonce = nonce;
        this.hash = hash;
        this.previousBlockHash = previousBlockHash;
    }
}
