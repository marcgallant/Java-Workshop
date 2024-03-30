package fr.epita.assistants.embedfiles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DisplayEmbedFile {
    private final String filename;

    public DisplayEmbedFile(String filename) {
        this.filename = filename;
    }

    public Optional<String> display() {
        InputStream inputStream = DisplayEmbedFile.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            return Optional.empty();
        }

        Stream<String> stream = new BufferedReader(new InputStreamReader(inputStream)).lines();
        return Optional.of(stream.reduce("", (accumulator, i) -> accumulator + i + "\n"));
    }
}
