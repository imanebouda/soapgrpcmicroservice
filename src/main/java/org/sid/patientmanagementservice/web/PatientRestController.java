package org.sid.patientmanagementservice.web;

import lombok.AllArgsConstructor;
import org.sid.patientmanagementservice.entities.Patient;
import org.sid.patientmanagementservice.mapper.PatientMapper;
import org.sid.patientmanagementservice.repositories.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientRestController {
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }

    @GetMapping("/patients/id")
    public Patient patientById(@PathVariable Long id){
        return patientRepository.findById(id).get();
    }

    @PostMapping("/patients")
    public Patient savePatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }
}
