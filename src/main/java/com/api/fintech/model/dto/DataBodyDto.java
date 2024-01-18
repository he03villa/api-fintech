package com.api.fintech.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class DataBodyDto implements Serializable {
    @NonNull
    private String firtname;
    @NonNull
    private String lastname;
    @NonNull
    @Email
    private String email;
}
