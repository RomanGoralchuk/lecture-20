package by.itacademy.javaenterprise.goralchuk.services;

import by.itacademy.javaenterprise.goralchuk.dao.PatientDao;
import by.itacademy.javaenterprise.goralchuk.dao.impl.PatientDaoImpl;
import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientDao patientDao;
    @Value("${application.name}")
    private String applicationName;

    public List<Patient> getAllPatients() {
       List<Patient> patientList = null;
        try {
            patientList = patientDao.findAll();
        } catch (Exception e) {
            logger.warn("Cant show patients", e);
        }
        return patientList;
    }

    public String getApplicationName() {
        return applicationName;
    }
}
