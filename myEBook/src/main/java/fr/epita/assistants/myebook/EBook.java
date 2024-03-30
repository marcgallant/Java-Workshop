package fr.epita.assistants.myebook;

import java.util.ArrayList;
import java.util.List;

public class EBook implements IEditable, IPaginated {
    protected String name;

    protected List<String> pages;

    protected int index;

    // Instantiate an ebook with a specific name.
    public EBook(String name) {
        this.name = name;
        this.pages = new ArrayList<>();
        this.pages.add("");
        this.index = 0;
    }

    // Get the name of the book.
    public String getName() {
        return this.name;
    }

    // Create a book from the EBook.
    // The instantiated book is a version of the ebook, and can no longer be modified
    public Book print() {
        List<String> cloned = new ArrayList<>(this.pages);
        Book res = new Book(this.name, cloned);
        return res;
    }

    @Override
    public void writeCurrentPage(String pageText) {
        this.pages.set(index, pageText);
    }

    @Override
    public void addPage() {
        this.pages.add(this.index + 1, "");
    }

    @Override
    public void deletePage() {
        this.pages.remove(this.index);
        if (this.pages.size() == 0)
            this.pages.add("");
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
}
