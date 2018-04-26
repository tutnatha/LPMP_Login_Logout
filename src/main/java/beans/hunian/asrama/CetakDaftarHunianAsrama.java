/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.hunian.asrama;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
public class CetakDaftarHunianAsrama {

    public String SERVICE_BASE_URI;
    private List<DaftarHunianAsrama> daftarhunianAsramas;

    public CetakDaftarHunianAsrama() {
        FacesContext fc = FacesContext.getCurrentInstance();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");

//        this.personas = new ArrayList<>();
//        this.daftarhunianHdrs = new ArrayList<>();
        daftarhunianAsramas = getAllListDaftarHunianAsrama();        
    }
    
    
    private HttpHeaders getHeaders(){
	String credential="mukesh:m123";
	String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.add("Authorization","Basic " + encodedCredential);
	return headers;
    }

    
    public List<DaftarHunianAsrama> getAllListDaftarHunianAsrama(){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
//	String url = "http://207.148.66.201:8080/user/getAllDaftarHunianAsramas";
        String url = SERVICE_BASE_URI+"user/getAllDaftarHunianAsramas";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
	ResponseEntity<DaftarHunianAsrama[]> responseEntity = 
                restTemplate.exchange(url, 
                HttpMethod.GET, requestEntity, DaftarHunianAsrama[].class);
        DaftarHunianAsrama[] hdrList = responseEntity.getBody();

//	return hdrs;
        List<DaftarHunianAsrama> list = Arrays.asList(hdrList);
        
        return list;
    }


    public static void main(String[] args) {
//    public void verPDF2(){
//    public void exportarPDF2(){
//      String masterReportFileName = "C://tools/jasperreports-5.0.1/test"
//         + "/jasper_report_template.jrxml";     

      CetakDaftarHunianAsrama x = new CetakDaftarHunianAsrama();

      String masterReportFileName = "/Users/myssd/github.com/jumarome/" + 
              "jsfJasperReports/src/main/webapp"
              + "/reportDftrHunianAsrama.jrxml";
      
//      String subReportFileName = "C://tools/jasperreports-5.0.1/test"
//         + "/AddressReport.jrxml";
      
      String subReportFileName = "/Users/myssd/github.com/jumarome/"+
              "jsfJasperReports/src/main/webapp"
         + "/reportDftrHunianAsramaDtl.jrxml";
      
//      String destFileName = "C://tools/jasperreports-5.0.1/test"
//         + "/jasper_report_template.JRprint";
			
      String destFileName = "/Users/myssd/github.com/jumarome/"+
              "jsfJasperReports/src/main/webapp"
         + "/reportDftrHunianAsrama.JRprint";

      //Master
//      DataBeanList dataBeanList = new DataBeanList();
      
      //Dtl
//      ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
            
      JRBeanCollectionDataSource beanColDataSource = new 
//         JRBeanCollectionDataSource(dataList);
      JRBeanCollectionDataSource(x.getAllListDaftarHunianAsrama());

//      JRDataSource jrd = new JRDataSource(x.getAllListDaftarHunianAsrama());
      
      try {
         /* Compile the master and sub report */
         JasperReport jasperMasterReport = JasperCompileManager
            .compileReport(masterReportFileName);
         JasperReport jasperSubReport = JasperCompileManager
            .compileReport(subReportFileName);

         Map<String, Object> parameters = new HashMap<String, Object>();
         //Sub Report nya ditaruh di parameter
         parameters.put("subreportParameter", jasperSubReport);
         JasperFillManager.fillReportToFile(jasperMasterReport, 
            destFileName, parameters, beanColDataSource);
         
//         pdf();
            long start = System.currentTimeMillis();
//JasperExportManager.exportReportToPdfFile("build/reports/MasterReport.jrprint");
            JasperExportManager.exportReportToPdfFile(destFileName);
            System.err.println("PDF creation time : " 
                    + (System.currentTimeMillis() - start));
            
      } catch (JRException e) {

         e.printStackTrace();
      }
      System.out.println("Done filling!!! ...");
   }  

    public String getSERVICE_BASE_URI() {
        return SERVICE_BASE_URI;
    }

    public void setSERVICE_BASE_URI(String SERVICE_BASE_URI) {
        this.SERVICE_BASE_URI = SERVICE_BASE_URI;
    }

    public List<DaftarHunianAsrama> getDaftarhunianAsramas() {
        return daftarhunianAsramas;
    }

    public void setDaftarhunianAsramas(List<DaftarHunianAsrama> daftarhunianAsramas) {
        this.daftarhunianAsramas = daftarhunianAsramas;
    }
    
    
}
