package com.task.ibm.payroll.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor (staticName = "of")
@NoArgsConstructor
public class EmpRequest {
 
    @NotNull(message = "employee name can't be null item")
    private String name;

    @NotNull(message = "role can't be null item")
    private String role;
}
