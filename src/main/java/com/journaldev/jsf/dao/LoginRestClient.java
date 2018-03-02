package com.journaldev.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.journaldev.jsf.pojo.Users;

public class LoginRestClient {

    List<Users> userList = new ArrayList<Users>();
    Users usr = new Users();
    String username;

    public LoginRestClient(String param) {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://207.148.66.201:8080/user/users/{username}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Users> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Users.class, param);
        Users dftr = responseEntity.getBody();

        usr = dftr;
//        setUserList(userList);
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
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

    public Users getUsr() {
        return usr;
    }

    public void setUsr(Users usr) {
        this.usr = usr;
    }

}
