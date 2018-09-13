/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

/**
 *
 * @author tutnatha
 */
public class TopTenCountry {
    int no;
    String nama;
    int jmlTz;
    String urlFlag;

    public TopTenCountry() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJmlTz() {
        return jmlTz;
    }

    public void setJmlTz(int jmlTz) {
        this.jmlTz = jmlTz;
    }

    public String getUrlFlag() {
        return urlFlag;
    }

    public void setUrlFlag(String urlFlag) {
        this.urlFlag = urlFlag;
    }
    
    
}
