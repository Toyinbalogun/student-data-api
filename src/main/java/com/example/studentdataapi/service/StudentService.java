package com.example.studentdataapi.service;

import com.example.studentdataapi.dao.StudentRepository;
import com.example.studentdataapi.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll(); /* returns a list */
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentChecker = studentRepository. //first checks if the student already exists
                findStudentByEmail(student.getEmail());
        if (studentChecker.isPresent()) {
            throw new IllegalStateException("student with email already exists!");
        }
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "studentID " + studentId + " does not exist!"));
        if (name != null &&
                name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> emailChecker = studentRepository.
                    findStudentByEmail(email);
            if (emailChecker.isPresent()) {
                throw new IllegalStateException("student with email already exists!");
            }
            student.setEmail(email);
        }
    }

    public void deleteStudent(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if (!studentExists) {
            throw new IllegalStateException("studentID " + studentId + " does not exist!");
        }
        studentRepository.deleteById(studentId);
    }
}
