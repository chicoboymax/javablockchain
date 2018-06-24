package com.kapparhopi.blockchain.service;


import com.kapparhopi.blockchain.domain.Block;
import com.kapparhopi.blockchain.domain.Blockchain;
import com.kapparhopi.blockchain.domain.Transaction;
import com.kapparhopi.blockchain.domain.TransactionPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BlockchainService {

    private Blockchain blockchain;

    @Autowired
    public BlockchainService(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public Blockchain retrieveBlockchain() {
        return blockchain;
    }

    public int createNewTransaction(TransactionPayload transactionPayload) {
        long amount = transactionPayload.getAmount();
        String sender = transactionPayload.getSender();
        String recipient = transactionPayload.getRecipient();
        return blockchain.createNewTransaction(amount, sender, recipient);
    }

    public Block mineNewBlock() {

        String nodeAddress = UUID.randomUUID().toString().replaceAll("-", "");
        Block lastBlock = blockchain.getLastBlock();
        String previousBlockHash = lastBlock.getHash();

        List<Transaction> currentBlockTransactions = blockchain.getPendingTransactions();
        Long currentBlockIndex = lastBlock.getIndex() + 1L;

        Map<Long, List<Transaction>> currentBlockData = new HashMap<>();
        currentBlockData.put(currentBlockIndex, currentBlockTransactions);


        Long nonce = blockchain.proofOfWork(previousBlockHash, currentBlockData);

        String blockHash = blockchain.hashBlock(previousBlockHash, currentBlockData, nonce);

        blockchain.createNewTransaction(12, "00", nodeAddress);

        return blockchain.createNewBlock(nonce, blockHash, previousBlockHash);

    }

}
