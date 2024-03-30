package fr.epita.assistants.myebook;

import java.util.ArrayList;
import java.util.List;

public class Book implements IPaginated, IReadable {

    protected String name;

    protected List<String> pages;

    private int index;

    // Instantiate a book with a certain title and page contents.
    // Only the EBook class should be able to call the Book constructor since it
    // is the only one able to print it, hence it must not be public.
    protected Book(String name, List<String> pages) {
        this.name = name;
        this.pages = pages;
        this.index = 0;
    }

    // Get the name of the book.
    public String getName() {
        return this.name;
    }

    // Create an EBook from the book.
    public EBook scan() {
        List<String> cloned = new ArrayList<>(this.pages);
        EBook res = new EBook(this.name);
        res.pages = cloned;
        return res;
    }

    @Override
    public void openToPage(int page) {
        if (page >= 0 && page < this.pages.size()) this.index = page;
    }

    @Override
    public int getCurrentPage() {
        return this.index;
    }

    @Override
    public int getPageCount() {
        return this.pages.size();
    }

    @Override
    public String readCurrentPage() {
        return this.pages.get(this.index);
    }
}
