/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.controller;

import net.FAP.model.Attendance;
import net.FAP.model.Clss;
import net.FAP.model.StudentClass;
import net.FAP.model.Timetable;
import net.FAP.service.*;
import net.FAP.util.YearWeek;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author acer
 */
@Controller
public class AttendanceController {

    @Autowired
    AttendanceService attnSv;

    @Autowired
    TimetableService ttbSv;

    @Autowired
    StudentClassService scs;

    @Autowired
    StudentService ss;

    @Autowired
    ClassService csvc;

    @Autowired
    AccountService userService;

    @Autowired
    TeacherService tsvc;

    @GetMapping("/teacher/attendance/{timetableID}")
    public String getAttendanceByTimetable(@PathVariable int timetableID, Model m) throws JsonProcessingException, IOException {
        Timetable timetable = ttbSv.findById(timetableID).get();

        Iterable<Attendance> list = attnSv.findByTimetable(timetable);
        List<Attendance> listA = new ArrayList<>();
        for(Attendance e: list) {
                      listA.add(e);
                    }
        if (listA.isEmpty()) {
            Iterable<StudentClass> listSIC = scs.findByClss(timetable.getClss());
            List<StudentClass> listSC = new ArrayList<>();
                    for(StudentClass s: listSIC) {
                      listSC.add(s);
                    }
            if (!listSC.isEmpty()) {
                m.addAttribute("listS", listSC);
                m.addAttribute("lecture", timetable.getLecture());
                m.addAttribute("timetableID", timetableID);
                m.addAttribute("attend", timetable.isAttend());
                return "attendance";
            }
            else {
                m.addAttribute("message", "Dont have any students");
                return "attendance";
            }
        } else {
            m.addAttribute("listA", listA);
            m.addAttribute("lecture", timetable.getLecture());
            m.addAttribute("timetableID", timetableID);
            m.addAttribute("attend", timetable.isAttend());
            return "attendance";
        }

    }

    @PostMapping(value = "/teacher/attendance/add")
    public String add(@RequestParam(value = "id") String id, @RequestParam(value = "timetable") int timetable, @RequestParam(value = "lecture") int lecture, Model m) throws IOException {
        int k = 0;
        int studentID = 0;
        boolean status = false;
        int cnt = 0;
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == ('i')) {
                cnt++;
                studentID = Integer.parseInt(id.substring(k, i));
            }
            if (i + 5 <= id.length()) {
                if (id.substring(i + 1, i + 5).equals("true")) {
                    status = true;
                    k = i + 5;
                }
            }
            if (i + 6 <= id.length()) {
                if (id.substring(i + 1, i + 6).equals("false")) {
                    status = false;
                    k = i + 6;
                }
            }
            if (studentID != 0 && cnt != 0) {
                cnt = 0;
                Attendance a = new Attendance();
                Timetable t = ttbSv.findById(timetable).get();
                t.setAttend(true);
                ttbSv.save(t);
                a.setStatus(status);
                a.setTimetable(t);

                a.setStudent(ss.findByStudentID(studentID).get());
                    attnSv.save(a);

            }
        }
        TimetableController tc = new TimetableController();
        tc.csvc = csvc;
        tc.list = new YearWeek().year2023();
        tc.ttbService = ttbSv;
        tc.userService = userService;
        tc.tsvc = tsvc;
        tc.getTimetableByIDAlecture(timetable, lecture, m);

//        Map<String,Object> statusJson = new HashMap<>();
//        try {
//            attnSv.save(a);
//            statusJson.put("code","200");
//            statusJson.put("message","Student inserted successfully!");
//            statusJson.put("data",a);
//        }
//        catch (Exception e) {
//            String fullMessage = e.getMessage();
//            statusJson.put("code","500");
//            statusJson.put("message",String.format("Fail to update student!\nReason: %s",fullMessage));
//        }
         getAttendanceByTimetable(timetable, m);
        m.addAttribute("lecture", tsvc.findById(lecture).get());
        Timetable t = ttbSv.findById(timetable).get();
        Clss c = csvc.findById(t.getClss().getClassID()).get();
        m.addAttribute("t", t);
        m.addAttribute("c", c);
        m.addAttribute("message", "Add success");
        return "class";
    }
    @GetMapping(value="/teacher/attendance/update/{id}/{timetable}/{teacher}")
    public String test(@PathVariable String id, @PathVariable int timetable, @PathVariable int lecture, Model m) throws IOException {
        int k = 0;
        int studentID = 0;
        boolean status = false;
        int cnt = 0;
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == ('i')) {
                cnt++;
                studentID = Integer.parseInt(id.substring(k, i));
            }
            if (i + 5 <= id.length()) {
                if (id.substring(i + 1, i + 5).equals("true")) {
                    status = true;
                    k = i + 5;
                }
            }
            if (i + 6 <= id.length()) {
                if (id.substring(i + 1, i + 6).equals("false")) {
                    status = false;
                    k = i + 6;
                }
            }
            if (studentID != 0 && cnt != 0) {
                cnt = 0;
                Optional<Attendance> atd = attnSv.findByUsernameAndTimetable(ss.findByStudentID(studentID).get().getStudentID(), ttbSv.findById(timetable).get().getTimetableID());
                Timetable t = ttbSv.findById(timetable).get();
                Attendance a = atd.get();
                a.setStatus(status);
                a.setTimetable(t);

                a.setStudent(ss.findByStudentID(studentID).get());
                attnSv.save(a);
            }
        }

//        Map<String,Object> statusJson = new HashMap<>();
//        try {
//            attnSv.save(a);
//            statusJson.put("code","200");
//            statusJson.put("message","Student inserted successfully!");
//            statusJson.put("data",a);
//        }
//        catch (Exception e) {
//            String fullMessage = e.getMessage();
//            statusJson.put("code","500");
//            statusJson.put("message",String.format("Fail to update student!\nReason: %s",fullMessage));
//        }
        getAttendanceByTimetable(timetable, m);
        m.addAttribute("lecture", tsvc.findById(lecture).get());
        Timetable t = ttbSv.findById(timetable).get();
        Clss c = csvc.findById(t.getClss().getClassID()).get();
        m.addAttribute("t", t);
        m.addAttribute("c", c);
        m.addAttribute("message", "Update success");
        return "class";
    }



    @PostMapping(value = "/teacher/attendance/update")
    public String upd(@RequestParam(value = "id") String id, @RequestParam(value = "timetable") int timetable, @RequestParam(value = "lecture") int lecture, Model m) throws IOException {

                int k = 0;
        int studentID = 0;
        boolean status = false;
        int cnt = 0;
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == ('i')) {
                cnt++;
                studentID = Integer.parseInt(id.substring(k, i));
            }
            if (i + 5 <= id.length()) {
                if (id.substring(i + 1, i + 5).equals("true")) {
                    status = true;
                    k = i + 5;
                }
            }
            if (i + 6 <= id.length()) {
                if (id.substring(i + 1, i + 6).equals("false")) {
                    status = false;
                    k = i + 6;
                }
            }
            if (studentID != 0 && cnt != 0) {
                cnt = 0;
                Optional<Attendance> atd = attnSv.findByUsernameAndTimetable(ss.findByStudentID(studentID).get().getStudentID(), ttbSv.findById(timetable).get().getTimetableID());
                Timetable t = ttbSv.findById(timetable).get();
                Attendance a = atd.get();
                a.setStatus(status);
                a.setTimetable(t);

                a.setStudent(ss.findByStudentID(studentID).get());
                attnSv.save(a);
            }
        }

//        Map<String,Object> statusJson = new HashMap<>();
//        try {
//            attnSv.save(a);
//            statusJson.put("code","200");
//            statusJson.put("message","Student inserted successfully!");
//            statusJson.put("data",a);
//        }
//        catch (Exception e) {
//            String fullMessage = e.getMessage();
//            statusJson.put("code","500");
//            statusJson.put("message",String.format("Fail to update student!\nReason: %s",fullMessage));
//        }
         getAttendanceByTimetable(timetable, m);
        m.addAttribute("lecture", tsvc.findById(lecture).get());
        Timetable t = ttbSv.findById(timetable).get();
        Clss c = csvc.findById(t.getClss().getClassID()).get();
        m.addAttribute("t", t);
        m.addAttribute("c", c);
        m.addAttribute("message", "Update success");
        return "class";
    }

    @PostMapping("/teacher/students/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable int id, @RequestBody Attendance newAttendance) {
        Map<String, Object> statusJson = new HashMap<>();
        Optional<Attendance> atd = attnSv.findById(id);

        if (!atd.isPresent()) {
            statusJson.put("code", "404");
            statusJson.put("message", String.format("Student with id: '%d' can't be found to update!", id));
            return ResponseEntity.ok(statusJson);
        }

        try {
            Attendance a = atd.get();
            a.setStatus(newAttendance.isStatus());
            a.setTimetable(newAttendance.getTimetable());
            a.setStudent(newAttendance.getStudent());
            attnSv.save(a);
            statusJson.put("code", "200");
            statusJson.put("message", "Student updated successfully!");
            statusJson.put("data", a);
        } catch (Exception e) {
            String fullMessage = e.getMessage();
            statusJson.put("code", "500");
            statusJson.put("message", String.format("Fail to update student!\nReason: %s", fullMessage));
        }
        return ResponseEntity.ok(statusJson);
    }
}
