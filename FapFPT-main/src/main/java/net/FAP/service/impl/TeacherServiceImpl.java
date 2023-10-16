package net.FAP.service.impl;

import net.FAP.model.Teacher;
import net.FAP.repository.TeacherRepository;
import net.FAP.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Override
    public Optional<Teacher> findById(int teacherID) {
        return teacherRepository.findById(teacherID);
    }
}
