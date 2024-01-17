package org.sid.patientmanagementservice.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.sid.patientmanagementservice.dtos.PatientDto;
import org.sid.patientmanagementservice.entities.Patient;
import org.sid.patientmanagementservice.mapper.PatientMapper;
import org.sid.patientmanagementservice.repositories.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService
public class PatientSoapService {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    public List<Patient> allPatients(){
        return patientRepository.findAll();
    }

    @WebMethod
    public Patient patientById(@WebParam(name = "id") Long id){
        return patientRepository.findById(id).get();
    }

    @WebMethod
    public Patient savePatient(@WebParam(name = "patient") PatientDto patientDto){
        Patient patient = patientMapper.fromPatientDtoToPatientEntity(patientDto);
        return patientRepository.save(patient);
    }
}
