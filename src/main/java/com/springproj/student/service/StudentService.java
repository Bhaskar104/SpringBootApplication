package com.springproj.student.service;


import com.springproj.student.entity.Student;
import com.springproj.student.repository.StudentRepository;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;

    }
    public List<Student> hello(){
        return  studentRepository.findAll();

    }

    public void registerStudent(Student student) {
        Optional<Student> student1 = studentRepository.findStudentByEmail(student.getEmail());
        if(student1.isPresent()){
            throw new IllegalStateException("Student Already Exist with email "+student.getEmail());
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if(!studentRepository.existsById(studentId)){
            throw new IllegalStateException("No Student Available with ID "+studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student std = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("No Student Available with Student ID "+studentId));

        if(name != null && name.length() > 0 ){
            std.setName(name);
        }
        if(email != null && email.length() > 0 ){
            std.setEmail(email);
        }
    }
}
