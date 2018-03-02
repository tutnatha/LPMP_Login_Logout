package com.journaldev.jsf.beans;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtlKey;
import com.journaldev.jsf.pojo.daftarhunian.QueryDaftarhunianDlt;

@ManagedBean
@RequestScoped
public class AddDaftarHunianDtlBean implements Serializable{

    private List<String> selectOneMenuKamars = new ArrayList<String>();

    private String selectOneMenuKamar;

    public AddDaftarHunianDtlBean() {
        selectOneMenuKamarService();
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

    public List<String> selectOneMenuKamarService(){
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://207.148.66.201:8080/user/kamars";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.Kamar[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, com.journaldev.jsf.pojo.daftarhunian.Kamar[].class);
        com.journaldev.jsf.pojo.daftarhunian.Kamar[] kamars = responseEntity.getBody();

        for(com.journaldev.jsf.pojo.daftarhunian.Kamar kamar : kamars) {
            String s = kamar.getNo(); //hanya mindahin ke var saja it's OK.
            selectOneMenuKamars.add(s);
        }
        return selectOneMenuKamars;
    }

    public void btnSelectItemsOKCLick(){
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://207.148.66.201:8080/user/daftarhunianDtls";    //harus dirubah ke app.properties
        com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl objDfrtDtl = new com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl();
        int no = 16; //Integer.parseInt(this.getNo()); //selectOneMenuKamar;
        objDfrtDtl.setNo(no);
        String noKamar = this.getSelectOneMenuKamar();
        objDfrtDtl.setNoKamar(noKamar);
        //generate auto number
//        UUID x = new UUID(1, 9999999);  //not use
        Sequence seq = new Sequence();
        int seqNo = seq.nextValue();
        objDfrtDtl.setSeqNo(seqNo);

        HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl> requestEntity = new                         HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl>(objDfrtDtl, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);

        //return "LPMPFormGetReservation";
    }

    //getters and setters of selectOneMenuKamar

    public String getSelectOneMenuKamar() {
        return selectOneMenuKamar;
    }

    public void setSelectOneMenuKamar(String selectOneMenuKamar) {
        this.selectOneMenuKamar = selectOneMenuKamar;
    }

    public List<String> getSelectOneMenuKamars() {
//        selectOneMenuKamars = selectOneMenuKamarService();
        return selectOneMenuKamars;
    }

    public void setSelectOneMenuKamars(List<String> selectOneMenuKamars) {
        this.selectOneMenuKamars = selectOneMenuKamars;
    }

}
