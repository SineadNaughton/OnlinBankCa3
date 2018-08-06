/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.services;

import com.mycompany.onlinebankca3.mockdatabase.MockDatabase;
import com.mycompany.onlinebankca3.model.Account;
import com.mycompany.onlinebankca3.model.Customer;
import com.mycompany.onlinebankca3.model.ResponseCustomer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author james
 */
public class CustomerService {

    private Map<Integer, Customer> customers = MockDatabase.getCustomers();
    private Map<Integer, Account> acounts = MockDatabase.getAccounts();

    //bank admin auth
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>(customers.values());
        return list;
    }

    //auth bby customer who has password
    //or auth by admin
    public ResponseCustomer getCustomer(int customerId) {
        ResponseCustomer resp = new ResponseCustomer();
        if (!customers.containsKey(customerId)) {
            resp.setMessage("Customer not found");
            return resp;
        } else {
            resp.setMessage("Customer found");
            resp.setObject(customers.get(customerId));
            return resp;
        }
    }

    //new customer
    public ResponseCustomer addCustomer(Customer customer) {
        ResponseCustomer resp = new ResponseCustomer();
        /*
       if (customer.getFirstName().equals("") || customer.getLastName().equals("")) {
            resp.setMessage("Firstname or lastname cannot be blank");
            resp.setObject(customer);
            return resp;
        }
        if (customer.getAddressStreet().equals("") || customer.getAddressCity().equals("")) {
            resp.setMessage("Address fields cannot be blank");
            resp.setObject(customer);
            return resp;
        }
         */
        if (customer.getPps().length() == 8 || customer.getPps().length() == 9) {

            for (Customer c : customers.values()) {
                if (customer.getPps().equalsIgnoreCase(c.getPps())) {
                    resp.setMessage("Customer already exsists");
                    resp.setObject(c);
                    return resp;
                }
            }

            customer.setCustomerId(customers.size() + 1);
            customer.setCreated(new Date());
            customers.put(customer.getCustomerId(), customer);

            if (customers.containsKey(customer.getCustomerId())) {
                resp.setMessage("Customer has been sucessfully created");
                resp.setObject(customer);
                return resp;
            } else {
                resp.setMessage("Customer not created");
                return resp;
            }
        } else {
            resp.setMessage("PPS invalid");
            resp.setObject(customer);
            return resp;
        }

    }

//update customer *******more checking
    public ResponseCustomer updateCustomer(int customerId, Customer customer) {
        ResponseCustomer resp = new ResponseCustomer();

        if (customers.containsKey(customerId)) {
            Customer custRecord = customers.get(customerId);

            if (!customer.getFirstName().equals("")) {
                custRecord.setFirstName(customer.getFirstName());
            } else {
                resp.setMessage("Firstname cannot be blank");
                return resp;
            }
            if (!customer.getLastName().equals("")) {
                custRecord.setLastName(customer.getLastName());
            } else {
                resp.setMessage("Lastname cannot be blank");
                return resp;
            }
            /*if (!customer.getAddressStreet().equals("")) {
                custRecord.setAddressStreet(customer.getAddressStreet());
            } else {
                resp.setMessage("Street Address cannot be blank");
                return resp;
            }
            if (!customer.getAddressCity().equals("")) {
                custRecord.setAddressCity(customer.getAddressCity());
            } else {
                resp.setMessage("City address cannot be blank");
                return resp;
            }*/

            //not needed
            /*if (!customer.getPps().equalsIgnoreCase(custRecord.getPps())) {
                resp.setMessage("PPS cannot be ammended");
                return resp;
            }*/

            //customers.put(customer.getCustomerId(), customer);
            resp.setMessage("Customer has been sucessfully updated");
            resp.setObject(custRecord);
        } else {
            resp.setMessage("Customer not found");
        }
        return resp;
    }

    public ResponseCustomer removeCustomer(int id) {
        ResponseCustomer resp = new ResponseCustomer();
        if (customers.containsKey(id)) {
            Customer customer = customers.remove(id);
            resp.setMessage("Customer has been sucessfully removed");
            resp.setObject(customer);
            //return resp;
        } else {
            resp.setMessage("Customer not found");
            //return resp;
        }

        return resp;
    }

}
