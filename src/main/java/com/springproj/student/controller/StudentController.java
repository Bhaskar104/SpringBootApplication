package com.springproj.student.controller;


import com.springproj.student.entity.Student;
import com.springproj.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {

    private final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> hello(){
        return studentService.hello();
    }

    @PostMapping
    public void registerNewStudent (@RequestBody Student student){
        studentService.registerStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable ("studentId") Long studentId, @RequestParam (required = false) String name, @RequestParam (required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }
}
