/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.hunian.asrama;

//import com.tutorialspoint.DataBean;
//import com.tutorialspoint.DataBeanList;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
@ManagedBean
@javax.faces.bean.RequestScoped
public class RptDaftarHunianAsramaBean implements Serializable{
    public String SERVICE_BASE_URI;
    private List<DaftarHunianAsrama> daftarhunianAsramas;

    public RptDaftarHunianAsramaBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");

//        this.personas = new ArrayList<>();
//        this.daftarhunianHdrs = new ArrayList<>();
        daftarhunianAsramas = getAllListDaftarHunianAsrama();
    }
   
    @PostConstruct
    public void init() {
        
    }

    public List<DaftarHunianAsrama> getDaftarhunianAsramas() {
        return daftarhunianAsramas;
    }

    public void setDaftarhunianAsramas(List<DaftarHunianAsrama> daftarhunianAsramas) {
        this.daftarhunianAsramas = daftarhunianAsramas;
    }
    
    public void verPDF() throws JRException, IOException {
         Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUsuario", "Jefferson Valdez");
        
        //Coba - cobi JSF
//        String subReportFileName = "/Users/myssd/github.com/jumarome/"+
//              "jsfJasperReports/src/main/webapp"
//         + "/reportDfrtHunianAsramaDtl.jrxml";
        
//        InputStream subReportFileName = this.getClass().getClassLoader().getResourceAsStream("/Users/myssd/github.com/jumarome/"+
//              "jsfJasperReports/src/main/webapp"
//         + "/reportDfrtHunianAsramaDtl.jrxml");
//        

//coba - cobi sing dadi putus asa..
//FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/filename.xml");
//            InputStream resourceStream = getClass().getResourceAsStream("beans/hunian/asrama/reportDfrtHunianAsramaDtl.jrxml");

//          InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("/WEB-INF/reportDfrtHunianAsramaDtl.jrxml");
//        

//        String subReportFileName = new String(FacesContext.getCurrentInstance().
//                getExternalContext().getRealPath("/")) + "/reportDfrtHunianAsramaDtl.jrxml";

        // java.io.FileNotFoundException: 
        // /WEB-INF/reportDfrtHunianAsramaDtl.jrxml (No such file or directory)
//        String subReportFileName = "/WEB-INF/reportDfrtHunianAsramaDtl.jrxml";

//        JasperDesign jasperDesign = JRXmlLoader.load(resourceStream);
//        JasperReport jasperSubReport = JasperCompileManager
//            .compileReport(jasperDesign);
//        
//coba-cobi lagi dari
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
//        File dtlFile = new File(FacesContext.getCurrentInstance().
//                getExternalContext().getRealPath("/reportDfrtHunianAsramaDtl.jasper"));

//        JasperReport jasperSubReport = (JasperReport)JRLoader.loadObject(dtlFile);
        JasperReport jasperSubReport = (JasperReport)JRLoader.loadObjectFromFile(realPath + "reportDfrtHunianAsramaDtl.jasper");

//        JasperReport jasperSubReport = (JasperReport)JRLoader.loadObjectFromFile("/Users/myssd/github.com/jumarome/"+
//              "jsfJasperReports/src/main/webapp"
//         + "/resources/reportDfrtHunianAsramaDtl.jrxml");
        
///Users/myssd/github.com/jumarome/jsfJasperReports/src/main/java
        
        parametros.put("subreportParameter", jasperSubReport);
        parametros.put("SUBREPORT_DIR", "/resources/");  //ini tdk dipakai di jasper file

        File jasper = new File(FacesContext.getCurrentInstance().
                getExternalContext().getRealPath("/reportDftrHunianAsrama.jasper"));
        
//        jasper.getPath();

//copy paste dari github chathurangat
        //JRDataSource ds = new JRBeanCollectionDataSource(items);
        
//ini copy paste juga        
//        parameterMap.put("JasperMainReportDataSource", datasource);
//        parameterMap.put("JasperCustomSubReportDataSource", datasource);

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), 
                parametros, new JRBeanCollectionDataSource(this.getDaftarhunianAsramas()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.
                getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }
    
    public void exportarPDF() throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUsuario", "Juan Romero");  //remark dulu
        
//        String subReportFileName = "file:///home/wisty/glassfish-4.1.1/glassfish/"+
//                "domains/domain1/applications/JSFJASPER-1.0-SNAPSHOT/"+
//                "reportDfrtHunianAsramaDtl.jrxml";

//  Caused by: java.io.FileNotFoundException:
//  /home/wisty/glassfish-4.1.1/glassfish/domains/domain1/applications
//  /JSFJASPER-1.0-SNAPSHOT/reportDfrtHunianAsramaDtl.jrxml (No such file or directory)
//        String subReportFileName = new String(FacesContext.getCurrentInstance().
//                getExternalContext().getRealPath("/")) + "/reportDfrtHunianAsramaDtl.jrxml";
//        
//        subReportFileName = new String(FacesContext.getCurrentInstance().
//                getExternalContext().getRealPath("/")) + "/reportDfrtHunianAsramaDtl.jrxml";
        
//        String subReportFileName = "/Users/myssd/github.com/jumarome/"+
//              "jsfJasperReports/src/main/webapp"
//         + "/reportDfrtHunianAsramaDtl.jrxml";
        
//        InputStream subReportFileName = this.getClass().getClassLoader().getResourceAsStream("/Users/myssd/github.com/jumarome/"+
//              "jsfJasperReports/src/main/webapp"
//         + "/reportDfrtHunianAsramaDtl.jrxml");

//        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("/WEB-INF/reportDfrtHunianAsramaDtl.jrxml");
//        ByteArrayInputStream resourceStream = (ByteArrayInputStream) getClass().getResourceAsStream("beans/hunian/asrama/reportDfrtHunianAsramaDtl.jrxml");

//        ServletContext context = this.getServletConfig().getServletContext();

//		File reportFile = new File(context.getRealPath("/reports/WebappReport.jasper"));
//		if (!reportFile.exists())
//			throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");

        
//        String subReportFileName = new String(FacesContext.getCurrentInstance().
//                getExternalContext().getResourceAsStream("/reportDfrtHunianAsramaDtl.jrxml");

        // java.io.FileNotFoundException: 
//         /WEB-INF/reportDfrtHunianAsramaDtl.jrxml (No such file or directory)
//      
//        String subReportFileName = "/WEB-INF/reportDfrtHunianAsramaDtl.jrxml";
    
//        InputStream resourceStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/jasper/" + fileName + ".jrxml");

//        JasperDesign jd = JRXmlLoader.load(subReportFileName);

//        JasperDesign jasperDesign = JRXmlLoader.load(resourceStream);
//        JasperReport jasperSubReport = JasperCompileManager
//            .compileReport(jasperDesign);
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                
//        File dtlFile = new File(FacesContext.getCurrentInstance().getExternalContext().
//                getRealPath("/reportDfrtHunianAsramaDtl.jasper"));
        
//                JasperReport jasperSubReport = (JasperReport)JRLoader.loadObject(dtlFile);
//        JasperReport jasperSubReport = (JasperReport)JRLoader.loadObjectFromFile("/Users/myssd/github.com/jumarome/"+
//              "jsfJasperReports/src/main/webapp"
//         + "/WEB-INF/reportDfrtHunianAsramaDtl.jrxml");
        
        JasperReport jasperSubReport = (JasperReport)JRLoader.loadObjectFromFile(realPath + "reportDfrtHunianAsramaDtl.jasper");

        parametros.put("subreportParameter", jasperSubReport);
        parametros.put("SUBREPORT_DIR", "/WEB-INF/");   //tidak dipakai di jasper file 
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().
                getRealPath("/reportDftrHunianAsrama.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), 
                parametros, 
                new JRBeanCollectionDataSource(this.getDaftarhunianAsramas()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().
                getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public List<DaftarHunianAsrama> getAllListDaftarHunianAsrama(){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
//	String url = "http://207.148.66.201:8080/user/getAllDaftarHunianAsramas";
        String url = SERVICE_BASE_URI+"user/getAllDaftarHunianAsramas";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        
        //instantiate Java Class        
        
	ResponseEntity<DaftarHunianAsrama[]> responseEntity = 
                restTemplate.exchange(url, 
                HttpMethod.GET, requestEntity, DaftarHunianAsrama[].class);
        DaftarHunianAsrama[] hdrList = responseEntity.getBody();     
       
//	return hdrs;
        List<DaftarHunianAsrama> list = Arrays.asList(hdrList);
        
        int x = list.get(0).getDaftarHunianAsramaDtls().size();
        
        System.out.println("int x :"+x);
        
        return list;
    }

    private HttpHeaders getHeaders(){
	String credential="mukesh:m123";
	String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.add("Authorization","Basic " + encodedCredential);
	return headers;
    }
    
    public String getSERVICE_BASE_URI() {
        return SERVICE_BASE_URI;
    }

    public void setSERVICE_BASE_URI(String SERVICE_BASE_URI) {
        this.SERVICE_BASE_URI = SERVICE_BASE_URI;
    }
    
//    public static void main(String[] args) {
    public void verPDF2(){
//    public void exportarPDF2(){
//      String masterReportFileName = "C://tools/jasperreports-5.0.1/test"
//         + "/jasper_report_template.jrxml";     

      String masterReportFileName = "/Users/myssd/github.com/jumarome/" + 
              "jsfJasperReports/src/main/webapp"
              + "/reportDftrHunianAsrama.jrxml";
      
//      String subReportFileName = "C://tools/jasperreports-5.0.1/test"
//         + "/AddressReport.jrxml";
      
      String subReportFileName = "/Users/myssd/github.com/jumarome/"+
              "jsfJasperReports/src/main/webapp"
         + "/reportDfrtHunianAsramaDtl.jrxml";
      
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
      JRBeanCollectionDataSource(getAllListDaftarHunianAsrama());

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

}
