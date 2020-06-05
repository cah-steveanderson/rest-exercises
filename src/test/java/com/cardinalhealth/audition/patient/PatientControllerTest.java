package com.cardinalhealth.audition.patient;

import com.cardinalhealth.audition.ResourceNotFoundException;
import com.cardinalhealth.audition.entities.Patient;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class PatientControllerTest {

    @Test
    public void testGetThrowsResourceNotFoundException() {
        PatientController patientController = new PatientController();
        try {
            patientController.getPatientById(UUID.randomUUID());
            fail("getPatientById() should have failed, but didn't");
        }
        catch (ResourceNotFoundException e) {}
    }

    @Test
    public void testGetById() {
        PatientController patientController = new PatientController();
        List<Patient> patients = patientController.getPatients();

        assertThat(patientController.getPatientById(patients.get(0).getId())).isEqualTo(patients.get(0));
    }
}