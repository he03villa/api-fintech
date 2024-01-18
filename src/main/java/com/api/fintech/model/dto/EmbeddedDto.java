package com.api.fintech.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
public class EmbeddedDto implements Serializable {
    private List<StudentsDto> students;
}
