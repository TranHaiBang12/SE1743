/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.service;

import net.FAP.model.Timetable;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.Optional;

/**
 *
 * @author acer
 */
public interface TimetableService {
    @Query(value = "SELECT * FROM timetable WHERE start_date >= ?1 AND end_date <= ?2 AND lecture = ?3", nativeQuery = true)
    public Iterable<Timetable> findByWeek(Date d, Date s, int lecture);
    
    public Optional<Timetable> findById(int id);
    
    public Iterable<Timetable> findAll();
    
    public Timetable save(Timetable t);
}
