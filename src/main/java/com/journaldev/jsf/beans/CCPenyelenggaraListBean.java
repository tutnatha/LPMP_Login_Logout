/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author myssd
 */
@Named(value = "cCPenyelenggaraListBean")
@Dependent
public class CCPenyelenggaraListBean {

    /**
     * Creates a new instance of CCPenyelenggaraListBean
     */
    public CCPenyelenggaraListBean() {
    }

    public String addPenyelenggara(){
        String page = "FormPenyelenggaraEntry";
        return page;
    }
}
