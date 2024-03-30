package fr.epita.assistants.myebook;

public class EBookReader implements IPaginated, IReadable, IUpdatable {

    protected EBook eBook;

    private double version;

    // Instantiate a new ebook reader with firmware 1.0
    public EBookReader() {
        this.eBook = null;
        this.version = 1.0;
    }

    // Load an ebook into the reader.
    public void openEbook(EBook ebook) {
        this.eBook = ebook;
    }

    @Override
    public void openToPage(int page) {
        if (eBook != null)
            eBook.openToPage(page);
    }

    @Override
    public int getCurrentPage() {
        if (eBook != null)
            return eBook.getCurrentPage();
        return -1;
    }

    @Override
    public int getPageCount() {
        if (eBook != null)
            return eBook.getPageCount();
        return -1;
    }

    @Override
    public String readCurrentPage() {
        if (eBook != null)
            return eBook.pages.get(eBook.index);
        return null;
    }

    @Override
    public double getVersion() {
        return this.version;
    }

    @Override
    public void update(double version) {
        if (version > this.version)
            this.version = version;
    }
}
