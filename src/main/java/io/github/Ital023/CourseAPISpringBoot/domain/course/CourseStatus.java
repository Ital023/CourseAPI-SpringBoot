package io.github.Ital023.CourseAPISpringBoot.domain.course;

public enum CourseStatus {
    ACTIVE("active"),
    DESACTIVE("desactive");

    private final String description;

    CourseStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}