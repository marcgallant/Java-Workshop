import fr.epita.assistants.myebook.Book;
import fr.epita.assistants.myebook.EBook;
import fr.epita.assistants.myebook.EBookReader;

import static junit.framework.Assert.assertEquals;

public class Main {
    public static void main(String[] args) {
        EBook eBook = new EBook("Harry Potter");
        eBook.writeCurrentPage("This story is about a young wizard..");
        eBook.addPage();
        eBook.writeCurrentPage("This story is about a young wizard..");
        eBook.addPage();
        eBook.openToPage(2);
        eBook.addPage();
        eBook.writeCurrentPage("This story is about a young wizard..");
        eBook.addPage();
        eBook.openToPage(4);
        eBook.addPage();
        eBook.writeCurrentPage("This story is about a young wizard..");
        eBook.addPage();
        eBook.addPage();
        eBook.addPage();

        assertEquals(eBook.getPageCount(), 9);
        assertEquals(eBook.getCurrentPage(), 2);


        Book printedBook = eBook.print();

        assertEquals("This story is about a young wizard..",printedBook.readCurrentPage());
        EBookReader eBookReader = new EBookReader();
        eBookReader.openEbook(printedBook.scan());

        assertEquals("This story is about a young wizard..",eBookReader.readCurrentPage());

    }
}
