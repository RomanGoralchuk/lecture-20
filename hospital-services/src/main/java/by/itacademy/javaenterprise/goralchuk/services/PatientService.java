package by.itacademy.javaenterprise.goralchuk.services;

import by.itacademy.javaenterprise.goralchuk.dao.PatientDao;
import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientDao patientDao;

    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    public long addPatient(Patient patient) {
        patientDao.save(patient);
        return patientDao.count();
    }
}
