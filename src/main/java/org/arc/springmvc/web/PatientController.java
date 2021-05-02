package org.arc.springmvc.web;

import org.arc.springmvc.dao.PatientRepository;
import org.arc.springmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String index(){
        return "index";
    }

    @PostMapping(value="/savePatient")
    //**************** BindingResult collection contient la liste des erreurs
    public String savePatient(@Validated Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formpatient";

        patientRepository.save(patient);
        return "formpatient";
    }

    @GetMapping(path="/formpatient")
    public String formpatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formpatient";
    }

    @GetMapping(path = "/patients")
    public String list(Model model,
                       @RequestParam(name="page", defaultValue = "0") int page,
                       @RequestParam(name="size", defaultValue = "5") int size,
                       @RequestParam(name="keyword",defaultValue = "")String nome)
                       {
        Page<Patient> lst = patientRepository.findByNameContains(nome,PageRequest.of(page, size));
        model.addAttribute("patients",lst.getContent());
        model.addAttribute("pages", new int[lst.getTotalPages()]);
        model.addAttribute("currentpage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", nome);

        return "patients";
    }


    @GetMapping("/deletePatient")
    public String delete(long id, int page, int size, String keyword) {
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;

    }




    /*private PatientRepository patientRepository;
    @GetMapping("/patients")
    public List<Patient> patients(){
        return patientRepository.findAll();

    }*/

}
