/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.hunian.asrama;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author myssd
 */
public class Room implements Serializable{
    private String no;
    private int lantai;
    private int jmlTt;
    private String urlPicture;
    
    private List<DaftarHunianAsramaDtl> daftarHunianAsramaDtls = new ArrayList<DaftarHunianAsramaDtl>();

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getLantai() {
        return lantai;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public int getJmlTt() {
        return jmlTt;
    }

    public void setJmlTt(int jmlTt) {
        this.jmlTt = jmlTt;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

//	public Set<com.course.springbootstarter.daftarhunian.asrama.DaftarHunianAsramaDtl> getDaftarHunianAsramaDtls() {
    public List<DaftarHunianAsramaDtl> getDaftarHunianAsramaDtls() {
        return daftarHunianAsramaDtls;
    }

    public void setDaftarHunianAsramaDtls(
//	Set<com.course.springbootstarter.daftarhunian.asrama.DaftarHunianAsramaDtl> daftarHunianAsramaDtls) {
        List<DaftarHunianAsramaDtl> daftarHunianAsramaDtls) {
        this.daftarHunianAsramaDtls = daftarHunianAsramaDtls;
    }

    public Room(){

    }
}
