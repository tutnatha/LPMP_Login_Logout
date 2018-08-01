/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.journaldev.jsf.pojo.daftarhunian.Kamar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
 * @author tutnatha
 */
@ManagedBean
//@ViewScoped   //remark dulu apakah pengaruh?
@SessionScoped
public class CCKamarListBean implements Serializable{
    List<Kamar> listKamar = new ArrayList<Kamar>();
    
    private String selectedNo;
    
    public String SERVICE_BASE_URI;
    FacesContext fc = FacesContext.getCurrentInstance();    //coba pasang disini.

    public CCKamarListBean() {
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
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
        
    public List<Kamar> getKamarListFromRestServer(){
        HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
        
        String url = SERVICE_BASE_URI+"user/kamars";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        
        ResponseEntity<Kamar[]> responseEntity = 
                restTemplate.exchange(url, 
                HttpMethod.GET, requestEntity, Kamar[].class);
        
        Kamar[] kamarList = responseEntity.getBody();
        
        List<Kamar> list = Arrays.asList(kamarList);
        
        int x = list.size();
        
        System.out.println("int x :"+x);
        
        listKamar.clear();
        listKamar.addAll(list);
        
        return listKamar;        
    }
    
    public void onClick(){
        getKamarListFromRestServer();
    }
        
    public String onClickKamar(Kamar kamar){    //with param
//    public String onClickKamar(){
        selectedNo = kamar.getNo();   //remark dulu
        this.setSelectedNo(selectedNo);
        //tampilkan Hunian Hdr per Kamar
        String page = "ViewFormKamar.jsf";
        return page;
    }
    
    //getter and setter
    public List<Kamar> getListKamar() {
        return listKamar;
    }

    public void setListKamar(List<Kamar> listKamar) {
        this.listKamar = listKamar;
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
    
    public String getSelectedNo() {
        return selectedNo;
    }

    public void setSelectedNo(String selectedNo) {
        this.selectedNo = selectedNo;
    }  
    
    //end getter and setter    

}
