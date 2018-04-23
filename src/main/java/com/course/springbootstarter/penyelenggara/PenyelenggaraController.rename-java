/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course.springbootstarter.penyelenggara;

/**
 *
 * @author myssd
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("user")
public class PenyelenggaraController {
    @Autowired
    private PenyelenggaraService penyelenggaraService;

    @RequestMapping("/penyelenggaras")	//ok
    public List<Penyelenggara> getAllPenyelenggaras() {
        return penyelenggaraService.getAllPenyelenggaras();
    }
    
    @RequestMapping("/penyelenggaras/{no}")	//ok
    public Penyelenggara getPenyelenggara(@PathVariable String no) {
        return penyelenggaraService.getPenyelenggara(no);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/penyelenggaras")
    public void addPenyelenggara(@RequestBody Penyelenggara penyelenggara) {
        penyelenggaraService.addPenyelenggara(penyelenggara);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updatePenyelenggara/{no}")
    public void updatePenyelenggara(@RequestBody Penyelenggara penyelenggara, @PathVariable String no) {
        penyelenggaraService.updatePenyelenggara(no, penyelenggara);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePenyelenggara/{no}")
    public void deletePenyelenggara(@PathVariable String no) {
        penyelenggaraService.deletePenyelenggara(no);
    }
}
