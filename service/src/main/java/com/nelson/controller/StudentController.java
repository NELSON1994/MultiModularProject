package com.nelson.controller;

import com.nelson.domain.Student;
import com.nelson.service.GetValueService;
import com.nelson.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private GetValueService service;

    @Autowired
    private StudentService service1;

     @GetMapping("/VG/{value}")
    private String welcomeText( @PathVariable("value") String value){
        String res=null;
          res=service.fetchValue(value);
        return res;
    }
    @PostMapping("/save")
    private Student saveStudent(@RequestBody Student student){
        Student student1= service1.createStudent(student);
        return student1;
    }
    @GetMapping("/users")
    private List<Student> fetchAll(){
         List<Student> students=service1.fetchAll();
         return students;
    }
    @GetMapping("/users/{id}")
    private Student fetchone(@PathVariable("id") Long id){
         Student student=service1.fetchOne(id);
         return student;
    }
    @PutMapping("/users/approve/{id}")
    private Student approve(@PathVariable("id") Long id){
        Student student=service1.approveStudent(id);
        return student;
    }
    @PutMapping("/users/unapprove/{id}")
    private Student unapprove(@PathVariable("id") Long id){
        Student student=service1.unapproveStudent(id);
        return student;
    }
    @PutMapping("/users/delete/{id}")
    private Student delete(@PathVariable("id") Long id){
        Student student=service1.deleteStudent(id);
        return student;
    }
    @PutMapping("/users/disable/{id}")
    private Student disable(@PathVariable("id") Long id){
        Student student=service1.disableStudent(id);
        return student;
    }
    @PutMapping("/users/enable/{id}")
    private Student enable(@PathVariable("id") Long id){
        Student student=service1.enabledStudent(id);
        return student;
    }

}
