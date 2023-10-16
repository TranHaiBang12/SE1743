/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.service.impl;

import net.FAP.model.Clss;
import net.FAP.repository.ClassRepository;
import net.FAP.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author acer
 */
@Service
public class ClassServiceImpl implements ClassService{
    
    @Autowired
    ClassRepository classRepository;

    @Override
    public Optional<Clss> findById(int classID) {
        return classRepository.findById(classID);
    }
    
}
