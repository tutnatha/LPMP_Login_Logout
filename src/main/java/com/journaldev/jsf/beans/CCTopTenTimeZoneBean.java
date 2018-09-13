/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

//import javax.inject.Named;
//import javax.enterprise.context.Dependent;
//import com.journaldev.jsf.pojo.daftarhunian.Kamar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author tutnatha
 */
//@Named(value = "cCTopTenTimeZoneBean")
//@Dependent
@ManagedBean
@ViewScoped   //remark dulu apakah pengaruh?
//@SessionScoped
public class CCTopTenTimeZoneBean implements Serializable{

    List<TopTenCountry> listTopTen = new ArrayList<TopTenCountry>();
    TopTenCountry topTenCountry = new TopTenCountry();
    /**
     * Creates a new instance of CCTopTenTimeZoneBean
     */
    public CCTopTenTimeZoneBean() {
        
        
    }
    
    @PostConstruct
    public void init(){
        showTopTenCountry();
    }

    public List<TopTenCountry> showTopTenCountry(){
        //10
	topTenCountry = new TopTenCountry();
        topTenCountry.setJmlTz(2);
        topTenCountry.setNama("Turki");
        topTenCountry.setNo(10);
        topTenCountry.setUrlFlag("");
        
        listTopTen.add(topTenCountry);
        
        //9
	topTenCountry = new TopTenCountry();
        topTenCountry.setJmlTz(2);
        topTenCountry.setNama("Kazakhstan");
        topTenCountry.setNo(9);
        topTenCountry.setUrlFlag("");
        
        listTopTen.add(topTenCountry);
        
        //8
	topTenCountry = new TopTenCountry();
        topTenCountry.setJmlTz(2);
        topTenCountry.setNama("Mongolia");
        topTenCountry.setNo(8);
        topTenCountry.setUrlFlag("");
        
        listTopTen.add(topTenCountry);
        
        //7
	topTenCountry = new TopTenCountry();
        topTenCountry.setJmlTz(3);
        topTenCountry.setNama("Indonesia");
        topTenCountry.setNo(7);
        topTenCountry.setUrlFlag("");
        
        listTopTen.add(topTenCountry);
        
        //6
	topTenCountry = new TopTenCountry();
        topTenCountry.setJmlTz(3);
        topTenCountry.setNama("Brazil");
        topTenCountry.setNo(6);
        topTenCountry.setUrlFlag("");
        
        listTopTen.add(topTenCountry);
        
        //5
	topTenCountry = new TopTenCountry();        
	topTenCountry.setJmlTz(3);
        topTenCountry.setNama("Australia");
        topTenCountry.setNo(5);
        topTenCountry.setUrlFlag("");
        
        listTopTen.add(topTenCountry);
        
        //3
	topTenCountry = new TopTenCountry();
        topTenCountry.setJmlTz(3);
        topTenCountry.setNama("Kanada");
        topTenCountry.setNo(3);
        topTenCountry.setUrlFlag("");
        
        listTopTen.add(topTenCountry);
        
        return listTopTen;
    }
    
    public void onClick(){
    }

    public void addCountry(){}

    public void onClickCountryName(TopTenCountry country){}

    public List<TopTenCountry> getListTopTen() {
    //start
    //10

    //end
        return listTopTen;
    }

    public void setListTopTen(List<TopTenCountry> listTopTen) {
        this.listTopTen = listTopTen;
    }
    
    public void setTopTenCountry(TopTenCountry t){
	this.topTenCountry = t;
    }

    public TopTenCountry getTopTenCountry(){
    	return topTenCountry;
    }
    
    
}
