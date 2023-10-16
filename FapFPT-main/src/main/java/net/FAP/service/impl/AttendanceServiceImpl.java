/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.service.impl;

import net.FAP.model.Attendance;
import net.FAP.model.Timetable;
import net.FAP.repository.AttendanceRepository;
import net.FAP.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author acer
 */
@Service
public class AttendanceServiceImpl implements AttendanceService{
    
    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public Attendance save(Attendance a) {
        return attendanceRepository.save(a);
    }




    @Override
    public Iterable<Attendance> findByTimetable(Timetable timetable) {
        return attendanceRepository.findByTimetable(timetable);
    }

    @Override
    public Optional<Attendance> findById(int attendanceID) {
        return attendanceRepository.findById(attendanceID);
    }

    @Override
    public Optional<Attendance> findByUsernameAndTimetable(int studentID, int timetableID) {
        return attendanceRepository.findByUsernameAndTimetable(studentID, timetableID);
    }


}
