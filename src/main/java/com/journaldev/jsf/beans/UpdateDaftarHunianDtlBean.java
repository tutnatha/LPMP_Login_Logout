package com.journaldev.jsf.beans;

import com.course.springbootstarter.penyelenggara.Penyelenggara;
import com.journaldev.jsf.hashmap.PenyelenggaraHashMap;
import com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl;
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
import javax.faces.bean.ViewScoped;
import org.springframework.http.HttpStatus;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
//@RequestScoped  //remark dulu
@ViewScoped     //coba-cobi
public class UpdateDaftarHunianDtlBean implements Serializable{

//contoh lagi
//    @ManagedProperty("#{loginBean}") 
//    private LoginBean loginBean;
/* Ndak mau dia.. :D*/
    @ManagedProperty("#{reservationBean}") 
    private ReservationBean reservationBean; // +setter (no getter!)
    
    //noTrx nya DaftarHunianHdr
    private int noTrx;
    
//    @ManagedProperty(value="#{param.no}")
    private String no;
    
    private List<String> selectOneMenuKamars = new ArrayList<String>();
    private String selectOneMenuKamar;

    public String SERVICE_BASE_URI; 
    
    ArrayList<Penyelenggara> pList = new ArrayList<Penyelenggara>();
    PenyelenggaraHashMap pHm = new PenyelenggaraHashMap();
            
    public UpdateDaftarHunianDtlBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
        selectOneMenuKamarService();
        
        //Remark du ya..
//        no = rBean.getNo();
//        this.setNo(reservationBean.getNo());

    }

    @PostConstruct
    public void init() {
        // You can do here your initialization thing based on managed properties, if necessary.
//        user = userService.find(id);
        no = reservationBean.getNo();
	noTrx = reservationBean.getSelectedQueryDaftarhunianDlt().getDaftarHunianDtlKey().getNoTrx();
	selectOneMenuKamar = reservationBean.getSelectedQueryDaftarhunianDlt().getDaftarHunianDtlKey().getNoKamar();
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
//        String url = SERVICE_BASE_URI + "user/daftarhunianDtls";
        String url = SERVICE_BASE_URI + "user/daftarhunianDtls2";
        com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl objDfrtDtl = new com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl();
        int no = Integer.parseInt(this.getNo()); //this.getNoTrx();  //selectOneMenuKamar;
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
        objDfrtDtl.setSeqNo(seqNo);  //remark dulu
//        objDfrtDtl.setSeqNo(1); //buat ngetest aja

        HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl> requestEntity = 
            new HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl>(objDfrtDtl, headers);
        
        //diganti !! dgn yg dibawah
//        URI uri = restTemplate.postForLocation(url, requestEntity);   
        //Diganti dgn model ResponseEntity 
        ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl> response = 
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, 
                com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl.class);

        //buat kan handle return from web services
        //untuk menampilkan message sukses atau failure saving data..
        HttpStatus statusCode = response.getStatusCode();

//return msg
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
//end return msg

        com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl daftarhunianDtl = response.getBody();
        
        if(daftarhunianDtl.getId().getNoKamar() != null){
           //nomor Kamar 
//           String sNo = daftarhunianDtl.getId().getNoTrx();
           Integer iNo = daftarhunianDtl.getId().getNoTrx();
           String sNo = iNo.toString();
           this.setNo(sNo);
           
           //tampilkan message sukses atau failure
           FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        url,
                        statusCode.toString()));
        }
        
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
    
    //Jgn pakai Getter
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
    //End Getter and Setter

    //Tidak Pakai Getter ya..
//    public ReservationBean getReservationBean() {
//        return reservationBean;
//    }

    public void setReservationBean(ReservationBean reservationBean) {
        this.reservationBean = reservationBean;
    }

    public String btnCloseClick(){
        //retrieve by noTrx
        reservationBean.searchByTrxNo();
        return "LPMPFormReservation?faces-redirect=true";
    }

}

