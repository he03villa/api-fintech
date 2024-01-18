package com.api.fintech.controller;

import com.api.fintech.model.dto.DataBodyDto;
import com.api.fintech.model.dto.EmbeddedDto;
import com.api.fintech.model.dto.StudentDto;
import com.api.fintech.model.dto.StudentsDto;
import com.api.fintech.model.entity.Student;
import com.api.fintech.model.payload.MensajeResponse;
import com.api.fintech.service.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class StudentController {
    @Autowired
    private IStudentService studentServices;

    @GetMapping("/students")
    public ResponseEntity<?> all(){
        EmbeddedDto list = studentServices.listAll();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("La consulta es exitosa")
                ._embedded(list)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<?> create(@Valid @RequestBody DataBodyDto body) {
        try {
            Student existeStudent = studentServices.findByEmail(body.getEmail());
            if (existeStudent != null) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El student ya exite")
                        ._embedded(null)
                        .build(), HttpStatus.BAD_REQUEST);
            }
            StudentDto studentDto = StudentDto.builder()
                    .firtname(body.getFirtname())
                    .lastname(body.getLastname())
                    .email(body.getEmail())
                    .build();
            StudentsDto student = studentServices.save(studentDto);

            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Student se registro")
                    ._embedded(student)
                    .build(), HttpStatus.CREATED);
        }  catch (DataAccessException exData) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exData.getMessage())
                    ._embedded(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody DataBodyDto body, @PathVariable Integer id) {
        try {
            if (studentServices.existsById(id)) {
                StudentDto studentDto = StudentDto.builder()
                        .firtname(body.getFirtname())
                        .lastname(body.getLastname())
                        .email(body.getEmail())
                        .id(id)
                        .build();
                StudentsDto student = studentServices.save(studentDto);

                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Student se actualizo")
                        ._embedded(student)
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El student no existe")
                        ._embedded(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
        }  catch (DataAccessException exData) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exData.getMessage())
                    ._embedded(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Student student = studentServices.findById(id);
            studentServices.delete(student);
            return new ResponseEntity<>(MensajeResponse.builder()
                    ._embedded(null)
                    .mensaje("")
                    .build(), HttpStatus.NO_CONTENT);
        } catch (DataAccessException exData) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exData.getMessage())
                    ._embedded(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
        StudentsDto student = studentServices.findByIdF(id);
        if (student == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El student no existe")
                    ._embedded(null)
                    .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("La consulta es exitosa")
                ._embedded(student)
                .build(), HttpStatus.OK);
    }
}
