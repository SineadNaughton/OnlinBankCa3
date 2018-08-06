/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.mockdatabase;

import com.mycompany.onlinebankca3.model.Account;
import com.mycompany.onlinebankca3.model.Customer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sinead Naughton
 */
public class MockDatabase {
    
    
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static Map<Integer, Customer> customers = new HashMap<>();

   // public static Map<Long, Customer> getCustomers() {
   //     return customers;
   // }

    public static Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public static Map<Integer, Customer> getCustomers() {
        return customers;
    }
}
