package fr.epita.assistants.grades.model;

import lombok.*;

import java.util.List;

@Builder
public record Student(String name, int age, List<Grade> grades) {
}
