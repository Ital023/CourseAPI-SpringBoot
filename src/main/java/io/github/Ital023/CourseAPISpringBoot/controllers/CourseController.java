package io.github.Ital023.CourseAPISpringBoot.controllers;

import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseDTO;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseEntity;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseRepository;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody CourseDTO courseDTO){

        CourseEntity course = CourseEntity.builder()
                .name(courseDTO.name())
                .category(courseDTO.category())
                .courseStatus(CourseStatus.ACTIVE.getDescription())
                .build();

        System.out.println(course);

        courseRepository.save(course);
        return ResponseEntity.ok().body(course);
    }
}
