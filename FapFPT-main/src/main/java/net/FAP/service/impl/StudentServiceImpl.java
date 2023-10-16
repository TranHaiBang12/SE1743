/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.service.impl;

import net.FAP.model.Student;
import net.FAP.repository.StudentRepository;
import net.FAP.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author acer
 */
@Service
public class StudentServiceImpl implements StudentService{
    
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Optional<Student> findByStudentID(int studentID) {
        return studentRepository.findByStudentID(studentID);
    }
    
}
