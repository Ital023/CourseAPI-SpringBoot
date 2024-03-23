package io.github.Ital023.CourseAPISpringBoot.domain.course;

import jakarta.validation.constraints.NotBlank;

public record CourseDTO(@NotBlank String name,@NotBlank String category) {
}
