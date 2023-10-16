/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.repository;

import net.FAP.model.Clss;
import net.FAP.model.StudentClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author acer
 */
@Repository
public interface StudentClassRepository extends CrudRepository<StudentClass, Integer>{
    public Iterable<StudentClass> findByClss(Clss clss);
}
