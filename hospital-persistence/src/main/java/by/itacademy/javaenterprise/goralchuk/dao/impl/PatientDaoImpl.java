package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.dao.PatientDao;
import by.itacademy.javaenterprise.goralchuk.entity.client.LifeStatus;
import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PatientDaoImpl implements PatientDao {
    private static final Logger logger = LoggerFactory.getLogger(PatientDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public PatientDaoImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Transactional(readOnly = true)
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

    @Transactional
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

    @Transactional
    @Override
    public Patient update(Patient patient) {
        return null;
    }

    @Transactional
    @Override
    public long delete(Long id) {
        return 0;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Patient> findAll() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Patient> findAllPatientByLifeStatus(LifeStatus lifeStatus) {
        return null;
    }

    public long count() {
        Long count = entityManager
                .createQuery("select count(m) from Patient m", Long.class)
                .getSingleResult();
        return count;
    }
}
