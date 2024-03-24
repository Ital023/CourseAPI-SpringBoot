package io.github.Ital023.CourseAPISpringBoot.controllers;

import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseDTO;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseEntity;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseRepository;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseStatus;
import io.github.Ital023.CourseAPISpringBoot.services.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity create(@RequestBody CourseDTO courseDTO, UriComponentsBuilder uriBuilder){
        CourseEntity course =  courseService.create(courseDTO);

        var uri = uriBuilder.path("/courses/{id}")
                .buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(uri).body(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseEntity>> allCourses(){
        return ResponseEntity.ok().body(courseService.allCourses());
    }

    //Todos os cursos ativos da plataforma
    @GetMapping("/active/")
    public ResponseEntity<List<CourseEntity>> allActive(){;
        return ResponseEntity.ok().body(courseService.allActive());
    }

    //Todos os curos apartir do nome
    @GetMapping("/body")
    public ResponseEntity<List<CourseEntity>> byName(@RequestBody CourseDTO courseDTO){

        if(courseService.nameIsNotNull(courseDTO)){
            return ResponseEntity.ok().body(courseService.byName(courseDTO.name()));
        }

        return ResponseEntity.ok().body(courseService.byCategory(courseDTO.category()));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity alter(@PathVariable UUID id,@RequestBody CourseDTO courseDTO){
        return courseService.alter(id,courseDTO);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    @Transactional
    public ResponseEntity toggleActive(@PathVariable UUID id){
        return courseService.toggleActive(id);
    }

}
