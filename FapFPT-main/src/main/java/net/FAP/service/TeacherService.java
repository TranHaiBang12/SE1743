package net.FAP.service;

import net.FAP.model.Teacher;

import java.util.Optional;

public interface TeacherService {

    public Optional<Teacher> findById(int teacherID);
}
