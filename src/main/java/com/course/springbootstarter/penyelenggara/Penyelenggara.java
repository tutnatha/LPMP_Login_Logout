/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course.springbootstarter.penyelenggara;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Date;


/**
 *
 * @author myssd
 */
//@Entity
//@Table(name = "penyelenggara")
public class Penyelenggara {
//    @Id
//    @Column(name="kode")
    private String kode;
//    @Column(name="nama_instansi")
    private String nama;

    public Penyelenggara(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    public Penyelenggara() {
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
