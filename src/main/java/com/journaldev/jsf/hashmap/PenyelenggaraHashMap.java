/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.hashmap;

import com.course.springbootstarter.penyelenggara.Penyelenggara;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author myssd
 */
public class PenyelenggaraHashMap {
    Map m = new HashMap();
    List<Penyelenggara> pList = new ArrayList<Penyelenggara>();
    
    public PenyelenggaraHashMap(ArrayList<Penyelenggara> p){ //panggil yg ini
        fillMap(p);
    }
    
    public PenyelenggaraHashMap(){
//        fillMap(pList); //pList init null
    }
    
    public Map fillMap(List<Penyelenggara> p){
        
        for(int i=0; i<p.size(); i++){
            // Add some vehicles.
            String kode = p.get(i).getKode();
            String nama = p.get(i).getNama();
            m.put("BMW", 5);            
        }

        return m;
    }

    //Function Find Code ...
    
    //Function FInd Nama ...
    
    //Getters & Setters
    public Map getM() {
        return m;
    }

    public void setM(Map m) {
        this.m = m;
    }

    public List<Penyelenggara> getpList() {
        return pList;
    }

    public void setpList(List<Penyelenggara> pList) {
        this.pList = pList;
    }
       
}
