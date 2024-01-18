package com.api.fintech.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class SelfDto implements Serializable {
    private String href;
}
