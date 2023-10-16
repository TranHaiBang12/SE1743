/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.repository;

import net.FAP.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author acer
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{
    @Query(value = "SELECT * FROM students WHERE student_id = ?1", nativeQuery = true)
    public Optional<Student> findByStudentID(int studentID);
}
