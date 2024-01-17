package org.sid.patientmanagementservice.web;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.sid.patientmanagementservice.entities.Patient;
import org.sid.patientmanagementservice.mapper.PatientMapper;
import org.sid.patientmanagementservice.repositories.PatientRepository;
import org.sid.patientmanagementservice.stub.PatientServiceGrpc;
import org.sid.patientmanagementservice.stub.PatientServiceOuterClass;

import java.util.List;
import java.util.stream.Collectors;

//@AllArgsConstructor
//@GrpcService
@AllArgsConstructor
public class PatientGrpcService extends PatientServiceGrpc.PatientServiceImplBase {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @Override
    public void getAllPatients(PatientServiceOuterClass.GetAllPatientsRequest request, StreamObserver<PatientServiceOuterClass.GetAllPatientsResponse> responseObserver){
        List<Patient> patientList = patientRepository.findAll();
        List<PatientServiceOuterClass.Patient> grpcPatients =
                patientList.stream()
                        .map(patientMapper::fromPatientEntityToPatientGrpc).collect(Collectors.toList());

        PatientServiceOuterClass.GetAllPatientsResponse patientsResponse =
                PatientServiceOuterClass.GetAllPatientsResponse.newBuilder()
                        .addAllPatients(grpcPatients)
                        .build();
        responseObserver.onNext(patientsResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getPatientById(PatientServiceOuterClass.GetPatientByIdRequest request, StreamObserver<PatientServiceOuterClass.GetPatientByIdResponse> responseObserver){
        Patient patientEntity = patientRepository.findById(request.getPatientId()).get();
        PatientServiceOuterClass.GetPatientByIdResponse response =
                PatientServiceOuterClass.GetPatientByIdResponse.newBuilder()
                        .setPatient(patientMapper.fromPatientEntityToPatientGrpc(patientEntity))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void savePatient(PatientServiceOuterClass.SavePatientRequest request, StreamObserver<PatientServiceOuterClass.SavePatientResponse> responseObserver){
        PatientServiceOuterClass.PatientRequest patientRequest = request.getPatient();
        Patient patient= patientMapper.fromGrpcPatientRequestToPatientEntity(patientRequest);
        Patient savedPatient = patientRepository.save(patient);
        PatientServiceOuterClass.SavePatientResponse response =
                PatientServiceOuterClass.SavePatientResponse.newBuilder()
                        .setPatient(patientMapper.fromPatientEntityToPatientGrpc(savedPatient))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
