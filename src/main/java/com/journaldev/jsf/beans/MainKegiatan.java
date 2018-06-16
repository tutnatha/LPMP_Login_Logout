/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.journaldev.jsf.pojo.Kegiatan;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class MainKegiatan {

    public String SERVICE_BASE_URI;
    FacesContext fc = FacesContext.getCurrentInstance();    //coba pasang disini.
    
    List<Kegiatan> listKegiatan = new ArrayList<Kegiatan>();
    
    public MainKegiatan() {
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
    
    public List<Kegiatan> getKegiatanBerlangsung(){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://207.148.66.201:8080/user/kegiatans";
//        String url = SERVICE_BASE_URI+"user/kegiatans";
//        String url = SERVICE_BASE_URI+"user/kegiatans";
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
        
        int x = this.getKegiatanBerlangsung().size();
        
        System.out.println("int x :"+x);
        
        listKegiatan.addAll(list);
        return listKegiatan;
    }
    
    public static void main(String args[]){
        MainKegiatan z = new MainKegiatan();
        z.getKegiatanBerlangsung();
        int i = z.listKegiatan.size();
        System.out.println("i : "+i);
    }
}
