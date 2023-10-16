/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.util;

import net.FAP.model.Week;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class YearWeek {

    private List<Week> list = new ArrayList<>();

    public List<Week> year2023() {

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
        return list;
    }
}
