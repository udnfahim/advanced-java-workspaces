package com.unittesting.repository;

import com.unittesting.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public Student save(Student student) {
        students.add(student);
        return student;
    }

    public Optional<Student> findById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id) {
        students.removeIf(student -> student.getId().equals(id));
    }
}
