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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

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
        Long testId = 10L;

        String expectedTestName = "TestName";

        Patient patient = new Patient();
        patient.setPatientIdCardNumber(testId);

        Patient patientTwo = new Patient();
        patient.setPatientIdCardNumber(testId);
        patient.setName(expectedTestName);

        when(entityManager.getTransaction()).thenReturn(entityTransaction);

        patientDao.update(patientTwo);
        String actualTestName = patient.getName();

        assertNotNull(patientDao.update(patientTwo));

        logger.debug("Test result name from DB: {}", patient.getName());
        assertEquals(expectedTestName, actualTestName);
    }

    @Test
    public void whenDeletePeopleFromDatabase() {
/*        Long expectedId = 10L;
        Patient patient = new Patient();
        patient.setPatientIdCardNumber(expectedId);




        Long actualId = patientDao.delete(expectedId);

        assertEquals(actualId, expectedId);
    }*/
    }

    @Test
    public void findAll() {
        List<Patient> patientsList = List.of(
                new Patient(),
                new Patient(),
                new Patient());
        Query query = mock(Query.class);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(patientsList);

        logger.debug("TEST {}", patientDao.findAll());

        int expectedSize = patientsList.size();
        int actualSize = patientDao.findAll().size();

        assertEquals("Test find all persons", expectedSize, actualSize);
    }

    @Test
    public void findAllPatientByLifeStatus() {
    }

    @Test
    public void count() {
    }
}