/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.repository;

import net.FAP.model.Attendance;
import net.FAP.model.Timetable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author acer
 */
@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Integer> {

    public Iterable<Attendance> findByTimetable(Timetable timetable);

    @Query(value = "SELECT * FROM attendance WHERE student_id = ?1 AND timetable_id=?2", nativeQuery = true)
    public Optional<Attendance> findByUsernameAndTimetable(int studentID, int timetableID);
}
