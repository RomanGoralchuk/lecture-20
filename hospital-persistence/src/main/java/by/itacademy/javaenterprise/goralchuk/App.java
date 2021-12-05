package by.itacademy.javaenterprise.goralchuk;

import by.itacademy.javaenterprise.goralchuk.dao.impl.PatientDaoImpl;
import by.itacademy.javaenterprise.goralchuk.dao.impl.ProphylacticDaoImpl;
import by.itacademy.javaenterprise.goralchuk.dao.impl.ProphylacticLeaveDaoImpl;
import by.itacademy.javaenterprise.goralchuk.entity.client.*;
import by.itacademy.javaenterprise.goralchuk.entity.documents.ProphylacticLeave;
import by.itacademy.javaenterprise.goralchuk.util.FlywayUtil;
import by.itacademy.javaenterprise.goralchuk.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            FlywayUtil.cleanMigration();
            FlywayUtil.updateMigration();

            PatientDaoImpl patientDao = new PatientDaoImpl(entityManager);
            ProphylacticDaoImpl prophylacticDao = new ProphylacticDaoImpl(entityManager);

            patientDao.save(new Patient(new Address()));
            patientDao.save(new Patient(new Address()));
            patientDao.save(new Patient(new Address()));

            Patient patientTest = new Patient();
            patientTest.setName("TestName");
            patientTest.setSurname("TestSurname");
            patientTest.setBirthday(java.sql.Date.valueOf("2000-12-12"));
            patientTest.setGender(Gender.MALE);
            patientTest.setAddress(
                    new Address(220092L, "Gottom","Gotoomstreet", 106, 10)
            );
            patientTest.setLifeStatus(LifeStatus.ALIVE);

            patientDao.save(patientTest);

            logger.info("{}", patientDao.find(10L));

            prophylacticDao.save(new Prophylactic("Hello"));
            prophylacticDao.save(new Prophylactic("Sitius Altius Fortius"));

            logger.info("{}", prophylacticDao.find(1L));
            logger.info("{}", prophylacticDao.find(2L));

            ProphylacticLeaveDaoImpl prophylacticLeaveDao = new ProphylacticLeaveDaoImpl(entityManager);

            prophylacticLeaveDao.save(new ProphylacticLeave());
            prophylacticLeaveDao.save(new ProphylacticLeave());
            prophylacticLeaveDao.save(new ProphylacticLeave());

        } catch (StackOverflowError  e) {
            logger.error(e.getMessage(), e);
        }
        HibernateUtil.close();
    }
}
