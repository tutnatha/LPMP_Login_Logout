/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.tomcat.util.codec.binary.Base64;
 
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
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
//@Named(value = "scheduleViewHunianHdr")
//@Dependent
@ManagedBean
@ViewScoped
public class ScheduleViewHunianHdr implements Serializable {

    private ScheduleModel eventModel;
     
    private ScheduleModel lazyEventModel;
 
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    //Custom variable
    List<DaftarhunianHdr> listDftrHdr = new ArrayList<DaftarhunianHdr>();

    public String SERVICE_BASE_URI;
    FacesContext fc = FacesContext.getCurrentInstance();    //coba pasang disini.
    //end Custom var
 
    /**
     * Creates a new instance of ScheduleViewHunianHdr
     */
    public ScheduleViewHunianHdr() {
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
    }
    
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));

        //Internal	0	Mon Mar 05 08:00:00 WITA 2018	Mon Mar 05 08:00:00 WITA 2018	        
        Date d1 = new Date(2018, 03, 05);
        Date d2 = new Date(2018, 03, 15);
        //buat parameterize sesuai dgn tgl Hunian Hdr!!!
        eventModel.addEvent(new DefaultScheduleEvent("Internal", contohStart(d1), contohEnd(d2)));
        
        //loop listDftrHdr
        String opsi_penyelenggara = "Umum";
        listDftrHdr = getDaftarhunianHdrPerPenyelenggara(opsi_penyelenggara); //hardcode
        int size = listDftrHdr.size();
        for (DaftarhunianHdr d : listDftrHdr) {
            int jmlPeserta = d.getJmlPeserta();
            int kodeKegiatan = d.getKodeKegiatan();
            String no = d.getNo();
            String penyelenggara = d.getPenyelenggara();
            String ss = d.getSudahSelesai();
            Date mulai = d.getTglMulai();       //new Date(2018, 01, 05);    
            Date selesai = d.getTglSelesai();   //new Date(2018, 01, 15);  
            
            eventModel.addEvent(new DefaultScheduleEvent(penyelenggara + "-" + no, 
          contohStart(mulai), contohEnd(selesai)));
        }
        //end loop
        
        lazyEventModel = new LazyScheduleModel() {
             
            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
                 
                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }   
        };
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    
    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);
         
        return t.getTime();
    }
    
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);
         
        return t.getTime();
    }
    
    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);
         
        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);
         
        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);
         
        return t.getTime();
    }
    
    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);
         
        return t.getTime();
    }
    
    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);     
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }
    
    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }
    
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
         
        return date.getTime();
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
    
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
    
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
//Custom Code
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
        //coba kodePenyelenggara di hardcode dulu

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

    private Date contohStart(Date mulai) {
//        Date d1 = new Date(2018, 03, 05);
        Calendar t = (Calendar) startDay(mulai).clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 0);
        t.set(Calendar.HOUR, 8);
        t.setTime(mulai);   //coba
        
        return t.getTime();
    }
    
    private Date contohEnd(Date selesai) {
//        Date d1 = new Date(2018, 03, 15);
        Calendar t = (Calendar) endDay(selesai).clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 0);
        t.set(Calendar.HOUR, 8);
        t.setTime(selesai); //coba
        
        return t.getTime();
    }
    
    //need parameterize!!!
    private Calendar startDay(Date mulai) {
        Calendar calendar = Calendar.getInstance();
//        calendar.set(2018, 2, 5, 0, 0, 0); 
        calendar.set(mulai.getYear(), mulai.getMonth() - 1, mulai.getDate(), 0, 0, 0);
        
        return calendar;
    }
    
    //0 : january
    private Calendar endDay(Date selesai) {
        Calendar calendar = Calendar.getInstance();
//        calendar.set(2018, 2, 15, 0, 0, 0);
        calendar.set(selesai.getYear(), selesai.getMonth() - 1, selesai.getDate(), 0, 0, 0);
        
        return calendar;
    }    

    //getter and setter
    public List<DaftarhunianHdr> getListDftrHdr() {
        return listDftrHdr;
    }

    public void setListDftrHdr(List<DaftarhunianHdr> listDftrHdr) {
        this.listDftrHdr = listDftrHdr;
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
    //end getter and setter
}
