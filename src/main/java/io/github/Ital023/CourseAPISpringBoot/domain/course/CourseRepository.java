package io.github.Ital023.CourseAPISpringBoot.domain.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    List<CourseEntity> findByActive(String active);

    @Query(value = "SELECT * FROM courses WHERE name LIKE %:keyword%", nativeQuery = true)
    List<CourseEntity> findByName(@Param("keyword") String keyword);

}
