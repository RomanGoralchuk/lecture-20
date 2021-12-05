package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.dao.PatientDao;
import by.itacademy.javaenterprise.goralchuk.entity.client.LifeStatus;
import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    private static final Logger logger = LoggerFactory.getLogger(PatientDaoImpl.class);

    private EntityManager entityManager;

    public PatientDaoImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Patient find(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        if (patient == null) {
            logger.debug("Object not found");
            return null;
        } else {
            logger.debug("Operation completed");
            return patient;
        }
    }

    @Override
    public Patient save(Patient patient) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(patient);
            entityManager.getTransaction().commit();
            logger.debug("The transaction was successful - {}", patient);
            return patient;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Patient update(Patient patient) {
        return null;
    }

    @Override
    public long delete(Long id) {
        return 0;
    }

    @Override
    public List<Patient> findAll() {
        return null;
    }

    @Override
    public List<Patient> findAllPatientByLifeStatus(LifeStatus lifeStatus) {
        return null;
    }
}
