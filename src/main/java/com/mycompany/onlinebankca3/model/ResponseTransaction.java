/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SineadNaughton
 */
public class ResponseTransaction {
    
    private String message;
    private Transaction transaction;
    private List<Transaction> transactionList = new ArrayList<>();

    public ResponseTransaction() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
    
    
}
