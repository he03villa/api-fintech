package com.api.fintech.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "firtname")
    private String firtname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
}
