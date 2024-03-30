package fr.epita.assistants.practicelombok;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "nickname"})
@ToString(of = {"name", "speed"})
public class Horse {
    @Getter @Setter private String name;
    @Getter @Setter private String nickname;
    @Getter private int speed;
}
