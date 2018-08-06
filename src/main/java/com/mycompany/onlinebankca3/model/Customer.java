/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SineadNaughton
 */
@XmlRootElement
public class Customer {

    private String firstName;
    private String lastName;
    private int customerId;
    private String addressStreet;
    private String addressCity;
    private Date dob;
    private String password;
    private Date created;
    private String pps;

    private Map<Integer, Integer> bankAccounts = new HashMap<>();
    private List<Link> links = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstName, String lastName, int customerId, String addressStreet, String addressCity, Date dob, String password, Date created, String pps) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerId = customerId;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.dob = dob;
        this.password = password;
        this.created = created;
        this.pps = pps;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPps() {
        return pps;
    }

    public void setPps(String pps) {
        this.pps = pps;
    }

    public Map<Integer, Integer> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Map<Integer, Integer> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);

    }
}
