package io.github.Ital023.CourseAPISpringBoot.services;

import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;



}
