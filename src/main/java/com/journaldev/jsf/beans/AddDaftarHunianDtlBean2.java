package com.journaldev.jsf.beans;

import com.course.springbootstarter.penyelenggara.Penyelenggara;
import com.journaldev.jsf.hashmap.PenyelenggaraHashMap;
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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

/* Class ini tidak jadi dipakai */
//@ManagedBean
//@RequestScoped
public class AddDaftarHunianDtlBean2 implements Serializable{

//contoh lagi
//    @ManagedProperty("#{loginBean}") 
//    private LoginBean loginBean;
/* Ndak mau dia.. :D*/
//    @ManagedProperty("#{reservationBean}")
//    private ReservationBean rBean = new ReservationBean();
    
    //noTrx nya DaftarHunianHdr
    private int noTrx;
    
    @ManagedProperty(value="#{param.no}")
    private String no;
    
    private List<String> selectOneMenuKamars = new ArrayList<String>();
    private String selectOneMenuKamar;

    public String SERVICE_BASE_URI; 
    
    ArrayList<Penyelenggara> pList = new ArrayList<Penyelenggara>();
    PenyelenggaraHashMap pHm = new PenyelenggaraHashMap();
            
    public AddDaftarHunianDtlBean2() {
        FacesContext fc = FacesContext.getCurrentInstance();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
        selectOneMenuKamarService();
        
        //Remark du ya..
//        no = rBean.getNo();
        this.setNo(no);
    }

    @PostConstruct
    public void init() {
//        user = userService.find(id);
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
//        String url = "http://207.148.66.201:8080/user/kamars";
        String url = SERVICE_BASE_URI+"user/kamars";
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
//        String url = "http://207.148.66.201:8080/user/daftarhunianDtls";    //harus dirubah ke app.properties
        String url = SERVICE_BASE_URI + "user/daftarhunianDtls";
        com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl objDfrtDtl = new com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl();
        int no = 16; //Integer.parseInt(this.getNo()); //selectOneMenuKamar;
        com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl.MyCompositePK id = 
                new com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl.MyCompositePK();
        id.setNoTrx(no);
        String noKamar = this.getSelectOneMenuKamar();
        id.setNoKamar(noKamar);
        objDfrtDtl.setId(id);
//        objDfrtDtl.setNoKamar(noKamar);
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

    //Getter and Setter
    public int getNoTrx() {
        return noTrx;
    }

    public void setNoTrx(int noTrx) {
        this.noTrx = noTrx;
    }
    
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
    //End Getter and Setter

}
