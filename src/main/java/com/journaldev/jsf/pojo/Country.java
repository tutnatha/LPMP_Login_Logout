package com.journaldev.jsf.pojo;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {  
	  
    private static final long serialVersionUID = 20111120L;  
  
    private String name;  
    private String code;  
    private String sport;  
    private List<League> leagues;  
  
    public Country(String name, String code, String sport, List<League> leagues) {  
        this.name = name;  
        this.code = code;  
        this.sport = sport;  
        this.leagues = leagues;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public String getCode() {  
        return code;  
    }  
  
    public String getSport() {  
        return sport;  
    }  
  
    public List<League> getLeagues() {  
        return leagues;  
    }  
}  
