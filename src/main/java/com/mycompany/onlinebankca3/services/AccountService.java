/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.services;

import com.mycompany.onlinebankca3.mockdatabase.MockDatabase;
import com.mycompany.onlinebankca3.model.Account;
import com.mycompany.onlinebankca3.model.Customer;
import com.mycompany.onlinebankca3.model.ResponseAccount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SineadNaughton
 */
public class AccountService {

    private Map<Integer, Customer> customers = MockDatabase.getCustomers();
    private Map<Integer, Account> accounts = MockDatabase.getAccounts();

    //get all acc for a customer
    public ResponseAccount getAllAccounts(int customerId) {
        ResponseAccount resp = new ResponseAccount();

        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        List<Account> accList = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getCustomerId() == customerId) {
                accList.add(account);
            }
        }

        resp.setMessage("Accounts found");
        resp.setAccounts(accList);

        return resp;
    }

    //get one
    public ResponseAccount getAccount(int customerId, int accountNo) {
        ResponseAccount resp = new ResponseAccount();

        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }

        Account account = accounts.get(accountNo);

        if (account.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to see account");
            return resp;
        }
        //Map<Long, Account> accounts = customer.getAccounts();

        resp.setMessage("Account found");
        resp.setAccount(account);

        return resp;
    }

    //add
    //admin function
    public ResponseAccount addAccount(int customerId, Account account) {
        //create rsponse instance
        ResponseAccount resp = new ResponseAccount();

        //check customer exists
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        //check account type is valid
        if (account.getAccType().equalsIgnoreCase("current") || account.getAccType().equalsIgnoreCase("savings") || account.getAccType().equalsIgnoreCase("creditcard")) {

            //pull out accounts customer has already
            List<Account> accList = new ArrayList<>();
            for (Account acc : accounts.values()) {
                if (acc.getCustomerId() == customerId) {
                    accList.add(acc);
                }
            }
            //check each account in the customer account array list and make sure the customer does not already have the account type
            for (Account a : accList) {
                if (account.getAccType().equalsIgnoreCase(a.getAccType())) {
                    resp.setMessage("Customer already has a " + a.getAccType() + " account. Link: ");
                    resp.setAccount(a);
                    return resp;
                }
            }

            if (account.getAccType().equalsIgnoreCase("creditcard")) {
                if (account.getCreditLimit() > 0) {
                    account.setCreditLimit(account.getCreditLimit() * -1);
                } else if (account.getCreditLimit() == 0) {
                    resp.setMessage("Please enter a valid credit limit for creditcard account ");
                    return resp;
                }
            }

            //set acc details
            if (account.getSortCode().equals("606060") || account.getSortCode().equals("909090") || account.getSortCode().equals("808080") || account.getSortCode().equals("707070")) {
                account.setAccNo(accounts.size() + 10000000 + 1);
                account.setAccCreated(new Date());
                account.setCustomerId(customerId);
                account.setAccBal(0.0);
                accounts.put(account.getAccNo(), account);
                resp.setAccount(account);
                resp.setMessage("Account created");
                return resp;
            } else {
                resp.setMessage("Invalid sort code entered. Sort Codes are: Dublin 606060, Cork 707070, Waterford 808080, Galway 909090");
                return resp;
            }
        }
        resp.setMessage("invalide account tyoe - must be current, savings, or creditcard");
        return resp;
    }

    //update
    public ResponseAccount updateAccountBranch(int customerId, int accountNo, Account account) {
        //create response instance
        ResponseAccount resp = new ResponseAccount();

        //check if customer exists
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        //check account exists with customer
        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }
        //find acc
        Account accountOnFile = accounts.get(accountNo);

        if (accountOnFile.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to alter account");
            return resp;
        }

        //updaye details
        //set acc details
        if (account.getSortCode().equals("606060") || account.getSortCode().equals("909090") || account.getSortCode().equals("808080") || account.getSortCode().equals("707070")) {

            accountOnFile.setAccDetailsUpdated(new Date());
            accountOnFile.setSortCode(account.getSortCode());
            resp.setAccount(accountOnFile);
            resp.setMessage("Account branch changed, please not only the branch can be altered on an account.  To alter account ype a number account must be set up. If you wish to update balance please do a transaction");
            return resp;
        } else {
            resp.setMessage("Invalid sort code entered. Sort Codes are: Dublin 606060, Cork 707070, Waterford 808080, Galway 909090");
            return resp;
        }

    }

    //close
    public ResponseAccount removeAccount(int customerId, int accountNo) {
        ResponseAccount resp = new ResponseAccount();

        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        }

        //check account exists with customer
        if (!accounts.containsKey(accountNo)) {
            resp.setMessage("Account not found");
            return resp;
        }

        //find account
        Account accountOnFile = accounts.get(accountNo);

        if (accountOnFile.getCustomerId() != customerId) {
            resp.setMessage("Customer not authorised to close account");
            return resp;
        }

        accounts.remove(accountNo);
        resp.setMessage("account removed");
        resp.setAccount(accountOnFile);

        return resp;

    }
}
