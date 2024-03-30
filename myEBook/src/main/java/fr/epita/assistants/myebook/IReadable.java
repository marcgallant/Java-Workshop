package fr.epita.assistants.myebook;

public interface IReadable {
    // This should return the contents of the currently opened page.
    default String readCurrentPage() {
        return null;
    }
}