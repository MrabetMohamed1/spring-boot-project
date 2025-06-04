package com.example.controller;

import com.example.models.Student;
import com.example.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
//@CrossOrigin(origins = "*") // autoriser les appels depuis Postman, React, Angular, etc.
public class StudentRestController {

    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // GET: Récupérer tous les étudiants
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET: Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // POST: Créer un nouvel étudiant
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // PUT: Mettre à jour un étudiant
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            return studentRepository.save(existing);
        }
        return null;
    }

    // DELETE: Supprimer un étudiant
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}
