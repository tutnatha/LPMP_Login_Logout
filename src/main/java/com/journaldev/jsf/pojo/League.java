package com.journaldev.jsf.pojo;

import java.io.Serializable;

public class League implements Serializable {  

  private static final long serialVersionUID = 20111120L;  

  private String name;  
  private int numberOfTeam;  

  public League(String name, int numberOfTeam) {  
      this.name = name;  
      this.numberOfTeam = numberOfTeam;  
  }  

  public String getName() {  
      return name;  
  }  

  public int getNumberOfTeam() {  
      return numberOfTeam;  
  }  
}  
