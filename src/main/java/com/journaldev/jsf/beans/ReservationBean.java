package com.journaldev.jsf.beans;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

//import com.concretepage.entity.Article;

@ManagedBean
@RequestScoped
public class ReservationBean implements Serializable{
	private String no;
	private String penyelenggara;
	private int jmlPeserta;
	private Date tglMulai;
	private Date tglSelesai;
	private String sudahSelesai;
	private int kodeKegiatan;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPenyelenggara() {
		return penyelenggara;
	}
	public void setPenyelenggara(String penyelenggara) {
		this.penyelenggara = penyelenggara;
	}
	public int getJmlPeserta() {
		return jmlPeserta;
	}
	public void setJmlPeserta(int jmlPeserta) {
		this.jmlPeserta = jmlPeserta;
	}
	public Date getTglMulai() {
		return tglMulai;
	}
	public void setTglMulai(Date tglMulai) {
		this.tglMulai = tglMulai;
	}
	public Date getTglSelesai() {
		return tglSelesai;
	}
	public void setTglSelesai(Date tglSelesai) {
		this.tglSelesai = tglSelesai;
	}
	public String getSudahSelesai() {
		return sudahSelesai;
	}
	public void setSudahSelesai(String sudahSelesai) {
		this.sudahSelesai = sudahSelesai;
	}
	
//	public String getKodeKegiatan() {
	public int getKodeKegiatan() {
		return kodeKegiatan;
	}
	
//	public void setKodeKegiatan(String kodeKegiatan) {
	public void setKodeKegiatan(int kodeKegiatan) {
		this.kodeKegiatan = kodeKegiatan;
	}
	
	/* coba dulu */
    private HttpHeaders getHeaders() {
    	String credential="mukesh:m123";
    	//String credential="tarun:t123";
    	String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
     	headers.add("Authorization", "Basic " + encodedCredential);
    	return headers;
    }
    
    public void addReservationDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
//	String url = "http://localhost:8080/user/article";
        String url = "http://207.148.66.201:8080/user/daftarhunianHdrs";	//harus dirubah ke app.properties
        com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr objDfrtHdr = new com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr();
        objDfrtHdr.setJmlPeserta(jmlPeserta); 
        objDfrtHdr.setKodeKegiatan(kodeKegiatan);
        //generate auto number
        UUID x = new UUID(1, 9999999);
        no = x.toString();
        objDfrtHdr.setNo(no);
        objDfrtHdr.setPenyelenggara(penyelenggara);
        sudahSelesai = "N";
        objDfrtHdr.setSudahSelesai(sudahSelesai);
        objDfrtHdr.setTglMulai(tglMulai);
        objDfrtHdr.setTglSelesai(tglSelesai);
                
        HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> requestEntity = new HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr>(objDfrtHdr, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        
        //Tampilkan ke screen browser
        System.out.println(uri.getPath());
        FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						url,
						"uri result : "+uri.getPath()));
		
    }

}
