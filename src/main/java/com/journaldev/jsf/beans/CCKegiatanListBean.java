/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author myssd
 */
@Named(value = "cCKegiatanListBean")
@Dependent
@SessionScoped
public class CCKegiatanListBean {

    /**
     * Creates a new instance of CCKegiatanListBean
     */
    public CCKegiatanListBean() {
    }

    public String addKegiatan(){
        String page = "FormKegiatanEntry.jsf";
        return page;
    }    
}
