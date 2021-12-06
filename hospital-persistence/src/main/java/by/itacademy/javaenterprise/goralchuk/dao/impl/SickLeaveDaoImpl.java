package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.dao.SickLeaveDao;
import by.itacademy.javaenterprise.goralchuk.entity.documents.SickLeave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;
import java.util.List;

public class SickLeaveDaoImpl implements SickLeaveDao {
    private static final Logger logger = LoggerFactory.getLogger(SickLeaveDaoImpl.class);

    private EntityManager entityManager;

    public SickLeaveDaoImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public SickLeave find(Long id) {
        SickLeave sickLeave = entityManager.find(SickLeave.class, id);
        if (sickLeave == null) {
            logger.debug("Object not found");
            return null;
        } else {
            logger.debug("Operation completed");
            return sickLeave;
        }
    }

    @Override
    public SickLeave save(SickLeave sickLeave) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sickLeave);
            entityManager.getTransaction().commit();
            logger.debug("The transaction was successful - {}", sickLeave);
            return sickLeave;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public SickLeave update(SickLeave sickLeave) {
        return null;
    }

    @Override
    public long delete(Long id) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaDelete<SickLeave> criteriaDelete = cb.createCriteriaDelete(SickLeave.class);
            Root<SickLeave> root = criteriaDelete.from(SickLeave.class);

            criteriaDelete.where(cb.equal(root.get("numberSickLeave"), id));

            entityManager.getTransaction().begin();
            entityManager.createQuery(criteriaDelete).executeUpdate();
            entityManager.getTransaction().commit();
            return id;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public List<SickLeave> findAll() {
        return null;
    }
}
