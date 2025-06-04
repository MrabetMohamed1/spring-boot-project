package com.example.controller;

import com.example.models.Student;
import com.example.repository.StudentRepository;
import com.example.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String listStudents(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = studentRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            students = studentRepository.findAll();
        }
        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
