import java.io.Serializable;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.journaldev.jsf.pojo.daftarhunian.DaftarhunianHdr;

/**
 * Data Model Bean for Items
 * @author Thecoder4.eu
 */
@ManagedBean
@RequestScoped
public class DaftarhunianHdrBean implements Serializable {

    private static DaftarhunianHdr[] hdrList;
    private DataModel<DaftarhunianHdr> hdrs;

//([{"no":"1","penyelenggara":"Umum","jmlPeserta":100,"tglMulai":null,"tglSelesai":null,"sudahSelesai":"N","kodeKegiatan":"1"},
    public DaftarhunianHdrBean(){
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateInString = "7-Jun-2013";
	Date date = null;
        try {

            date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

	hdrList = new DaftarhunianHdr[]{
            new DaftarhunianHdr("1","Umum",100,date,date,"N",1),
	    new DaftarhunianHdr("2","Dinas Pend",123,date, date,"N",2),
	    new DaftarhunianHdr("3","123",123,date,date,"N",123),
	    new DaftarhunianHdr("4","123",123,date,date,"N",123)
	}; 
//{"no":"2","penyelenggara":"Dinas ////////Pend","jmlPeserta":100,"tglMulai":null,"tglSelesai":null,"sudahSelesai":"N","kodeKegiatan":"2"},//{"no":"3","penyelenggara":"123","jmlPeserta":123,"tglMulai":33064329600000,"tglSelesai":1562544000000,"sudahSelesai":"N","kodeKegiatan":"123"},{"no":"4","penyelenggara":"123","jmlPeserta":123,"tglMulai":33064329600000,"tglSelesai":1562544000000,"sudahSelesai":"N","kodeKegiatan":"123"},{"no":"5","penyelenggara":"801","jmlPeserta":12,"tglMulai":1510272000000,"tglSelesai":1544400000000,"sudahSelesai":"Y","kodeKegiatan":"123"}]),
                
//    hdrs = new ArrayDataModel<DaftarhunianHdr>(hdrList);
	hdrs = getAllDaftarHunianHdr();
    }

    public DataModel<DaftarhunianHdr> getHdrs() {
        return hdrs;
    }

    public void setItems(DataModel<DaftarhunianHdr> hdrs) {
        this.hdrs = hdrs;
    }

    private HttpHeaders getHeaders(){
	String credential="mukesh:m123";
	String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.add("Authorization","Basic " + encodedCredential);
	return headers;
    }

    public DataModel<DaftarhunianHdr> getAllDaftarHunianHdr(){
	HttpHeaders headers = getHeaders();
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://207.148.66.201:8080/user/daftarhunianHdrs";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
	ResponseEntity<DaftarhunianHdr[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, DaftarhunianHdr[].class);
        DaftarhunianHdr[] hdrList = responseEntity.getBody();
	hdrs = new ArrayDataModel<DaftarhunianHdr>(hdrList);
	return hdrs;   	
    }
}

