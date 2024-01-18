package com.api.fintech.model.dao;

import com.api.fintech.model.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student, Integer> {
    Student findByEmail(String email);
}
