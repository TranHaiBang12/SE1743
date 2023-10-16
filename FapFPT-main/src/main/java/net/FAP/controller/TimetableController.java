/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.controller;

import net.FAP.model.*;
import net.FAP.service.AccountService;
import net.FAP.service.ClassService;
import net.FAP.service.TeacherService;
import net.FAP.service.TimetableService;
import net.FAP.util.FormatDate;
import net.FAP.util.YearWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author acer
 */
@Controller
public class TimetableController {

    @Autowired
    TimetableService ttbService;

    List<Week> list = new ArrayList<>();

    @Autowired
    AccountService userService;

    @Autowired
    ClassService csvc;

    @Autowired
    TeacherService tsvc;

    @Autowired
    AccountService acsv;

    public Account getUserByUsername(String username) {
        Optional<Account> u = userService.findByUsername(username);
        Account user = u.get();
        return user;
    }

    @GetMapping(value = "/teacher/timetable/{user}")
    public String getTimetableByDefault(@PathVariable String user, Model m) {
        Date now = Date.valueOf((Calendar.getInstance().getTime().getYear() + 1900) + "-" + (Calendar.getInstance().getTime().getMonth() + 1) + "-" + Calendar.getInstance().getTime().getDate());
        getAllWeek();
        Date d = null, s = null;
        int lecture = userService.findByUsername(user).get().getTeacher().getTeacherID();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getStart().compareTo(now) <= 0 && list.get(i).getEnd().compareTo(now) >= 0) {
                d = list.get(i).getStart();
                s = list.get(i).getEnd();
            }
        }
        Iterable<Timetable> listI = ttbService.findByWeek(d, s, lecture);
        List<Timetable> listT = new ArrayList<>();
        for(Timetable e: listI) {
            listT.add(e);
        }

        getAllWeek();
        String t = "2023-05-01";
        Date date1 = Date.valueOf(t);
        for (int i = 0; i < listT.size(); i++) {
            String day;
            String date2;
            String month;
            long time = listT.get(i).getStartDate().getTime() - date1.getTime();
            time = (time / (24 * 60 * 60 * 1000)) % 7;
            if (time == 0) {
                day = "Mon";
            } else if (time == 1) {
                day = "Tue";
            } else if (time == 2) {
                day = "Wed";
            } else if (time == 3) {
                day = "Thu";
            } else if (time == 4) {
                day = "Fri";
            } else if (time == 5) {
                day = "Sat";
            } else {
                day = "Sun";
            }
            listT.get(i).setDay(day);
        }

        m.addAttribute("start", d);
        m.addAttribute("end", s);
        m.addAttribute("week", list);
        m.addAttribute("list", listT);
        m.addAttribute("lecture", lecture);

        return "timetable";
    }



    @GetMapping(value = "/teacher/timetable/{id}/{lecture}")
    public String getTimetableByIDAlecture(@PathVariable int id, @PathVariable int lecture, Model m) {
        Teacher teacher = tsvc.findById(lecture).get();
        m.addAttribute("lecture", teacher);
        Timetable t = ttbService.findById(id).get();
        Clss c = csvc.findById(t.getClss().getClassID()).get();
        m.addAttribute("t", t);
        m.addAttribute("c", c);
        
        return "class";
    }

    @GetMapping(value = "/teacher/timetable/{d}/{s}/{lecture}")
    public String getTimetableByDate(@PathVariable Date d, @PathVariable Date s, @PathVariable int lecture, Model m) {
        Iterable<Timetable> listI = ttbService.findByWeek(d, s, lecture);
        List<Timetable> listT = new ArrayList<>();
                    for(Timetable e: listI) {
                      listT.add(e);
                    }
                  
        getAllWeek();
        String t = "2023-05-01";
        Date date1 = Date.valueOf(t);
        for (int i = 0; i < listT.size(); i++) {
            String day;
            String date2;
            String month;
            long time = listT.get(i).getStartDate().getTime() - date1.getTime();
            time = (time / (24 * 60 * 60 * 1000)) % 7;
            if (time == 0) {
                day = "Mon";
            } else if (time == 1) {
                day = "Tue";
            } else if (time == 2) {
                day = "Wed";
            } else if (time == 3) {
                day = "Thu";
            } else if (time == 4) {
                day = "Fri";
            } else if (time == 5) {
                day = "Sat";
            } else {
                day = "Sun";
            }
            listT.get(i).setDay(day);
        }

            m.addAttribute("start", d);
            m.addAttribute("end", s);
            m.addAttribute("week", list);
            m.addAttribute("list", listT);
            m.addAttribute("lecture", lecture);

            return "timetable";
    }

    public void getAllWeek() {

        list.add(new Week(1, Date.valueOf("2023-01-02"), Date.valueOf("2023-01-08")));
        list.add(new Week(1, Date.valueOf("2023-01-09"), Date.valueOf("2023-01-15")));
        list.add(new Week(1, Date.valueOf("2023-01-16"), Date.valueOf("2023-01-22")));
        list.add(new Week(1, Date.valueOf("2023-01-23"), Date.valueOf("2023-01-29")));
        list.add(new Week(1, Date.valueOf("2023-01-30"), Date.valueOf("2023-02-05")));
        list.add(new Week(1, Date.valueOf("2023-02-06"), Date.valueOf("2023-02-12")));
        list.add(new Week(1, Date.valueOf("2023-02-13"), Date.valueOf("2023-02-19")));
        list.add(new Week(1, Date.valueOf("2023-02-20"), Date.valueOf("2023-02-26")));
        list.add(new Week(1, Date.valueOf("2023-02-27"), Date.valueOf("2023-03-05")));
        list.add(new Week(1, Date.valueOf("2023-03-06"), Date.valueOf("2023-03-12")));
        list.add(new Week(1, Date.valueOf("2023-03-13"), Date.valueOf("2023-03-19")));
        list.add(new Week(1, Date.valueOf("2023-03-20"), Date.valueOf("2023-03-26")));
        list.add(new Week(1, Date.valueOf("2023-03-27"), Date.valueOf("2023-04-02")));
        list.add(new Week(1, Date.valueOf("2023-04-03"), Date.valueOf("2023-04-09")));
        list.add(new Week(1, Date.valueOf("2023-04-10"), Date.valueOf("2023-04-16")));
        list.add(new Week(1, Date.valueOf("2023-04-17"), Date.valueOf("2023-04-23")));

        list.add(new Week(1, Date.valueOf("2023-04-24"), Date.valueOf("2023-04-30")));
        list.add(new Week(1, Date.valueOf("2023-05-01"), Date.valueOf("2023-05-07")));
        list.add(new Week(1, Date.valueOf("2023-05-08"), Date.valueOf("2023-05-14")));
        list.add(new Week(1, Date.valueOf("2023-05-15"), Date.valueOf("2023-05-21")));
        list.add(new Week(1, Date.valueOf("2023-05-22"), Date.valueOf("2023-05-28")));
        list.add(new Week(1, Date.valueOf("2023-05-29"), Date.valueOf("2023-06-04")));
        list.add(new Week(1, Date.valueOf("2023-06-05"), Date.valueOf("2023-06-11")));
        list.add(new Week(1, Date.valueOf("2023-06-12"), Date.valueOf("2023-06-18")));
        list.add(new Week(1, Date.valueOf("2023-06-19"), Date.valueOf("2023-06-23")));
        list.add(new Week(1, Date.valueOf("2023-06-26"), Date.valueOf("2023-07-02")));
        list.add(new Week(1, Date.valueOf("2023-07-03"), Date.valueOf("2023-07-09")));
        list.add(new Week(1, Date.valueOf("2023-07-10"), Date.valueOf("2023-07-16")));
        list.add(new Week(1, Date.valueOf("2023-07-17"), Date.valueOf("2023-07-23")));
        list.add(new Week(1, Date.valueOf("2023-07-24"), Date.valueOf("2023-07-30")));
        list.add(new Week(1, Date.valueOf("2023-07-31"), Date.valueOf("2023-08-06")));
        list.add(new Week(1, Date.valueOf("2023-08-07"), Date.valueOf("2023-08-13")));

        list.add(new Week(1, Date.valueOf("2023-08-14"), Date.valueOf("2023-08-20")));
        list.add(new Week(1, Date.valueOf("2023-08-21"), Date.valueOf("2023-08-27")));
        list.add(new Week(1, Date.valueOf("2023-08-28"), Date.valueOf("2023-09-03")));
        list.add(new Week(1, Date.valueOf("2023-09-04"), Date.valueOf("2023-09-10")));
        list.add(new Week(1, Date.valueOf("2023-09-11"), Date.valueOf("2023-09-17")));
        list.add(new Week(1, Date.valueOf("2023-09-18"), Date.valueOf("2023-09-24")));
        list.add(new Week(1, Date.valueOf("2023-09-25"), Date.valueOf("2023-10-01")));
        list.add(new Week(1, Date.valueOf("2023-10-02"), Date.valueOf("2023-10-08")));
        list.add(new Week(1, Date.valueOf("2023-10-09"), Date.valueOf("2023-10-15")));
        list.add(new Week(1, Date.valueOf("2023-10-16"), Date.valueOf("2023-10-22")));
        list.add(new Week(1, Date.valueOf("2023-10-23"), Date.valueOf("2023-10-29")));
        list.add(new Week(1, Date.valueOf("2023-10-30"), Date.valueOf("2023-11-05")));
        list.add(new Week(1, Date.valueOf("2023-11-06"), Date.valueOf("2023-11-12")));
        list.add(new Week(1, Date.valueOf("2023-11-13"), Date.valueOf("2023-11-19")));
        list.add(new Week(1, Date.valueOf("2023-11-20"), Date.valueOf("2023-11-26")));
        list.add(new Week(1, Date.valueOf("2023-11-27"), Date.valueOf("2023-12-03")));

        list.add(new Week(1, Date.valueOf("2023-12-04"), Date.valueOf("2023-12-10")));
        list.add(new Week(1, Date.valueOf("2023-12-11"), Date.valueOf("2023-12-17")));
        list.add(new Week(1, Date.valueOf("2023-12-18"), Date.valueOf("2023-12-24")));
        list.add(new Week(1, Date.valueOf("2023-12-25"), Date.valueOf("2023-12-31")));

        FormatDate fd = new FormatDate();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setStartS(fd.formatDateSQL(list.get(i).getStart()));
            list.get(i).setEndS(fd.formatDateSQL(list.get(i).getEnd()));
        }
    }
}
