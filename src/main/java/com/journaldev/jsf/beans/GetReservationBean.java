package com.journaldev.jsf.beans;

import beans.hunian.asrama.DaftarHunianAsrama;
import beans.hunian.asrama.DaftarHunianAsramaDtl;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@ManagedBean
@RequestScoped
public class GetReservationBean implements Serializable{
	private String no;
	private String penyelenggara;
	private int jmlPeserta;
	private Date tglMulai;
	private Date tglSelesai;
	private String sudahSelesai;
	private int kodeKegiatan;
	
        List<QueryDaftarhunianDlt> queryDaftarhunianDlt = new ArrayList<QueryDaftarhunianDlt>();
        public String SERVICE_BASE_URI;
	
        public GetReservationBean() {
		super();
		// TODO Auto-generated constructor stub
                FacesContext fc = FacesContext.getCurrentInstance();
                SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
    
	}
	
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
//    	try {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
//	String url = "http://localhost:8080/user/article";
//        String url = "http://207.148.66.201:8080/user/daftarhunianHdrs";	//harus dirubah ke app.properties
        String url = SERVICE_BASE_URI+"user/daftarhunianHdrs";
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

    	/* 1.tipu pakai Get: tapi lakukan insert ke data base */
    	/* 2. get select max(id) if exist compare with current row data */
    	    	
//    	}	catch (IOException | ParseException ex) {
    	
//    	}
//        }catch (URISyntaxException e){
//        	FacesContext.getCurrentInstance().addMessage(
//    				null,
//    				new FacesMessage(FacesMessage.SEVERITY_WARN,
//    						url,
//    						uri.toString()));        	
//        }
        
        /*
        if(uri==null){
        	FacesContext.getCurrentInstance().addMessage(
    				null,
    				new FacesMessage(FacesMessage.SEVERITY_WARN,
    						url,
    						"Success!!!"));
    		
        }else{
        	//Tampilkan ke screen browser
        	System.out.println(uri.getPath());
        	FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						url,
						"uri result : "+uri.getPath()));
        }
        */
//    	getDaftarhunianHdrByIdDemo();
    }

    public void getDaftarhunianHdrByIdDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://207.148.66.201:8080/user/daftarhunianHdrs/{no}";
    	String url = SERVICE_BASE_URI+"user/daftarhunianHdrs/{no}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
//        ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr.class, 16);
        ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> responseEntity = 
            restTemplate.exchange(url, HttpMethod.GET, requestEntity, 
            com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr.class, this.getNo());

        com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr dftr = responseEntity.getBody();
        System.out.println("Jml Peserta:"+dftr.getJmlPeserta()+", Kegiatan:"+dftr.getKodeKegiatan()
                 +", No:"+dftr.getNo());
        
//        FacesContext.getCurrentInstance().addMessage(
//				null,
//				new FacesMessage(FacesMessage.SEVERITY_WARN,
//						url,
//						"Jml Peserta:"+dftr.getJmlPeserta()+", Kegiatan:"+dftr.getKodeKegiatan()
//		                 +", No:"+dftr.getNo()));
        
        jmlPeserta = dftr.getJmlPeserta();
        kodeKegiatan = dftr.getKodeKegiatan();
        no = dftr.getNo();
        penyelenggara = dftr.getPenyelenggara();
        sudahSelesai = dftr.getSudahSelesai();
        tglMulai = dftr.getTglMulai();
        tglSelesai = dftr.getTglSelesai();
        
        this.setJmlPeserta(jmlPeserta);
        this.setKodeKegiatan(kodeKegiatan);        
        this.setNo(no);
        this.setPenyelenggara(penyelenggara);
        this.setSudahSelesai(sudahSelesai);
        this.setTglMulai(tglMulai);
        this.setTglSelesai(tglSelesai);
        
        //details
        getDaftarhunianDtls();
    }

    public void getDaftarhunianDtls() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://207.148.66.201:8080/user/daftarhunianDtls";
    	String url = SERVICE_BASE_URI+"user/daftarhunianDtls/{no}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl[]> responseEntity = 
            restTemplate.exchange(url, HttpMethod.GET, requestEntity, 
            com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl[].class, this.getNo());
        com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl[] articles = responseEntity.getBody();
        for(com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtl article : articles) {
              System.out.println("Id:"+article.getNo()+", Title:"+article.getNoKamar()
                      +", Category: "+article.getSeqNo());
              QueryDaftarhunianDlt q = new QueryDaftarhunianDlt();
              //Embeded Key
              DaftarHunianDtlKey k = new DaftarHunianDtlKey();
              String noKamar = article.getNoKamar();
              int noTrx = article.getNo();
              k.setNoKamar(noKamar);
              k.setNoTrx(noTrx);
              q.setDaftarHunianDtlKey(k);
              int jmlTt = 0;	//ambil dari jmlTt master Kamar
              int lantai = 1;	//ambil dari lantai master Kamar
              q.setJmlTt(jmlTt);
              q.setLantai(lantai);
              //add
              queryDaftarhunianDlt.add(q);
        }
    }

   	public List<QueryDaftarhunianDlt> getQueryDaftarhunianDlt() {
		return queryDaftarhunianDlt;
	}

	public void setQueryDaftarhunianDlt(List<QueryDaftarhunianDlt> queryDaftarhunianDlt) {
		this.queryDaftarhunianDlt = queryDaftarhunianDlt;
	}
    
/***  Buatkan method baru : get ws data dari url dibawah ini,
        tetapi retrieve per noTrx saja
        http://207.148.66.201:8080/user/daftarHunianAsrama/{no} ***/        
        
//    public List<DaftarHunianAsrama> getAllListDaftarHunianAsrama(){
    public List<DaftarHunianAsrama> getReservasiPerNoTrx(String noTrx){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
//	String url = "http://207.148.66.201:8080/user/daftarHunianAsrama/{no}";
//        String url = SERVICE_BASE_URI+"user/daftarHunianAsrama/{no}";
        String url = SERVICE_BASE_URI+"user/daftarHunianAsrama2/{no}";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        
        //instantiate Java Class        
        
	ResponseEntity<DaftarHunianAsrama[]> responseEntity = 
                restTemplate.exchange(url, 
                HttpMethod.GET, requestEntity, DaftarHunianAsrama[].class, noTrx);
//        DaftarHunianAsrama[] hdrList = responseEntity.getBody();
       //ia sudah menjasi single class
        DaftarHunianAsrama[] hdrList = responseEntity.getBody();
//	return hdrs;
        List<DaftarHunianAsrama> list = Arrays.asList(hdrList);
        
        int x = list.get(0).getDaftarHunianAsramaDtls().size();
        
        System.out.println("int x :"+x);
        
        return list;
    }

//    exportarPDF()
//    public void exportarPDF() throws JRException, IOException {
    public void printReservasiPerNoTrx() throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUsuario", "Juan Romero");  //remark dulu
        
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        JasperReport jasperSubReport = (JasperReport)JRLoader.loadObjectFromFile(realPath + "reportDfrtHunianAsramaDtl.jasper");

        parametros.put("subreportParameter", jasperSubReport);
        parametros.put("SUBREPORT_DIR", "/WEB-INF/");   //tidak dipakai di jasper file 
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().
                getRealPath("/reportDftrHunianAsrama.jasper"));
        
        String noTrx = this.getNo();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), 
                parametros,                
                new JRBeanCollectionDataSource(this.getReservasiPerNoTrx(noTrx)));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().
                getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void searchByTrxNo(){
        String noTrx = this.getNo();
        DaftarHunianAsrama dha = getReservationByNoTrx(noTrx);
        int jml = dha.getJmlPeserta();
//        String kodeKegiatan = dha.getKodeKegiatan();
        int kodeKegiatan = dha.getKodeKegiatan();
        int inoTrx = dha.getNoTrx();
        String penyelenggara = dha.getPenyelenggara();
        String sudahSelesai = dha.getSudahSelesai();
        Date tglMulai = dha.getTglMulai();
        Date tglSelesai = dha.getTglSelesai();
        
        //Bean setter start
        this.setJmlPeserta(jmlPeserta);
        this.setKodeKegiatan(kodeKegiatan);
        this.setNo(no);
        this.setPenyelenggara(penyelenggara);
        this.setSudahSelesai(sudahSelesai);
        this.setTglMulai(tglMulai);
        this.setTglSelesai(tglSelesai);
        //Bean setter end
        
        List<DaftarHunianAsramaDtl> dhaDtls = dha.getDaftarHunianAsramaDtls();
        int z = dhaDtls.size();
        if(z > 0){
            for(DaftarHunianAsramaDtl dhaDtl : dhaDtls) {
              System.out.println("NoID : " + dhaDtl.getId()+", "
                      + "Title : " + dhaDtl.getRoom().getNo()
                      +", SeqNo: " + dhaDtl.getSeqNo());
              QueryDaftarhunianDlt q = new QueryDaftarhunianDlt();
              //Embeded Key
              DaftarHunianDtlKey k = new DaftarHunianDtlKey();
              String noKamar = dhaDtl.getRoom().getNo();
              int iNoTrx = dhaDtl.getId().getNoTrx();
              k.setNoKamar(noKamar);
              k.setNoTrx(iNoTrx);
              q.setDaftarHunianDtlKey(k);
              int jmlTt = 0;	//ambil dari jmlTt master Kamar
              int lantai = 1;	//ambil dari lantai master Kamar
              q.setJmlTt(jmlTt);
              q.setLantai(lantai);
              //add
              queryDaftarhunianDlt.add(q);
            }
        }
    }
    
    public DaftarHunianAsrama getReservationByNoTrx(String noTrx){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
//	String url = "http://207.148.66.201:8080/user/daftarHunianAsrama/{no}";
        String url = SERVICE_BASE_URI+"user/daftarHunianAsrama/{no}";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        
        //instantiate Java Class        
                
//	ResponseEntity<DaftarHunianAsrama[]> responseEntity =
        ResponseEntity<DaftarHunianAsrama> responseEntity =        
                restTemplate.exchange(url, 
//                HttpMethod.GET, requestEntity, DaftarHunianAsrama[].class, noTrx);
        HttpMethod.GET, requestEntity, DaftarHunianAsrama.class, noTrx);
//        DaftarHunianAsrama[] hdrList = responseEntity.getBody();
        DaftarHunianAsrama dha = responseEntity.getBody();
       
//	return hdrs;
//        List<DaftarHunianAsrama> list = Arrays.asList(hdrList);
        
//        int x = list.get(0).getDaftarHunianAsramaDtls().size();
          int x = dha.getDaftarHunianAsramaDtls().size();

        System.out.println("int x :"+x);
        
//        return list;
        return dha;
    }
}
