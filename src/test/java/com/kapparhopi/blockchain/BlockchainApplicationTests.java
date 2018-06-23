package com.kapparhopi.blockchain;

import com.kapparhopi.blockchain.domain.Blockchain;
import com.kapparhopi.blockchain.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockchainApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void createBlockChain() {
        Blockchain blockchain = new Blockchain();

        /*String previousBlockHash = "sdfd98s7d9s8fg6sd98gd67fg8sf";
        List<Transaction> blockList = new ArrayList<>();
        blockList.add(new Transaction(50, "34h53lk5l345", "s6f87g7sfg86sfdg9d"));
        blockList.add(new Transaction(75, "gdfgdfgdfgdgdf", "s6f87g7sfg86sfdg9d"));
        blockList.add(new Transaction(100, "fgdgdgdgdfgdfrtrtertet", "s6f87g7s6h34i563i4u6fg86sfdg9d"));*/



        System.out.println(blockchain);
    }

}
