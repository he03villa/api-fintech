package com.api.fintech.service;

import com.api.fintech.model.dto.EmbeddedDto;
import com.api.fintech.model.dto.StudentDto;
import com.api.fintech.model.dto.StudentsDto;
import com.api.fintech.model.entity.Student;

import java.util.List;

public interface IStudentService {
    EmbeddedDto listAll();
    StudentsDto save(StudentDto student);

    StudentsDto findByIdF(Integer id);

    void delete(Student student);

    boolean existsById(Integer id);

    Student findById(Integer id);
    Student findByEmail(String email);
}
