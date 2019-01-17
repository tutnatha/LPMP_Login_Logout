/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.journaldev.jsf.pojo.daftarhunian.Kamar;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
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
@ManagedBean
//@RequestScoped  //remark dulu
@ViewScoped
public class FormEntryKamarBean implements Serializable{

    private boolean isProtect = true;
    private Kamar kamar;
    
    //attribute
    private String no;
    private int lantai;
    private int jmlTt;
    private String urlPicture;
    private String noWisma;  //17-jan-2019
    //end attribute
    
    private String SERVICE_BASE_URI;

    public FormEntryKamarBean() {
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

//    public void btnSaveClick(){   //balik lagi ke hal. sebelumnya
    public String btnSaveClick(){
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();

//        String url = "http://207.148.66.201:8080/user/daftarhunianDtls";    //harus dirubah ke app.properties
        String url = SERVICE_BASE_URI + "user/kamars";
        
        com.journaldev.jsf.pojo.daftarhunian.Kamar objKamar = new com.journaldev.jsf.pojo.daftarhunian.Kamar();
        objKamar.setJmlTt(jmlTt);
        Sequence seq = new Sequence();
        objKamar.setLantai(lantai);  //remark dulu
        objKamar.setNo(no);
        objKamar.setUrlPicture(urlPicture);
        objKamar.setNoWisma(noWisma); //17-jan-19

        HttpEntity<com.journaldev.jsf.pojo.daftarhunian.Kamar> requestEntity = 
            new HttpEntity<com.journaldev.jsf.pojo.daftarhunian.Kamar>(objKamar, headers);
        
        //diganti !! dgn yg dibawah
//        URI uri = restTemplate.postForLocation(url, requestEntity);   
        //Diganti dgn model ResponseEntity
        ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.Kamar> response = 
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, 
                com.journaldev.jsf.pojo.daftarhunian.Kamar.class);

        //buat kan handle return from web services
        //untuk menampilkan message sukses atau failure saving data..
        HttpStatus statusCode = response.getStatusCode();
 
        if(statusCode.is2xxSuccessful()){
            String status ="Success";
            FacesMessages.info("Info", "Input Kamar Sukses!");
        }
        if(statusCode.is1xxInformational()){
            FacesMessages.info("Info", "is1xxInformational ->" + statusCode.toString());
        }
        if(statusCode.is3xxRedirection()){
            FacesMessages.error("is3xxInformational", statusCode.toString());
        }
        if(statusCode.is4xxClientError()){
            FacesMessages.fatal("is4xxClientError", statusCode.toString());
        }
        if(statusCode.is5xxServerError()){
            FacesMessages.fatal("is5xxServerError", statusCode.toString());
        }
        
        com.journaldev.jsf.pojo.daftarhunian.Kamar retKamar = response.getBody();
        
        return "CCKamarList.jsf";
    }
    
    public boolean isIsProtect() {
        return isProtect;
    }

    public void setIsProtect(boolean isProtect) {
        this.isProtect = isProtect;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public void setKamar(Kamar kamar) {
        this.kamar = kamar;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getLantai() {
        return lantai;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public int getJmlTt() {
        return jmlTt;
    }

    public void setJmlTt(int jmlTt) {
        this.jmlTt = jmlTt;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
    
    public String goBack(){
        return "CCKamarList.jsf";
    }    

//17-jan-2019
    public String getNoWisma() {
        return noWisma;
    }

    public void setNoWisma(String noWisma) {
        this.noWisma = noWisma;
    }

}
