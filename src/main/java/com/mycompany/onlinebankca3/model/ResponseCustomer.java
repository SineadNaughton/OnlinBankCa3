/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SineadNaughton
 */
@XmlRootElement
public class ResponseCustomer {

    private String message;
    private Customer object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getObject() {
        return object;
    }

    public void setObject(Customer object) {
        this.object = object;
    }

}
