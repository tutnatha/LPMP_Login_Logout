/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course.springbootstarter.penyelenggara;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author myssd
 */
public class PenyelenggaraService {
    @Autowired
    private PenyelenggaraRepository penyelenggaraRepository;

    public List<Penyelenggara> getAllPenyelenggaras() {
        List<Penyelenggara> penyelenggaras = new ArrayList<>();
        penyelenggaraRepository.findAll().forEach(penyelenggaras::add);
        return penyelenggaras;
    }

    public Penyelenggara getPenyelenggara(String no) {
        //return kamars.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        return penyelenggaraRepository.findOne(no);
    }

    public void addPenyelenggara(Penyelenggara penyelenggara) {
        penyelenggaraRepository.save(penyelenggara);
    }

    void updatePenyelenggara(String no, Penyelenggara penyelenggara) {
        penyelenggaraRepository.save(penyelenggara);
    }

    void updatePenyelenggara(Penyelenggara penyelenggara) {
        penyelenggaraRepository.save(penyelenggara);
    }
    
    void deletePenyelenggara(String no) {
        penyelenggaraRepository.delete(no);
    }

}
