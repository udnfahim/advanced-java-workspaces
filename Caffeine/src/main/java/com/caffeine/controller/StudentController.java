package com.caffeine.controller;

import com.caffeine.model.Student;
import com.caffeine.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:2222")
@RequestMapping("/api/cache/")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}/cached")
    public Map<String, Object> getStudentCached(@PathVariable Integer id)
            throws InterruptedException {

        long start = System.currentTimeMillis();
        Student s = studentService.getStudentByIdCached(id);
        long time = System.currentTimeMillis() - start;

        return Map.of(
                "student", s,
                "timeMs", time,
                "type", "CACHED"
        );
    }

    @GetMapping("/{id}/nocache")
    public Map<String, Object> getStudentNoCache(@PathVariable Integer id)
            throws InterruptedException {

        long start = System.currentTimeMillis();
        Student s = studentService.getStudentByIdNoCache(id);
        long time = System.currentTimeMillis() - start;

        return Map.of(
                "student", s,
                "timeMs", time,
                "type", "NO_CACHE"
        );
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }
}
