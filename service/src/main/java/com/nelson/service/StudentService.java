package com.nelson.service;

import com.nelson.domain.Student;
import com.nelson.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

//    @Autowired
//    private Student student;
// fetch all students
    public List<Student> fetchAll(){
        List<Student> studentList=studentRepository.findAll();
        return studentList;
    }

    //fetch one student
    public Student fetchOne(Long id){
        Optional<Student> student=studentRepository.findById(id);
        Student student1=new Student();
        if (student.isPresent()){
             student1=student.get();
        }
        return student1;
    }

    // create student
    public Student createStudent(Student student){
        student.setStatus("UnApproved");
        Student student1=studentRepository.save(student);

        return student1;
    }

    // approve student
public Student approveStudent(Long id){
        Optional<Student> get1=studentRepository.findById(id);
    Student student=new Student();
    Student resp=new Student();
        if (get1.isPresent()){
             student=get1.get();
            student.setStatus("Approve");
            studentRepository.save(student);
            Student approved=studentRepository.findById(id).get();
            System.out.println("****Approved*****"+approved);
            resp=approved;
        }
        return resp;
}
    //unapprove student
    public Student unapproveStudent(Long id){
        Optional<Student> get1=studentRepository.findById(id);
        Student student=new Student();
        Student resp=new Student();
        if (get1.isPresent()){
            student=get1.get();
            student.setStatus("UnApprove");
            studentRepository.save(student);
            Student approved=studentRepository.findById(id).get();
            System.out.println("****UnApproved*****"+approved);
            resp=approved;
        }
        return resp;
    }
    // disable student
    public Student disableStudent(Long id){
        Optional<Student> get1=studentRepository.findById(id);
        Student student=new Student();
        Student resp=new Student();
        if (get1.isPresent()){
            student=get1.get();
            student.setStatus("Disabled");
            studentRepository.save(student);
            Student approved=studentRepository.findById(id).get();
            System.out.println("****Disabled*****"+approved);
            resp=approved;
        }
        return resp;
    }
    //enable student
    public Student enabledStudent(Long id){
        Optional<Student> get1=studentRepository.findById(id);
        Student student=new Student();
        Student resp=new Student();
        if (get1.isPresent()){
            student=get1.get();
            student.setStatus("Enabled");
            studentRepository.save(student);
            Student approved=studentRepository.findById(id).get();
            System.out.println("****Enabled*****"+approved);
            resp=approved;
        }
        return resp;
    }
    //delete student
    public Student deleteStudent(Long id){
        Optional<Student> get1=studentRepository.findById(id);
        Student student=new Student();
        Student resp=new Student();
        if (get1.isPresent()){
            student=get1.get();
            student.setStatus("Deleted");
            studentRepository.save(student);
            Student approved=studentRepository.findById(id).get();
            System.out.println("****Deleted*****"+approved);
            resp=approved;
        }
        return resp;
    }

    //update student
}
