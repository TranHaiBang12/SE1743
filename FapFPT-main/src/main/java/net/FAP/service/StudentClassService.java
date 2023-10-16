/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.service;

import net.FAP.model.Clss;
import net.FAP.model.StudentClass;

/**
 *
 * @author acer
 */
public interface StudentClassService {
    public Iterable<StudentClass> findByClss(Clss clss);
}
