package com.api.fintech.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class StudentDto implements Serializable {
    private Integer id;
    private String firtname;
    private String lastname;
    private String email;
}
