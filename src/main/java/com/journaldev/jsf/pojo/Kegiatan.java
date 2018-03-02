/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.pojo;

import javax.persistence.Column;

/**
 *
 * @author myssd
 */
public class Kegiatan {
private String kode;
//    @Column(name="nama_kegiatan")
    private String namaKegiatan;
    private String nama;
    private String keterangan;

    public Kegiatan(String kode, String namaKegiatan, String nama, String keterangan) {
        this.kode = kode;
        this.namaKegiatan = namaKegiatan;
        this.nama = nama;
        this.keterangan = keterangan;
    }

    public Kegiatan() {
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
  
    
}
