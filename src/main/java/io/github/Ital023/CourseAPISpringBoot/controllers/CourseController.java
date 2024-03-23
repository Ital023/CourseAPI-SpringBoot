package io.github.Ital023.CourseAPISpringBoot.controllers;

import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseDTO;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseEntity;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseRepository;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .active(CourseStatus.ACTIVE.getDescription())
                .build();

        System.out.println(course);

        courseRepository.save(course);
        return ResponseEntity.ok().body(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseEntity>> allCourses(){
        return ResponseEntity.ok().body(courseRepository.findAll());
    }

    @GetMapping("/active/")
    public ResponseEntity<List<CourseEntity>> allActive(){
        List<CourseEntity> coursesActive = courseRepository.findByActive(CourseStatus.ACTIVE.getDescription());
        return ResponseEntity.ok().body(coursesActive);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CourseEntity>> byName(@PathVariable String name){
        List<CourseEntity> coursesByName = courseRepository.findByName(name);
        return ResponseEntity.ok().body(coursesByName);
    }


}
