package fr.epita.assistans.embedfiles;

import fr.epita.assistants.embedfiles.DisplayEmbedFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisplayEmdedFileTest {
    @Test
    public void testWithExistingFile() {
        final var embedFile = new DisplayEmbedFile("sample.txt");
        final var result = embedFile.display().orElseThrow();
        assertTrue(result.contains("This is a sample"));
    }

    @Test
    public void testWithNonExistingFile() {
        final var embedFile = new DisplayEmbedFile("samplewfewfewfewfe.txt");
        final var result = embedFile.display();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testWithExistingFileMultipleLine() {
        final var embedFile = new DisplayEmbedFile("other-sample.txt");
        final var result = embedFile.display().orElseThrow();
        assertTrue(result.contains("gOpC7lf1zLh6qT+KKMYQxYikXmnbpDa0hpp9tSBBOtz9vOwdXpyqu4h6l5wTMdj956N094\n2qW30rBs8+eRIqd4IjLMObDSXpz9NL3NbozZBpC"));
    }
}
