package io.github.Ital023.CourseAPISpringBoot.services;

import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseDTO;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseEntity;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseRepository;
import io.github.Ital023.CourseAPISpringBoot.domain.course.CourseStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity create(CourseDTO courseDTO){

        CourseEntity course = CourseEntity.builder()
                .name(courseDTO.name().toUpperCase())
                .category(courseDTO.category().toUpperCase())
                .active(CourseStatus.ACTIVE.getDescription())
                .build();

        courseRepository.save(course);
        return course;
    }

    //Retorna todos os cursos registrados no DB
    public List<CourseEntity> allCourses(){
        return courseRepository.findAll();
    }

    //Retorna todos os cursos ativos presentes no DB
    public List<CourseEntity> allActive(){
        List<CourseEntity> coursesActive = courseRepository.findByActive(CourseStatus.ACTIVE.getDescription());
        return coursesActive;
    }

    //Retorna todos os cursos filtrados pelo nome ou category
    public List<CourseEntity> byName(String name){
        List<CourseEntity> coursesByName = courseRepository.findByName(name.toUpperCase());
        return coursesByName;
    }

    public List<CourseEntity> byCategory(String category){
        List<CourseEntity> coursesByCategory = courseRepository.findByCategory(category.toUpperCase());
        return coursesByCategory;
    }

    public ResponseEntity alter(UUID id,CourseDTO courseDTO){

        Optional<CourseEntity> courseOptional = courseRepository.findById(id);

        if(courseOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        CourseEntity course = courseOptional.get();

        if(nameAndCategoryIsNotNull(courseDTO)){

            course.setName(courseDTO.name());
            course.setCategory(courseDTO.category());
        }
        else if(categoryIsNotNull(courseDTO)){

            course.setCategory(courseDTO.category());

        }else if(nameIsNotNull(courseDTO)){
            course.setName(courseDTO.name());
        }else{
            return ResponseEntity.badRequest().body("Preencha os campos.");
        }

        return ResponseEntity.noContent().build();
    }

    public void delete(UUID id){
        courseRepository.deleteById(id);
    }

    public ResponseEntity toggleActive(UUID id){
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

    //REFACTOR: EXTRACT METHOD

    public boolean nameAndCategoryIsNotNull(CourseDTO courseDTO){
        if(courseDTO.name() != null && courseDTO.category() != null){
            return true;
        }
        return false;
    }

    public boolean nameIsNotNull(CourseDTO courseDTO){
        if(courseDTO.name() != null){
            return true;
        }
        return false;
    }

    public boolean categoryIsNotNull(CourseDTO courseDTO){
        if(courseDTO.category() != null){
            return true;
        }
        return false;
    }

}
