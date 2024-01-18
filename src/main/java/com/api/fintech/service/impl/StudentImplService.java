package com.api.fintech.service.impl;

import com.api.fintech.model.dao.StudentDao;
import com.api.fintech.model.dto.*;
import com.api.fintech.model.entity.Student;
import com.api.fintech.service.IStudentService;
import org.hibernate.collection.spi.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentImplService implements IStudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public EmbeddedDto listAll() {
        ArrayList<StudentsDto> listStudents = new ArrayList<>();
        List<Student> listStudent = (List<Student>) studentDao.findAll();
        listStudent.sort(Comparator.comparing(Student::getId));
        for (Student student :  listStudent) {
            StudentsDto studentsDto = mappin(student);
            listStudents.add(studentsDto);
        }
        return EmbeddedDto.builder().students(listStudents).build();

    }

    @Transactional
    @Override
    public StudentsDto save(StudentDto studentDto) {
        Student student = Student.builder()
                .id(studentDto.getId())
                .firtname(studentDto.getFirtname())
                .email(studentDto.getEmail())
                .lastname(studentDto.getLastname())
                .build();
        Student storeStudent = studentDao.save(student);
        return mappin(storeStudent);
    }

    @Transactional(readOnly = true)
    @Override
    public StudentsDto findByIdF(Integer id) {
        Student student = studentDao.findById(id).orElse(null);

        if (student == null) {
            return null;
        }

        return mappin(student);
    }

    @Transactional
    @Override
    public void delete(Student student) {
        studentDao.delete(student);
    }

    @Override
    public boolean existsById(Integer id) {
        return studentDao.existsById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Student findById(Integer id) { return studentDao.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    @Override
    public Student findByEmail(String email) { return studentDao.findByEmail(email); }

    public StudentsDto mappin(Student student) {
        return StudentsDto.builder()
                .firtname(student.getFirtname())
                .lastname(student.getLastname())
                .email(student.getEmail())
                ._links(LinkDto.builder()
                        .self(SelfDto.builder()
                                .href("http://localhost:8092/api/students/" + student.getId())
                                .build())
                        .student(StudentLinkDto.builder()
                                .href("http://localhost:8092/api/students/" + student.getId())
                                .build())
                        .build())
                .build();
    }
}
