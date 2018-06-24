package com.kapparhopi.blockchain.controller;


import com.kapparhopi.blockchain.domain.Block;
import com.kapparhopi.blockchain.domain.Blockchain;
import com.kapparhopi.blockchain.domain.Transaction;
import com.kapparhopi.blockchain.domain.TransactionPayload;
import com.kapparhopi.blockchain.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BlockchainController {

    private BlockchainService blockchainService;

    @Autowired
    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @GetMapping("/blockchain")
    public Blockchain getBlockchain() {
        return blockchainService.retrieveBlockchain();
    }

    @PostMapping("/transaction")
    public String postTransaction(@RequestBody TransactionPayload transactionPayload) {
        int index = blockchainService.createNewTransaction(transactionPayload);
        return String.format("Transaction will be created in block index %s", index);

    }

    @GetMapping("/mine")
    public Block mine() {
        return blockchainService.mineNewBlock();

    }
}
