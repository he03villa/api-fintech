package com.api.fintech.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class StudentsDto implements Serializable {
    private String firtname;
    private String lastname;
    private String email;
    private LinkDto _links;
}
