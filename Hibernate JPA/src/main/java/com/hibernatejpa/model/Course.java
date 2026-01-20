package com.hibernatejpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // define as a table by mapping mysql
@Table(name = "courses")
public class Course {

    @Id // for set id as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for set id auto increment in identity for mysql
    private int id ;
    private String courseName ;
    private String courseCode ;

    @ManyToMany(mappedBy = "courseList") // here we use mappedBy for define which will be owner , and student is owner ;
    private List<Student> studentList ;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "teacherCourseMapping" , joinColumns = @JoinColumn(name = "couseId"))
    private Teacher teacher; // teacher one cause here has many course to one teacher , no need just single object
}
