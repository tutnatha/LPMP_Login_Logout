/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

//import beans.hunian.asrama.DaftarHunianAsrama;
//import com.course.springbootstarter.kegiatan.Kegiatan;
import com.journaldev.jsf.pojo.Kegiatan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
//@RequestScoped
//@ViewScoped     //coba-cobi
@SessionScoped
public class ViewOnlyKegiatanBeans implements Serializable{
    public String SERVICE_BASE_URI;
    FacesContext fc = FacesContext.getCurrentInstance();    //coba pasang disini.
    
    List<Kegiatan> listKegiatan = new ArrayList<Kegiatan>();
            
    public ViewOnlyKegiatanBeans() {
        super();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
//        listKegiatan = this.getKegiatanBerlangsung();
    }
    
    @PostConstruct
    public void init() {
        listKegiatan = getKegiatanBerlangsung();
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
    
    public String clickCari(){
        getKegiatanBerlangsung();
        return "OK";
    }
    
    public List<Kegiatan> getKegiatanBerlangsung(){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
//	String url = "http://207.148.66.201:8080/user/kegiatans";
//        String url = SERVICE_BASE_URI+"user/kegiatans";
        String url = SERVICE_BASE_URI+"user/kegiatans";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        
        //instantiate Java Class        
        
	ResponseEntity<Kegiatan[]> responseEntity = 
                restTemplate.exchange(url, 
                HttpMethod.GET, requestEntity, Kegiatan[].class);
//        DaftarHunianAsrama[] hdrList = responseEntity.getBody();
       //ia sudah menjasi single class
        Kegiatan[] kegiatanList = responseEntity.getBody();
//	return hdrs;
        List<Kegiatan> list = Arrays.asList(kegiatanList);
        
        int x = list.size();    //goblok bikin endlest loop 
        
        System.out.println("int x :"+x);
        
        listKegiatan.addAll(list);
        return listKegiatan;
    }
    
    //Getter and Setter

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

    public List<Kegiatan> getListKegiatan() {
        return listKegiatan;
    }

    public void setListKegiatan(List<Kegiatan> listKegiatan) {
        this.listKegiatan = listKegiatan;
    }
    
    public static void main(String args[]){
        ViewOnlyKegiatanBeans z = new ViewOnlyKegiatanBeans();
        z.getKegiatanBerlangsung();
        int i = z.listKegiatan.size();
        System.out.println("i : "+i);
    }
}
