
//no_trx
//nama kegiatan
//penyelenggara
//tgl mulai
//tgl selesai
//jml peserta
//sudah_selesai

package com.journaldev.jsf.pojo.daftarhunian;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Column;
import java.util.Date;

//@Entity
//@Table(name = "daftarhunian_hdr")
public class DaftarhunianHdr {

    private static final long serialVersionUID = -3009157732242241606L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="no_trx")
//    private int no;
    private String no;          //catatan: pakai String dulu ya..
//    @Column(name="penyelenggara")
    private String penyelenggara;
//    @Column(name="jml_peserta")
    private int jmlPeserta;
//    @Column(name="tgl_mulai")
    private Date tglMulai;
//    @Column(name="tgl_selesai")
    private Date tglSelesai;
//    @Column(name="sudah_selesai")
    private String sudahSelesai;
    private int kodeKegiatan;

    public DaftarhunianHdr() {

    }

    //    public DaftarhunianHdr(int no, String noKamar, String regNo, String isUsed, Date startDate, Date endDate) {
    public DaftarhunianHdr(String no, String penyelenggara, int jmlPeserta, Date tglMulai, Date tglSelesai, 
	String sudahSelesai, int kodeKegiatan) {
        super();
        this.no = no;
        this.penyelenggara = penyelenggara;
        this.jmlPeserta = jmlPeserta;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.sudahSelesai = sudahSelesai;
        this.kodeKegiatan = kodeKegiatan;
    }

//    public int getNo() {
    public String getNo() {
        return no;
    }

//    public void setNo(int no) {
//        this.no = no;
//    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(String penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

    public String getSudahSelesai() {
        return sudahSelesai;
    }

    public void setSudahSelesai(String sudahSelesai) {
        this.sudahSelesai = sudahSelesai;
    }

    public int getJmlPeserta() {
        return jmlPeserta;
    }

    public void setJmlPeserta(int jmlPeserta) {
        this.jmlPeserta = jmlPeserta;
    }

    public Date getTglMulai() {return tglMulai;}

    public void setTglMulai(Date tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Date getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(Date tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public int getKodeKegiatan(){
	return kodeKegiatan;
    }

    public void setKodeKegiatan(int kodeKegiatan){
	this.kodeKegiatan = kodeKegiatan;
    }
}
