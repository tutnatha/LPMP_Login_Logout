/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.course.springbootstarter.penyelenggara.Penyelenggara;
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
//@Named(value = "formEntryPenyelenggara")  //fatal : jgn digunakan
//@Dependent                                //fatal : reserch dulu lebih lanjut
@ManagedBean
@ViewScoped
public class FormEntryPenyelenggara implements Serializable{

    private Penyelenggara penyelenggara;
    
    private String SERVICE_BASE_URI;
    
    //attribute
    private String kode;
    private String nama;

    /**
     * Creates a new instance of FormEntryPenyelenggara
     */
    public FormEntryPenyelenggara() {
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

        String url = SERVICE_BASE_URI + "user/penyelenggaras";
        com.course.springbootstarter.penyelenggara.Penyelenggara objPenyelenggara = new com.course.springbootstarter.penyelenggara.Penyelenggara();
//        int no = Integer.parseInt(this.getNo()); //this.getNoTrx();  //selectOneMenuKamar;
//        id.setNoTrx(no);
        kode = this.getKode();
        nama = this.getNama();
        objPenyelenggara.setKode(kode); //"kodeZ"
        objPenyelenggara.setNama(nama); //"namaZ"
        
        Sequence seq = new Sequence();
        int seqNo = seq.nextValue();
//        objDfrtDtl.setSeqNo(seqNo);  //remark dulu

        HttpEntity<com.course.springbootstarter.penyelenggara.Penyelenggara> requestEntity = 
            new HttpEntity<com.course.springbootstarter.penyelenggara.Penyelenggara>(objPenyelenggara, headers);
        
        //diganti !! dgn yg dibawah
//        URI uri = restTemplate.postForLocation(url, requestEntity);   
        //Diganti dgn model ResponseEntity 
        ResponseEntity<com.course.springbootstarter.penyelenggara.Penyelenggara> response = 
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, 
                com.course.springbootstarter.penyelenggara.Penyelenggara.class);

        //buat kan handle return from web services
        //untuk menampilkan message sukses atau failure saving data..
        HttpStatus statusCode = response.getStatusCode();
        com.course.springbootstarter.penyelenggara.Penyelenggara retPenyelenggara = response.getBody();
    }
    
    public Penyelenggara getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(Penyelenggara penyelenggara) {
        this.penyelenggara = penyelenggara;
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
