package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.dao.ProphylacticDao;
import by.itacademy.javaenterprise.goralchuk.entity.client.Prophylactic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class ProphylacticDaoImpl implements ProphylacticDao {
    private static final Logger logger = LoggerFactory.getLogger(ProphylacticDaoImpl.class);

    private EntityManager entityManager;

    public ProphylacticDaoImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Prophylactic find(Long id) {
        Prophylactic prophylactic = entityManager.find(Prophylactic.class, id);
        if (prophylactic == null) {
            logger.debug("Object not found");
            return null;
        } else {
            logger.debug("Operation completed");
            return prophylactic;
        }
    }

    @Override
    public Prophylactic save(Prophylactic prophylactic) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(prophylactic);
            entityManager.getTransaction().commit();
            logger.debug("The transaction was successful - {}", prophylactic);
            return prophylactic;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Prophylactic update(Prophylactic patient) {
        return null;
    }

    @Override
    public long delete(Long id) {
        return 0;
    }

    @Override
    public List<Prophylactic> findAll() {
        return null;
    }
}
