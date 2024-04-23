package com.example.spring_mvc_thylemeaf_hospital.web;

import com.example.spring_mvc_thylemeaf_hospital.entities.Patient;
import com.example.spring_mvc_thylemeaf_hospital.repositories.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/user/index")
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
    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+ "&keyword="+keyword;
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

 @PostMapping(path="/admin/save")
 @PreAuthorize("hasRole('ROLE_ADMIN')")

 public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
         patientRepository.save(patient);
         return "redirect:/admin/index?page="+page+"&keyword="+keyword;

 }
     @GetMapping("/admin/editPatient")
     @PreAuthorize("hasRole('ROLE_ADMIN')")
     public String editPatient(Model model,Long id,String keyword , int page){
         Patient  patient=patientRepository.findById(id).orElse(null);
         if(patient==null) throw new RuntimeException("Patient introuvable");
         model.addAttribute("patient",patient);
         model.addAttribute("page",page);
         model.addAttribute("keyword",keyword);
         return "editPatient";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }




}
//@RequestParam(name="page") récupèrer un paramètre qui s'appelle page est affecter au param page au dessous
/* HttpServletRequest request*/
        /*int page=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("size"));*/

      /* List<Patient> patientList=patientRepository.findAll();
       model.addAttribute("listPatient",patientList);*/

//Page<Patient> pagePatients=patientRepository.findAll(PageRequest.of(page,size));