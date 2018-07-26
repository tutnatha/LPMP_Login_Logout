/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 *
 * @author myssd
 */
@ManagedBean
@ViewScoped
public class CustomComponentsHunianHdrPerPenyelanggara implements Serializable{
    public String SERVICE_BASE_URI;
    FacesContext fc = FacesContext.getCurrentInstance();    //coba pasang disini.
    
    List<DaftarhunianHdr> listDftrHdr = new ArrayList<DaftarhunianHdr>();

    public CustomComponentsHunianHdrPerPenyelanggara(){
        super();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");    
    }
    
    @PostConstruct
    public void init(){
        
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
    
/* ini bean class yg gak jadi ky nya. */    
}
