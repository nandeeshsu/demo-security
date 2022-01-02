package com.example.demo.security.api;

import com.example.demo.security.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentAPI {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Nandeesh"),
            new Student(2, "Mita"),
            new Student(3, "Priya")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId())).findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist "));
    }
}
