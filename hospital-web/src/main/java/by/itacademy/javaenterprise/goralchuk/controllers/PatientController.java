package by.itacademy.javaenterprise.goralchuk.controllers;

import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;
import by.itacademy.javaenterprise.goralchuk.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public ModelAndView modelAndView() {
        List<Patient> patientsList = patientService.getAllPatients();
        ModelAndView view = new ModelAndView();
        view.setViewName("patients");
        view.addObject("patients", patientsList);
        view.addObject("appName", patientService.getApplicationName());
        return view;
    }
}
