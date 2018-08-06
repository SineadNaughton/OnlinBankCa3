/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SineadNaughton
 */
@XmlRootElement
public class Transaction {

    private long transactionId;//set by service
    private double transactionAmount;
    private Date transactionDate;//set by service
    private String transactionType;//set by service
    private String transactionComments;
    private String transferSource;//set by service
    private int accNoTransferTo; 
    private String sortCodeTransferTo;
    private String commentsOnTransfer;
    
    

    public Transaction() {
    }

    public Transaction(long transactionId, double transactionAmount, Date transactionDate, String transactionType, String transactionComments, int accNoTransferTo, String sortCodeTransferTo, String commentsOnTransfer) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionComments = transactionComments;
        this.accNoTransferTo = accNoTransferTo;
        this.sortCodeTransferTo = sortCodeTransferTo;
        this.commentsOnTransfer = commentsOnTransfer;
    }


    public String getTransferSource() {
        return transferSource;
    }

    public void setTransferSource(String transferSource) {
        this.transferSource = transferSource;
    }



    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionComments() {
        return transactionComments;
    }

    public void setTransactionComments(String transactionComments) {
        this.transactionComments = transactionComments;
    }

    public int getAccNoTransferTo() {
        return accNoTransferTo;
    }

    public void setAccNoTransferTo(int accNoTransferTo) {
        this.accNoTransferTo = accNoTransferTo;
    }

    public String getSortCodeTransferTo() {
        return sortCodeTransferTo;
    }

    public void setSortCodeTransferTo(String sortCodeTransferTo) {
        this.sortCodeTransferTo = sortCodeTransferTo;
    }

    public String getCommentsOnTransfer() {
        return commentsOnTransfer;
    }

    public void setCommentsOnTransfer(String commentsOnTransfer) {
        this.commentsOnTransfer = commentsOnTransfer;
    }
    
    

}
