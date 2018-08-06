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
public class Link {
     private String link;
    private String rel;



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }   
}
