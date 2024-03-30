package fr.epita.assistants.myebook;

public interface IPaginated {
    int page = 0;

    // This should make the object opened to a specific page.
    // Page indices start at 0.
    // If the page number is invalid, do nothing.
    void openToPage(int page);

    // This method returns the currently opened page.
    default int getCurrentPage() {
        return -1;
    }

    // This method returns the total number of pages in the object.
    default int getPageCount() {
        return -1;
    }
}
