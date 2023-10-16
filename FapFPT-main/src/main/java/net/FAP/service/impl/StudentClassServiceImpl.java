/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.service.impl;

import net.FAP.model.Clss;
import net.FAP.model.StudentClass;
import net.FAP.repository.StudentClassRepository;
import net.FAP.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class StudentClassServiceImpl implements StudentClassService{
    
    @Autowired
    StudentClassRepository scr;



    @Override
    public Iterable<StudentClass> findByClss(Clss clss) {
        return scr.findByClss(clss);
    }
    
    
}
