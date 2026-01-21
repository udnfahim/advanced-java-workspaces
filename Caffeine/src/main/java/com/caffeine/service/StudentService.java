package com.caffeine.service;

import com.caffeine.model.Student;
import com.caffeine.repository.StudentRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Cacheable(value = "students", key = "#id")
    public Student getStudentByIdCached(Integer id) throws InterruptedException {
        Thread.sleep(2000); // simulate DB delay
        return studentRepo.findById(id).orElse(null);
    }

    public Student getStudentByIdNoCache(Integer id) throws InterruptedException {
        Thread.sleep(2000);
        return studentRepo.findById(id).orElse(null);
    }

    @CachePut(value = "students", key = "#id")
    public Student updateStudent(Integer id, Student student) {
        student.setId(id);
        return studentRepo.save(student);
    }

    @CacheEvict(value = "students", key = "#id")
    public void deleteStudent(Integer id) {
        studentRepo.deleteById(id);
    }


}
