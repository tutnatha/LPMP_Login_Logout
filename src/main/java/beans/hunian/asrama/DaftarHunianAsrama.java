/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.hunian.asrama;

import java.util.Date;
import java.util.List;

/**
 *
 * @author myssd
 */
public class DaftarHunianAsrama {
    private int noTrx;
    private String penyelenggara;
    private int jmlPeserta;
    private Date tglMulai;
    private Date tglSelesai;
    private String sudahSelesai;
//    private String kodeKegiatan;
    private int kodeKegiatan;
    private List<DaftarHunianAsramaDtl> daftarHunianAsramaDtls;
    
    public DaftarHunianAsrama() {
    }

    public int getNoTrx() {
        return noTrx;
    }

    public void setNoTrx(int noTrx) {
        this.noTrx = noTrx;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(String penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

    public int getJmlPeserta() {
        return jmlPeserta;
    }

    public void setJmlPeserta(int jmlPeserta) {
        this.jmlPeserta = jmlPeserta;
    }

    public Date getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(Date tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Date getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(Date tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getSudahSelesai() {
        return sudahSelesai;
    }

    public void setSudahSelesai(String sudahSelesai) {
        this.sudahSelesai = sudahSelesai;
    }
    
//    public String getKodeKegiatan() {
    public int getKodeKegiatan() {
        return kodeKegiatan;
    }

//    public void setKodeKegiatan(String kodeKegiatan) {
    public void setKodeKegiatan(int kodeKegiatan) {
        this.kodeKegiatan = kodeKegiatan;
    }

//    public DaftarHunianAsrama(int noTrx, String penyelenggara, int jmlPeserta, Date tglMulai, Date tglSelesai, String sudahSelesai, String kodeKegiatan) {
    public DaftarHunianAsrama(int noTrx, String penyelenggara, int jmlPeserta, Date tglMulai, Date tglSelesai, String sudahSelesai, int kodeKegiatan) {
        this.noTrx = noTrx;
        this.penyelenggara = penyelenggara;
        this.jmlPeserta = jmlPeserta;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.sudahSelesai = sudahSelesai;
        this.kodeKegiatan = kodeKegiatan;
    }

    public List<DaftarHunianAsramaDtl> getDaftarHunianAsramaDtls() {
        return daftarHunianAsramaDtls;
    }

    public void setDaftarHunianAsramaDtls(List<DaftarHunianAsramaDtl> daftarHunianAsramaDtl) {
        this.daftarHunianAsramaDtls = daftarHunianAsramaDtl;
    }
    
}
