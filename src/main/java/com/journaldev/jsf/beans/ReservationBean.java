package com.journaldev.jsf.beans;

import beans.hunian.asrama.DaftarHunianAsrama;
import beans.hunian.asrama.DaftarHunianAsramaDtl;
import com.journaldev.jsf.pojo.daftarhunian.DaftarHunianDtlKey;
import com.journaldev.jsf.pojo.daftarhunian.QueryDaftarhunianDlt;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
import net.bootsfaces.utils.FacesMessages;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ManagedBean
//@RequestScoped    //remark dulu..
@SessionScoped  //coba-cobi dulu
public class ReservationBean implements Serializable{
	private String no;
	private String penyelenggara;
	private int jmlPeserta;
	private Date tglMulai;
	private Date tglSelesai;
	private String sudahSelesai;
	private int kodeKegiatan;
	
        //sementara ini gak dipakai lagi
        private List<String> selectOneMenuKegiatans = new ArrayList<String>();
        private List<String> selectOneMenuPenyelenggaras = new ArrayList<String>();
        
        //ganti ke HashMap
        private Map<String, String> penyelenggaras = new HashMap<String, String>();
        private Map<String, String> kegiatansMap = new HashMap<String, String>();

        private String selectOneMenuPenyelenggara;
        private String selectOneMenuKegiatan;

        public String SERVICE_BASE_URI; 

        List<QueryDaftarhunianDlt> queryDaftarhunianDlt = new ArrayList<QueryDaftarhunianDlt>();
        
        //tambahan coba - cobi
//        @ManagedProperty("#{addDaftarHunianDtlBean}") 
//        private AddDaftarHunianDtlBean addDaftarHunianDtlBean; // +setter (no getter!)

        private boolean isDtlBtnDisabled;

	private QueryDaftarhunianDlt selectedQueryDaftarhunianDlt;
        
	public ReservationBean() {
		super();
		// TODO Auto-generated constructor stub
                FacesContext fc = FacesContext.getCurrentInstance();
                SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
                selectOneMenuPenyelenggaraService();
                selectOneMenuKegiatanService();
                
                //disable button
                setIsDtlBtnDisabled(true);
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
        
//        String url = SERVICE_BASE_URI+"user/daftarhunianHdrs";
        String url = SERVICE_BASE_URI+"user/daftarhunianHdrs2";
        
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
        
        //convert kodeKegiatan => selectOneMenuKegiatan;
        int intKode = Integer.valueOf(selectOneMenuKegiatan);
        objDfrtHdr.setKodeKegiatan(intKode);
        
    	HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> requestEntity = 
                new HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr>(objDfrtHdr, headers);
//    	URI uri = restTemplate.postForLocation(url, requestEntity);     //bisa juga pake ini...
        
    	/* 1.tipu pakai Get: tapi lakukan insert ke data base */
    	/* 2. get select max(id) if exist compare with current row data */
    	    	
        /* buka */
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> request = new HttpEntity<>(objDfrtHdr, headers);
        ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> response = 
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, 
                com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr.class);
        HttpStatus statusCode = response.getStatusCode();
        
        //Faces message 
        if(statusCode.is2xxSuccessful()){
            String status ="Success";
            FacesMessages.info("Info", "Input Reservai Sukses!");
	    FacesMessages.info("Info", "Lanjutkan dgn menambahkan Kamar yg dipilih!");
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
	//End Faces message        

        com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr daftarhunianHdr = response.getBody();
        
        if(daftarhunianHdr.getNo() != null){
           String sNo = daftarhunianHdr.getNo();
           this.setNo(no);
           
           //unable button
           this.setIsDtlBtnDisabled(false);
        }
        
//        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        /* end buka */
        
//    	}	catch (IOException | ParseException ex) {
    	
//    	}
//        }catch (URISyntaxException e){
        FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        url,
//                                        uri.toString()));
//                            response.getStatusCode().toString()));
                        statusCode.toString()));                                
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
        //set unabled button = true
        this.setIsDtlBtnDisabled(false);
    }

    public void updateReservationDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
	String url = SERVICE_BASE_URI+"user/daftarhunianHdrs/{no}";

	com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr objDfrtHdr = new com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr();
	jmlPeserta = this.getJmlPeserta();
	objDfrtHdr.setJmlPeserta(jmlPeserta); 
        objDfrtHdr.setKodeKegiatan(kodeKegiatan);
        objDfrtHdr.setNo(no);
        objDfrtHdr.setPenyelenggara(penyelenggara);
        objDfrtHdr.setSudahSelesai(sudahSelesai);
        objDfrtHdr.setTglMulai(tglMulai);
        objDfrtHdr.setTglSelesai(tglSelesai);

	//convert kodeKegiatan => selectOneMenuKegiatan;
        int intKode = Integer.valueOf(selectOneMenuKegiatan);
        objDfrtHdr.setKodeKegiatan(intKode);
        HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> requestEntity = 
                new HttpEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr>(objDfrtHdr, headers);

	//rest params
        Map<String, String> params = new HashMap<String, String>();
        params.put("no", no);

	ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> response = 
                restTemplate.exchange(url, HttpMethod.PUT, requestEntity, 
                com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr.class,params);

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

        com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr daftarhunianHdr = response.getBody();
        
    }

    public void getDaftarhunianHdrByIdDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
  //      String url = "http://207.148.66.201:8080/user/daftarhunianHdrs/{no}";
    	String url = SERVICE_BASE_URI+"user/daftarhunianHdrs/{no}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr.class, 16);
        com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr dftr = responseEntity.getBody();
        System.out.println("Jml Peserta:"+dftr.getJmlPeserta()+", Kegiatan:"+dftr.getKodeKegiatan()
                 +", No:"+dftr.getNo());
        
        FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						url,
						"Jml Peserta:"+dftr.getJmlPeserta()+", Kegiatan:"+dftr.getKodeKegiatan()
		                 +", No:"+dftr.getNo()));
        
    }

    public List<String> selectOneMenuKegiatanService(){
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://207.148.66.201:8080/user/kegiatans";
    	String url = SERVICE_BASE_URI+"user/kegiatans";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<com.journaldev.jsf.pojo.Kegiatan[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, com.journaldev.jsf.pojo.Kegiatan[].class);
        com.journaldev.jsf.pojo.Kegiatan[] kegiatans = responseEntity.getBody();

        for(com.journaldev.jsf.pojo.Kegiatan kegiatan : kegiatans) {
//            String s = kegiatan.getKode(); //hanya mindahin ke var saja it's OK.
            String s = kegiatan.getNama();
            selectOneMenuKegiatans.add(s);
            String kodeKeg = kegiatan.getKode();
            //harus dibalik karena dipakai di *.jsf atau *.xhtml
            kegiatansMap.put(s, kodeKeg);  
        }
        return selectOneMenuKegiatans;
    }
    
    /*Getters and Setters*/

    public List<String> getSelectOneMenuKegiatans() {
        return selectOneMenuKegiatans;
    }

    public void setSelectOneMenuKegiatans(List<String> selectOneMenuKegiatans) {
        this.selectOneMenuKegiatans = selectOneMenuKegiatans;
    }

    public String getSelectOneMenuKegiatan() {
        return selectOneMenuKegiatan;
    }

    public void setSelectOneMenuKegiatan(String selectOneMenuKegiatan) {
        this.selectOneMenuKegiatan = selectOneMenuKegiatan;
    }
    
    /*Getters and Setters*/
    public List<String> selectOneMenuPenyelenggaraService(){
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVICE_BASE_URI+"user/penyelenggaras";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<com.course.springbootstarter.penyelenggara.Penyelenggara[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, com.course.springbootstarter.penyelenggara.Penyelenggara[].class);
        com.course.springbootstarter.penyelenggara.Penyelenggara[] ps = responseEntity.getBody();

        for(com.course.springbootstarter.penyelenggara.Penyelenggara p : ps) {
            String s = p.getNama(); //hanya mindahin ke var saja it's OK.
            selectOneMenuPenyelenggaras.add(s);
            
            //fill HashMap
            String kode = p.getKode();
            String nama = p.getNama();
//            penyelenggaras.put(kode, nama);
            //coba dibalik
            penyelenggaras.put(nama, kode);
        }
        return selectOneMenuPenyelenggaras;
    }

    //Getters and Setters
    public List<String> getSelectOneMenuPenyelenggaras() {
        return selectOneMenuPenyelenggaras;
    }

    public void setSelectOneMenuPenyelenggaras(List<String> selectOneMenuPenyelenggaras) {
        this.selectOneMenuPenyelenggaras = selectOneMenuPenyelenggaras;
    }

    public String getSelectOneMenuPenyelenggara() {
        return selectOneMenuPenyelenggara;
    }

    public void setSelectOneMenuPenyelenggara(String selectOneMenuPenyelenggara) {
        this.selectOneMenuPenyelenggara = selectOneMenuPenyelenggara;
    }

    public String getSERVICE_BASE_URI() {
        return SERVICE_BASE_URI;
    }

    public void setSERVICE_BASE_URI(String SERVICE_BASE_URI) {
        this.SERVICE_BASE_URI = SERVICE_BASE_URI;
    }

    //Punyanya HashMap
    public Map<String, String> getPenyelenggaras() {
        return penyelenggaras;
    }

    public void setPenyelenggaras(Map<String, String> penyelenggaras) {
        this.penyelenggaras = penyelenggaras;
    }
    //End Punyanya HashMap
    
    public void penyelenggarasChanged(ValueChangeEvent e){
        //assign new value to localeCode
        penyelenggara = e.getNewValue().toString();
        //kirim kode bukan nama nya ke database
        
    }
    
    public void kegiatansChanged(ValueChangeEvent e){
        //assign new value to localeCode
        selectOneMenuKegiatan = e.getNewValue().toString();
        //kirim kode bukan nama nya ke database
        
    }
    
    //Gretters and Setters Map
    public Map<String, String> getKegiatansMap() {
        return kegiatansMap;
    }

    public void setKegiatansMap(Map<String, String> kegiatansMap) {
        this.kegiatansMap = kegiatansMap;
    }

    public List<QueryDaftarhunianDlt> getQueryDaftarhunianDlt() {
            return queryDaftarhunianDlt;
    }

    public void setQueryDaftarhunianDlt(List<QueryDaftarhunianDlt> queryDaftarhunianDlt) {
            this.queryDaftarhunianDlt = queryDaftarhunianDlt;
    }
    
    //End Gretters and Setters Map
    
    //Pencarian
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
	jmlPeserta = jml;
        this.setJmlPeserta(jmlPeserta);
        this.setKodeKegiatan(kodeKegiatan);
        this.setNo(no);
        this.setPenyelenggara(penyelenggara);
        this.setSudahSelesai(sudahSelesai);
        this.setTglMulai(tglMulai);
        this.setTglSelesai(tglSelesai);
        //Bean setter end
        queryDaftarhunianDlt.clear();
        
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
        //button unabled
        this.setIsDtlBtnDisabled(false);
        return dha;
    }
    
    //Jangan pakai model gini..
    //kembali dulu ke model inject Bean
//    AddDaftarHunianDtlBean2 addDftrHunianDtlBean = new AddDaftarHunianDtlBean2(); 

//    public AddDaftarHunianDtlBean2 getAddDftrHunianDtlBean() {
//        return addDftrHunianDtlBean;
//    }
//
//    public void setAddDftrHunianDtlBean(AddDaftarHunianDtlBean2 addDftrHunianDtlBean) {
//        this.addDftrHunianDtlBean = addDftrHunianDtlBean;
//    }

    //    public String addRoomReservationDtl(String no){
    public String addRoomReservationDtl(){
        //ini contoh nya
        //return "/Quizy.xhtml?faces-redirect=true";
        
        //Cara lain adalah dgn..
        //Buat kelas bean untuk Hunian Dtl
//        addDaftarHunianDtlBean.setNo(no);

        //cara2 
//        HttpSession session = getCurrentRequestFromFacesContext().getSession(false); //koq error ya?
        
        return "LPMPFormModalAddReservationDtl?faces-redirect=true";
//        return "LPMPFormModalAddReservationDtl2?faces-redirect=true";
    }

//    public AddDaftarHunianDtlBean getAddDaftarHunianDtlBean() {
//        return addDaftarHunianDtlBean;
//    }

//    public void setAddDaftarHunianDtlBean(AddDaftarHunianDtlBean addDaftarHunianDtlBean) {
//        this.addDaftarHunianDtlBean = addDaftarHunianDtlBean;
//    }
    
    public void resetFormField(){
        jmlPeserta = 0;
        no = null;
        kodeKegiatan = 0;
        penyelenggara = "";
        sudahSelesai = "N";
        tglMulai = null;
        tglSelesai = null;
        
        this.setJmlPeserta(jmlPeserta);
        this.setNo(no);
        this.setKodeKegiatan(kodeKegiatan);
        this.setPenyelenggara(penyelenggara);
        this.setSudahSelesai(sudahSelesai);
        this.setTglMulai(tglMulai);
        this.setTglSelesai(tglSelesai);
        
        queryDaftarhunianDlt.clear();
        this.setQueryDaftarhunianDlt(queryDaftarhunianDlt);
        //disable add detail button
        //disabled="#{bean.isDisabled}"
        this.setIsDtlBtnDisabled(true);
    }

    public String onEditDftrHunianDtl(QueryDaftarhunianDlt passedObj){
	//kirim ke next bean
        selectedQueryDaftarhunianDlt = passedObj;
	return "LPMPFormModalUpdateReservationDtl.jsf";
    }

    public boolean isIsDtlBtnDisabled() {
        return isDtlBtnDisabled;
    }

    public void setIsDtlBtnDisabled(boolean isDtlBtnDisabled) {
        this.isDtlBtnDisabled = isDtlBtnDisabled;
    }

    public QueryDaftarhunianDlt getSelectedQueryDaftarhunianDlt(){
	return selectedQueryDaftarhunianDlt;
    }
 
    public void setSelectedQueryDaftarhunianDlt(QueryDaftarhunianDlt qDhDtl){
	this.selectedQueryDaftarhunianDlt = qDhDtl;
    }   
}
