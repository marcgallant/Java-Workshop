package fr.epita.assistants.practicelombok;

import lombok.*;

@Value
public class Shark {
    private final String name;
    private final String nickname;
    private final int speed;
}
