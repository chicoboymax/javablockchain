package com.kapparhopi.blockchain.domain;

import lombok.Data;

@Data
public class TransactionPayload {

    private long amount;
    private String sender;
    private String recipient;

    public TransactionPayload(long amount, String sender, String recipient) {
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
    }
}
