package org.sid.patientmanagementservice.web;

import lombok.AllArgsConstructor;
import org.sid.patientmanagementservice.dtos.PatientDto;
import org.sid.patientmanagementservice.entities.Patient;
import org.sid.patientmanagementservice.mapper.PatientMapper;
import org.sid.patientmanagementservice.repositories.PatientRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientGraphQLController {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @QueryMapping
    public List<Patient> allPatients(){
        return patientRepository.findAll();
    }

    @QueryMapping
    public Patient patientById(@Argument Long id){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new RuntimeException(String.format("Patient %s not found", id));
        return patient;
    }

    @MutationMapping //pour l'ajout ou la modification
    public Patient savePatient(@Argument PatientDto patientDto){
        Patient p = patientMapper.fromPatientDtoToPatientEntity(patientDto);
        return patientRepository.save(p);
    }
}
