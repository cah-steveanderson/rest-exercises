package com.cardinalhealth.audition.patient;

import com.cardinalhealth.audition.ResourceNotFoundException;
import com.cardinalhealth.audition.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PatientController {
    private final Logger logger = LoggerFactory.getLogger(PatientController.class);
    
    private Map<UUID, Patient> patients;

    public PatientController() {
        patients = new HashMap<>();
        
        // Some initial patients...
        addPatientToDataStore(Patient.named("Anna", "Bendig"));
        addPatientToDataStore(Patient.named("Bendig","Cummins"));
        addPatientToDataStore(Patient.named("Gavin","Rave"));
        addPatientToDataStore(Patient.named("Justin","Gasper"));
        addPatientToDataStore(Patient.named("Louis","Bendig"));
        addPatientToDataStore(Patient.named("Indira","Gilicinski"));
        addPatientToDataStore(Patient.named("Tim","Larger"));
        addPatientToDataStore(Patient.named("Austin","Bendig"));
        addPatientToDataStore(Patient.named("Justin","Bendig"));
    }

    private void addPatientToDataStore(Patient patient) {
        patients.put(patient.getId(), patient);
        logger.info("Added patient: " + patient);
    }

    @RequestMapping(value="/patient/{id}", method= RequestMethod.GET)
    public Patient getPatientById(@PathVariable UUID id) {
        Patient patient = patients.get(id);

        if (patient == null) {
            throw new ResourceNotFoundException("Patient with id=" + id + " does not exist.");
        }
        return patient;
    }

    @RequestMapping(value="/patient", method= RequestMethod.GET)
    public List<Patient> getPatients() {
        return new ArrayList<>(patients.values());
    }
    
    @RequestMapping(value="/patient/{name}", method= RequestMethod.TRACE)
    public List<Patient> lookForAPatient(@PathVariable String inputName) {
        ArrayList<Patient> result = new ArrayList<>();

        for(Map.Entry<UUID, Patient> entry : patients.entrySet()) {
            String firstName = entry.getValue().getFirstName();
            String lastName = entry.getValue().getLastName();

            if (firstName.contains(inputName) || lastName.contains(inputName)) {
                result.add(entry.getValue());
            }
        }
        result.sort(Comparator.naturalOrder());

        return new ArrayList<>();
    }

    @RequestMapping(value="new", method= RequestMethod.PUT)
    public void addPatient(@RequestBody Patient newPatient)
    {
        addPatientToDataStore(newPatient);
    }
}
