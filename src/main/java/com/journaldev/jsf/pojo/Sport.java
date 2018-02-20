package com.journaldev.jsf.pojo;

import java.io.Serializable;
import java.util.List;

public class Sport implements Serializable {  
	  
    private static final long serialVersionUID = 20111120L;  
  
    private String name;  
    private List<Country> countriesWithLeague;  
  
    public Sport(String name, List<Country> countriesWithLeague) {  
        this.name = name;  
        this.countriesWithLeague = countriesWithLeague;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public List<Country> getCountriesWithLeague() {  
        return countriesWithLeague;  
    }  
}  
