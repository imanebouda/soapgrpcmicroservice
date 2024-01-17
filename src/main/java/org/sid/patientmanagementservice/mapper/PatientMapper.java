package org.sid.patientmanagementservice.mapper;

import org.modelmapper.ModelMapper;
import org.sid.patientmanagementservice.dtos.PatientDto;
import org.sid.patientmanagementservice.entities.Patient;
import org.sid.patientmanagementservice.stub.PatientServiceOuterClass;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    private ModelMapper modelMapper= new ModelMapper();
    public Patient fromPatientDtoToPatientEntity(PatientDto patientDto){
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patient;
    }

    public PatientServiceOuterClass.Patient fromPatientEntityToPatientGrpc(Patient patient) {
        PatientServiceOuterClass.Patient patient1 = modelMapper.map(patient, PatientServiceOuterClass.Patient.class);
        return patient1;
    }


    public Patient fromGrpcPatientRequestToPatientEntity(PatientServiceOuterClass.PatientRequest patientRequest) {
        Patient patient = modelMapper.map(patientRequest, Patient.class);
        return patient;
    }
}
