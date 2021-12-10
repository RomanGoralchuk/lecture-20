package by.itacademy.javaenterprise.goralchuk.controllers;

import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;
import by.itacademy.javaenterprise.goralchuk.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public void patients() {
        patientService.getAllPatients();
    }

    @PostMapping("/patients")
    public String add(@RequestBody Patient patient) {
        long count = patientService.addPatient(patient);
        return count + "";
    }
}
