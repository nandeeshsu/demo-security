/**
 * 
 */
package com.example.demo.security.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.model.Student;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nande
 *
 */
@RestController
@RequestMapping("mgmt/api/v1/students")
@Slf4j
public class StudentManagementAPI {
	
	private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Nandeesh"),
            new Student(2, "Mita"),
            new Student(3, "Priya")
    );
	
	@GetMapping
	public List<Student> getAllStudents() {
		return STUDENTS;
	}
	
	@GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId())).findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist "));
    }
	
	@PostMapping
	public Student registerNewStudent(@RequestBody Student student) {
		return student;
	}
	
	@DeleteMapping
	public void deleteStudent(@RequestBody Student student) {
		log.info("student deleted => {} ", student);
	}
	
	@PutMapping
	public void updateStudent(@RequestBody Student student) {
		log.info("student updated => {} ", student);
	}
}
