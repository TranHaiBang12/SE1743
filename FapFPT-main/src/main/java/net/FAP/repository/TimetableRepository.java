/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.repository;

import net.FAP.model.Timetable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

/**
 *
 * @author acer
 */
@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Integer>{
    @Query(value = "SELECT * FROM timetable WHERE start_date >= ?1 AND end_date <= ?2 AND lecture = ?3", nativeQuery = true)
    public Iterable<Timetable> findByWeek(Date d, Date s, int lecture);
}
