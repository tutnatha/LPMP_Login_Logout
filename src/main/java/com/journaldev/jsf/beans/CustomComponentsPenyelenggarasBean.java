/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.course.springbootstarter.penyelenggara.Penyelenggara;
//import com.journaldev.jsf.pojo.Kegiatan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author myssd
 */
@ManagedBean
//@ViewScoped   //remark dulu apakah pengaruh?
@SessionScoped //coba sessi ini
public class CustomComponentsPenyelenggarasBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String kode;
    private String nama;

    //kode penyelenggara yg dipilih
    private String selectedKode;
            
    public String SERVICE_BASE_URI;
    FacesContext fc = FacesContext.getCurrentInstance();    //coba pasang disini.
    
    List<Penyelenggara> listP = new ArrayList<Penyelenggara>();
    private Penyelenggara selectedPenyelenggara;
    
    public CustomComponentsPenyelenggarasBean(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    public CustomComponentsPenyelenggarasBean() {
        super();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
//        getPenyelenggaraBerlangsung();    //hang
    }
    
    @PostConstruct
    public void init(){
//        this.setListP(getPenyelenggaraBerlangsung());    //hang
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

    public List<Penyelenggara> getListP() {
        return listP;
    }

    public void setListP(List<Penyelenggara> listP) {
        this.listP = listP;
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

    public List<Penyelenggara> getPenyelenggaraBerlangsung(){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
//	String url = "http://207.148.66.201:8080/user/penyelenggaras";
//        String url = SERVICE_BASE_URI+"user/penyelenggaras";
        String url = SERVICE_BASE_URI+"user/penyelenggaras";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        
        //instantiate Java Class        
        
	ResponseEntity<Penyelenggara[]> responseEntity = 
                restTemplate.exchange(url, 
                HttpMethod.GET, requestEntity, Penyelenggara[].class);
//        DaftarHunianAsrama[] hdrList = responseEntity.getBody();
       //ia sudah menjasi single class
        Penyelenggara[] kegiatanList = responseEntity.getBody();
//	return hdrs;
        List<Penyelenggara> list = Arrays.asList(kegiatanList);
        
        int x = list.size();
        
        System.out.println("int x :"+x);
        
        //reset
        listP.clear();
        listP.addAll(list);
        return listP;
    }
    
    public void onClick(){
        this.setListP(getPenyelenggaraBerlangsung());
    }
    
    public String onClickPenyelenggara(Penyelenggara penyelenggara){    //with param
//    public String onClickPenyelenggara(){
        selectedKode = penyelenggara.getKode();   //remark dulu
        this.setSelectedKode(selectedKode);
        //tampilkan Hunian Hdr per Penyelenggara
        String page = "CompositeComponentsHunianHdr.jsf";
        return page;
    }

    public String onEditPenyelenggara(Penyelenggara penyelenggara){    //with param
//    public String onClickPenyelenggara(){
        selectedKode = penyelenggara.getKode();   //remark dulu
        this.setSelectedKode(selectedKode);
        //kirik ke next bean
        selectedPenyelenggara = penyelenggara;
        //tampilkan Hunian Hdr per Penyelenggara
        String page = "FormPenyelenggaraUpdate.jsf";
        return page;
    }
    
    public String getSelectedKode() {
        return selectedKode;
    }

    public void setSelectedKode(String selectedKode) {
        this.selectedKode = selectedKode;
    }

    public String getSERVICE_BASE_URI() {
        return SERVICE_BASE_URI;
    }

    public void setSERVICE_BASE_URI(String SERVICE_BASE_URI) {
        this.SERVICE_BASE_URI = SERVICE_BASE_URI;
    }

    public FacesContext getFc() {
        return fc;
    }

    public void setFc(FacesContext fc) {
        this.fc = fc;
    }

    public Penyelenggara getSelectedPenyelenggara() {
        return selectedPenyelenggara;
    }

    public void setSelectedPenyelenggara(Penyelenggara selectedPenyelenggara) {
        this.selectedPenyelenggara = selectedPenyelenggara;
    }
    
    //end getter setter
}
