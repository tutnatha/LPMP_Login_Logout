/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.journaldev.jsf.pojo.Kegiatan;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author myssd
 */
//@Named(value = "formEntryKegiatanBean") //generate oleh netbeans
//@Dependent                              //generate oleh netbeans
@ManagedBean
@ViewScoped
public class FormEntryKegiatanBean implements Serializable{

    private Kegiatan kegiatan;
    
    private String SERVICE_BASE_URI;
    
    //attribute
    private String kode;
    private String namaKegiatan;
    private String nama;
    private String keterangan;
//attribute end
    
    /**
     * Creates a new instance of FormEntryKegiatanBean
     */
    public FormEntryKegiatanBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");                
    
    }
    
    @PostConstruct
    public void init() {
        
    }
    
    private HttpHeaders getHeaders() {
        String credential="mukesh:m123";
        //String credential="tarun:t123";
        String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + encodedCredential);
        return headers;
    }
    
    public void btnSaveClick(){
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://207.148.66.201:8080/user/daftarhunianDtls";    //harus dirubah ke app.properties

        String url = SERVICE_BASE_URI + "user/kegiatans";
        com.course.springbootstarter.kegiatan.Kegiatan objKegiatan = new com.course.springbootstarter.kegiatan.Kegiatan();
//        int no = Integer.parseInt(this.getNo()); //this.getNoTrx();  //selectOneMenuKamar;
//        id.setNoTrx(no);
        objKegiatan.setKode(this.kode);
        objKegiatan.setKeterangan(keterangan);
//        objKegiatan.setNama(nama);
        objKegiatan.setNama(namaKegiatan);
        namaKegiatan = this.getNamaKegiatan();
        objKegiatan.setNamaKegiatan(namaKegiatan);
        
        Sequence seq = new Sequence();
        int seqNo = seq.nextValue();
//        objDfrtDtl.setSeqNo(seqNo);  //remark dulu

        HttpEntity<com.course.springbootstarter.kegiatan.Kegiatan> requestEntity = 
            new HttpEntity<com.course.springbootstarter.kegiatan.Kegiatan>(objKegiatan, headers);
        
        //diganti !! dgn yg dibawah
//        URI uri = restTemplate.postForLocation(url, requestEntity);   
        //Diganti dgn model ResponseEntity 
        ResponseEntity<com.course.springbootstarter.kegiatan.Kegiatan> response = 
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, 
                com.course.springbootstarter.kegiatan.Kegiatan.class);

        //buat kan handle return from web services
        //untuk menampilkan message sukses atau failure saving data..
        HttpStatus statusCode = response.getStatusCode();
        com.course.springbootstarter.kegiatan.Kegiatan retKegiatan = response.getBody();
    }
    
    //getter and setter
    public  Kegiatan getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
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
    
    
    //end setter

    public String getSERVICE_BASE_URI() {
        return SERVICE_BASE_URI;
    }

    public void setSERVICE_BASE_URI(String SERVICE_BASE_URI) {
        this.SERVICE_BASE_URI = SERVICE_BASE_URI;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
