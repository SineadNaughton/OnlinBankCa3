/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SineadNaughton
 */
@XmlRootElement
public class Account {
    
     private long accId;
    private int accNo;
    private String sortCode;
    private long customerId;
    private String accType;
    private Date accCreated;
    private Date accDetailsUpdated;
    private double accBal;
    private double creditLimit;
   
    private Map<Long, Transaction> transactions = new HashMap<>();

    public Account() {
    }

    public Account(long accId, int accNo, String sortCode, long customerId, String accType, Date accCreated, double accBal) {
        this.accId = accId;
        this.accNo = accNo;
        this.sortCode = sortCode;
        this.customerId = customerId;
        this.accType = accType;
        this.accCreated = accCreated;
        this.accBal = accBal;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Date getAccDetailsUpdated() {
        return accDetailsUpdated;
    }

    public void setAccDetailsUpdated(Date accDetailsUpdated) {
        this.accDetailsUpdated = accDetailsUpdated;
    }

    
    
    public long getAccId() {
        return accId;
    }

    public void setAccId(long accId) {
        this.accId = accId;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public Date getAccCreated() {
        return accCreated;
    }

    public void setAccCreated(Date accCreated) {
        this.accCreated = accCreated;
    }

    public double getAccBal() {
        return accBal;
    }

    public void setAccBal(double accBal) {
        this.accBal = accBal;
    }

    public Map<Long, Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<Long, Transaction> transactions) {
        this.transactions = transactions;
    }
    
    
    
}

    

