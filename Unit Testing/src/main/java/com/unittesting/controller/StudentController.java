package com.unittesting.controller;

import com.unittesting.model.Student;
import com.unittesting.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return service.getStudent(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return "Deleted";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/admin/dashboard")
    public String admin() { return "admin"; }

    @GetMapping("/dashboard")
    public String dashboard() { return "dashboard"; }
}
