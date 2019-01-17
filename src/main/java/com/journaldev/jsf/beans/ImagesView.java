/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import java.util.ArrayList;
import java.util.List;
//import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author tutnatha
 */
//@Named(value = "imagesView")
//@Dependent
@ManagedBean
@ViewScoped
public class ImagesView {

    /**
     * Creates a new instance of ImagesView
     */    
    
    private List<String> images;
    private String id;
    
    public ImagesView() {
    }
    
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 4; i++) {
            images.add("Valladolid-parque-" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
    
    public String cmdButton(){
        //code here
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    //https://stackoverflow.com/questions/16948731/how-to-get-the-selected-image-from-pgalleria
    /**/
    public void selectImage(String selectedImage) {        
        String fileName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("imageFileName");
        // find image by filename...
    }
    
    public String openPage(){
        return "Image.jsf";
    }
    
/**/
}
