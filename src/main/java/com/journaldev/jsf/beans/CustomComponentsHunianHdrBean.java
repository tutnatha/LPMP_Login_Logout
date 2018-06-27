/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.course.springbootstarter.penyelenggara.Penyelenggara;
import com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
@ViewScoped
public class CustomComponentsHunianHdrBean implements Serializable{
    public String SERVICE_BASE_URI;
    FacesContext fc = FacesContext.getCurrentInstance();    //coba pasang disini.
    
    List<DaftarhunianHdr> listDftrHdr = new ArrayList<DaftarhunianHdr>();

    @ManagedProperty("#{customComponentsPenyelenggarasBean}")
    private CustomComponentsPenyelenggarasBean sourceBean;
    
    //parameter untuk meretrieve web services
    public String choosedKode;

    public boolean itCallFromCCHunianHdr = false;  
    
    public CustomComponentsHunianHdrBean() {
        super();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");    
    }
    
    public String selectedNo; //No Hunian Hdr
    
    @PostConstruct
    public void init(){
        this.setChoosedKode(sourceBean.getSelectedKode());  //jalan jika: sourceBean mengunakan @Session Scope
        
        getDaftarhunianHdrPerPenyelenggara(choosedKode);    //@Session atau @View mempengaruhi getter & Setter variable 
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

    public List<DaftarhunianHdr> getDaftarhunianHdrPerPenyelenggara(String kodePenyelenggara){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
//	String url = "http://207.148.66.201:8080/user/daftarhunianHdrs3/{no}";
        String url = SERVICE_BASE_URI+"user/daftarhunianHdrs3/{no}";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        
        //instantiate Java Class        
        
	ResponseEntity<DaftarhunianHdr[]> responseEntity = 
                restTemplate.exchange(url, 
                HttpMethod.GET, requestEntity, DaftarhunianHdr[].class, kodePenyelenggara);
//        DaftarHunianAsrama[] hdrList = responseEntity.getBody();
       //ia sudah menjasi single class
        DaftarhunianHdr[] kegiatanList = responseEntity.getBody();
//	return hdrs;
        List<DaftarhunianHdr> list = Arrays.asList(kegiatanList);
        
        int x = list.size();
        
        System.out.println("int x :"+x);
        
        listDftrHdr.addAll(list);
        return listDftrHdr;
    }

    public String onClickHunianHdr(DaftarhunianHdr selectedHunianHdr){    //with param
//    public String onClickPenyelenggara(){
        selectedNo = selectedHunianHdr.getNo();   //remark dulu
        this.setSelectedNo(selectedNo);
        
        //coba diset Who Am I atau Sy lah yg panggil kamu!!!
        //buatkan sebuah var di LPMPViewOnlyReservation
        //bahwa sy adalah CCHHB CustomComponentsHunianHdrBean
        itCallFromCCHunianHdr = true;
        this.setItCallFromCCHunianHdr(itCallFromCCHunianHdr);
        
        //tampilkan Daftar Hunian Detail
//        String page = "forms/LPMPViewOnlyReservation.jsf";
        String page = "LPMPViewOnlyReservation2.jsf";
        return page;
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

    public List<DaftarhunianHdr> getListDftrHdr() {
        return listDftrHdr;
    }

    public void setListDftrHdr(List<DaftarhunianHdr> listDftrHdr) {
        this.listDftrHdr = listDftrHdr;
    }

    public CustomComponentsPenyelenggarasBean getSourceBean() {
        return sourceBean;
    }

    public void setSourceBean(CustomComponentsPenyelenggarasBean sourceBean) {
        this.sourceBean = sourceBean;
    }

    public String getChoosedKode() {
        return choosedKode;
    }

    public void setChoosedKode(String choosedKode) {
        this.choosedKode = choosedKode;
    }

    public String getSelectedNo() {
        return selectedNo;
    }

    public void setSelectedNo(String selectedNo) {
        this.selectedNo = selectedNo;
    }

    public boolean isItCallFromCCHunianHdr() {
        return itCallFromCCHunianHdr;
    }

    public void setItCallFromCCHunianHdr(boolean itCallFromCCHunianHdr) {
        this.itCallFromCCHunianHdr = itCallFromCCHunianHdr;
    }
        
    
}
