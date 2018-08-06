/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.services;

import com.mycompany.onlinebankca3.mockdatabase.MockDatabase;
import com.mycompany.onlinebankca3.model.Account;
import com.mycompany.onlinebankca3.model.Customer;
import com.mycompany.onlinebankca3.model.ResponseTransaction;
import com.mycompany.onlinebankca3.model.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SineadNaughton
 */
public class TransactionService {

    private Map<Integer, Account> accounts = MockDatabase.getAccounts();
    private Map<Integer, Customer> customers = MockDatabase.getCustomers();

    //see all
    public ResponseTransaction getAllTransactions(int customerId, int accountNo) {
        ResponseTransaction resp = new ResponseTransaction();
        // do entities exsist?
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }
        //is customer allowed to view?
        Account account = accounts.get(accountNo);

        if (account.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to see account");
            return resp;
        }

        //above passed show transactions
        List<Transaction> transList = new ArrayList<>(account.getTransactions().values());
        resp.setMessage("transactions for account " + accountNo + " : ");
        resp.setTransactionList(transList);
        return resp;
    }

    //see by date
    public ResponseTransaction getTransactionsByDate(int customerId, int accountNo, Date start, Date end) {
        ResponseTransaction resp = new ResponseTransaction();
        // do entities exsist?
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }
        //is customer allowed to view?
        Account account = accounts.get(accountNo);

        if (account.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to see account");
            return resp;
        }

        //above passed show transactions
        List<Transaction> transList = new ArrayList<>(account.getTransactions().values());
        List<Transaction> transListByDate = new ArrayList<>();
        for (Transaction t : transList) {
            Date date = t.getTransactionDate();
            if (date.compareTo(start) >= 0 && date.compareTo(end) <= 0) {
                transListByDate.add(t);
            }
        }
        resp.setMessage("transactions for for account " + accountNo + " during this period: ");
        resp.setTransactionList(transListByDate);
        return resp;

    }

    //see by debit or credit
    public ResponseTransaction getTransactionsByDebitorCredit(int customerId, int accountNo, String filter) {
        ResponseTransaction resp = new ResponseTransaction();
        // do entities exsist?
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }
        //is customer allowed to view?
        Account account = accounts.get(accountNo);

        if (account.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to see account");
            return resp;
        }

        //above passed show transactions
        List<Transaction> transList = new ArrayList<>(account.getTransactions().values());
        List<Transaction> transListFilter = new ArrayList<>();
        if (filter.equalsIgnoreCase("debit")) {
            for (Transaction t : transList) {
                if (t.getTransactionAmount() < 0) {
                    transListFilter.add(t);
                }
            }
        } else if (filter.equalsIgnoreCase("credit")) {
            for (Transaction t : transList) {
                if (t.getTransactionAmount() > 0) {
                    transListFilter.add(t);
                }
            }
        }
        resp.setMessage(filter + " transactions for for account " + accountNo + " : ");
        resp.setTransactionList(transListFilter);
        return resp;

    }

    //see one
    public ResponseTransaction getTransaction(int customerId, int accountNo, long transactionId) {
        ResponseTransaction resp = new ResponseTransaction();
        // do entities exsist?
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }
        //is customer allowed to view?
        Account account = accounts.get(accountNo);

        if (account.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to see account");
            return resp;
        }

        if (!account.getTransactions().containsKey(transactionId)) {
            resp.setMessage("transaction not found");
            return resp;
        }

        //above passed show transactions
        Transaction transaction = accounts.get(accountNo).getTransactions().get(transactionId);
        resp.setMessage("transaction " + transactionId + " for account no. " + accountNo + " : ");
        resp.setTransaction(transaction);
        return resp;
    }

    //deposit
    public ResponseTransaction depoitFunds(int customerId, int accountNo, Transaction transaction) {
        ResponseTransaction resp = new ResponseTransaction();

        // do entities exsist?
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }
        //is amount allowed
        if (transaction.getTransactionAmount() <= 0) {
            resp.setMessage("Transaction not complete, Please enter a valid deposit amount");
            return resp;
        }

        Account account = accounts.get(accountNo);
        //make new deposit
        Map<Long, Transaction> transactions = account.getTransactions();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionId(transactions.size() + 1);
        transaction.setTransactionType("Deposit");

        //update account
        account.setAccBal(account.getAccBal() + transaction.getTransactionAmount());

        //save transaction and send response
        transactions.put(transaction.getTransactionId(), transaction);
        resp.setMessage("Deposit complete");
        resp.setTransaction(transaction);
        return resp;

    }

    //withdraw on card
    public ResponseTransaction withdrawFundsDebitCard(int customerId, int accountNo, Transaction transaction) {
        ResponseTransaction resp = new ResponseTransaction();
        // do entities exsist?
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }
        //is customer allowed to withdraw
        Account accountFrom = accounts.get(accountNo);
        if (accountFrom.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to see account");
            return resp;
        }

        //check withdraw amount is valid
        if (transaction.getTransactionAmount() == 0) {
            resp.setMessage("Transaction not complete, Please enter a valid deposit amount");
            return resp;
        }

        //convert withdraw amount to a minus 
        if (transaction.getTransactionAmount() > 0) {
            transaction.setTransactionAmount(transaction.getTransactionAmount() * -1);
        }

        //check if enough to withdraw
        if (accountFrom.getAccType().equalsIgnoreCase("current") || accountFrom.getAccType().equalsIgnoreCase("savings")) {
            if (accountFrom.getAccBal() + transaction.getTransactionAmount() < 0) {
                resp.setMessage("You do not have enough funds to make this withdrawl");
                return resp;
            }
        } else if (accountFrom.getAccType().equalsIgnoreCase("creditcard")) {
            if (accountFrom.getAccBal() + transaction.getTransactionAmount() < accountFrom.getCreditLimit()) {
                resp.setMessage("This transaction exceeds your credit limit");
                return resp;
            }
        }

        Map<Long, Transaction> transactions = accountFrom.getTransactions();

        transaction.setTransactionDate(new Date());
        transaction.setTransactionId(transactions.size() + 1);
        transaction.setTransactionType("Withdrawal");

        //update account
        accountFrom.setAccBal(accountFrom.getAccBal() + transaction.getTransactionAmount());
        if (accountFrom.getAccType().equalsIgnoreCase("creditcard")) {
            accountFrom.setCreditLimit(accountFrom.getCreditLimit() + transaction.getTransactionAmount());
        }

        //save transaction and send response
        transactions.put(transaction.getTransactionId(), transaction);
        resp.setMessage("Withdrawl complete");
        resp.setTransaction(transaction);
        return resp;

    }

    //trsansfer
    public ResponseTransaction transferFunds(int customerId, int accountNo, Transaction transaction) {
        ResponseTransaction resp = new ResponseTransaction();
        // do entities exsist?
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account to transfer from not found ");
            return resp;
        }

        //is customer allowed to transfer
        Account accountFrom = accounts.get(accountNo);
        if (accountFrom.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to see account");
            return resp;
        }

        //does transfer account exist
        if (!accounts.containsKey(transaction.getAccNoTransferTo())) {
            resp.setMessage("Account to transfer to not found");
            return resp;
        }
        //check that the sort code matches
        Account accountTo = accounts.get(transaction.getAccNoTransferTo());
        if (!accountTo.getSortCode().equalsIgnoreCase(transaction.getSortCodeTransferTo())) {
            resp.setMessage("Account and sort code to transfer to do not match");
            return resp;
        }

        //is it a valid tranaction amount
        if (transaction.getTransactionAmount() == 0) {
            resp.setMessage("Transaction not complete, Please enter a valid transfer amount");
            return resp;
        }
        //make minus for transfer out
        if (transaction.getTransactionAmount() > 0) {
            transaction.setTransactionAmount(transaction.getTransactionAmount() * -1);
        }

        //check if enough to transfer
        if (accountFrom.getAccType().equalsIgnoreCase("current") || accountFrom.getAccType().equalsIgnoreCase("savings")) {
            if (accountFrom.getAccBal() + transaction.getTransactionAmount() < 0) {
                resp.setMessage("You do not have enough funds to make this transfer");
                return resp;
            }
        } else if (accountFrom.getAccType().equalsIgnoreCase("creditcard")) {
            if (accountFrom.getAccBal() + transaction.getTransactionAmount() < accountFrom.getCreditLimit()) {
                resp.setMessage("This transaction exceeds your credit limit");
                return resp;
            }
        }

        Map<Long, Transaction> transactions = accountFrom.getTransactions();
        //set tranaction on this acc
        transaction.setTransactionDate(new Date());
        transaction.setTransactionId(transactions.size() + 1);
        transaction.setTransactionType("Transfer out");

        //update account
        accountFrom.setAccBal(accountFrom.getAccBal() + transaction.getTransactionAmount());
        if (accountFrom.getAccType().equalsIgnoreCase("creditcard")) {
            accountFrom.setCreditLimit(accountFrom.getCreditLimit() + transaction.getTransactionAmount());
        }
        transactions.put(transaction.getTransactionId(), transaction);

        //created transaction for acc receiving funds
        Transaction transTo = new Transaction();
        Map<Long, Transaction> transactionsAccTo = accountTo.getTransactions();

        transTo.setTransactionAmount(transaction.getTransactionAmount() * -1);//set reansaction amoun to positive
        transTo.setTransactionDate(new Date());
        transTo.setTransactionId(transactionsAccTo.size() + 1);
        transTo.setTransactionType("Transfer in");
        transTo.setTransferSource("transferred from:" + accountFrom.getAccNo());

        accountTo.setAccBal(accountTo.getAccBal() + transaction.getTransactionAmount());
        transactionsAccTo.put(transTo.getTransactionId(), transTo);
        //save transaction and send response
        resp.setMessage("Transfer complete");
        resp.setTransaction(transaction);
        return resp;

    }
}
