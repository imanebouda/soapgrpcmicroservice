package org.sid.patientmanagementservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private String name;
    private String email;
    private String description_diagnostic;


}
