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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity alter(@PathVariable UUID id,@RequestBody CourseDTO courseDTO){

        Optional<CourseEntity> courseOptional = courseRepository.findById(id);

        if(courseOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        CourseEntity course = courseOptional.get();

        if(courseDTO.name() != null && courseDTO.category() != null){
            course.setName(courseDTO.name());
            course.setCategory(courseDTO.category());
        }
        else if(courseDTO.name() == null){
            course.setCategory(courseDTO.category());
        }else{
            course.setName(courseDTO.name());
        }

        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    @Transactional
    public ResponseEntity toggleActive(@PathVariable UUID id){
        Optional<CourseEntity> courseOptional = courseRepository.findById(id);

        if(courseOptional.isEmpty()){
            return ResponseEntity.badRequest().body("Usuario nao existe");
        }

        CourseEntity course = courseOptional.get();

        if(course.getActive().equals(CourseStatus.ACTIVE.getDescription())){
            course.setActive(CourseStatus.DESACTIVE.getDescription());
        }else{
            course.setActive(CourseStatus.ACTIVE.getDescription());
        }

        return ResponseEntity.noContent().build();
    }

}
