package com.example.spring_mvc_thylemeaf_hospital.web;

import com.example.spring_mvc_thylemeaf_hospital.entities.Patient;
import com.example.spring_mvc_thylemeaf_hospital.repositories.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue ="0" ) int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw
                        ){

        Page<Patient> pagePatients=patientRepository.findByNomContains(kw,PageRequest.of(page,size));

        model.addAttribute("listPatients",pagePatients.getContent());;
        //return le nombre total des pages
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+ "&keyword="+keyword;
    }


}
//@RequestParam(name="page") récupèrer un paramètre qui s'appelle page est affecter au param page au dessous
/* HttpServletRequest request*/
        /*int page=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("size"));*/

      /* List<Patient> patientList=patientRepository.findAll();
       model.addAttribute("listPatient",patientList);*/

//Page<Patient> pagePatients=patientRepository.findAll(PageRequest.of(page,size));