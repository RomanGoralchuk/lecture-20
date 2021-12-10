package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientDaoImplTest {
    private static final Logger logger = LoggerFactory.getLogger(PatientDaoImplTest.class);

    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityTransaction entityTransaction;
    @InjectMocks
    private PatientDaoImpl patientDao;

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            logger.error("Test failed: {}", description, e);
        }

        @Override
        protected void succeeded(Description description) {
            logger.info("Test successes: {}", description);
        }
    };

    @After
    public void closeEntityManager() {
        entityManager.close();
    }


    @Test
    public void whenFindPatientById() {
        Long expectedId = 10L;
        Patient patient = new Patient();
        patient.setPatientIdCardNumber(expectedId);

        when(entityManager.find(Patient.class, expectedId)).thenReturn(patient);

        logger.info("FirstObject  {}", patient);
        logger.info("SecondObject  {}", patientDao.find(expectedId));

        assertEquals(patient, patientDao.find(expectedId));
    }

    @Test
    public void whenSavePatientToDatabase() {
        Long expectedId = 10L;
        Patient patient = new Patient();
        patient.setPatientIdCardNumber(expectedId);

        when(entityManager.getTransaction()).thenReturn(entityTransaction);

        assertNotNull(patientDao.save(patient));
        assertEquals(expectedId, patientDao.save(patient).getPatientIdCardNumber());
    }

    @Test
    public void whenUpdatePatientToDatabase() {
/*        Long expectedId = 10L;
        String expectedText = "TestUpdate";

        Patient patient = new Patient();
        patient.setPatientIdCardNumber(expectedId);

        Patient patientUpdateInfo = new Patient();
        patientUpdateInfo.setPatientIdCardNumber(expectedId);
        patientUpdateInfo.setName(expectedText);


        logger.info("FIRST {}",patient.getName());
        logger.info("SECOND {}",patientUpdateInfo.getName());
        logger.info("RESULT {}",patientDao.update(patientUpdateInfo));

        assertEquals(expectedText, patientDao.update(patientUpdateInfo).getName());*/
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllPatientByLifeStatus() {
    }

    @Test
    public void count() {
    }
}