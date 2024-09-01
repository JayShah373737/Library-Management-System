package com.example.library;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

    @Test
    public void testBookCreation() {

        Book b1 = new Book("37", "Java", "Alistar Cook", 2007);
        Library l = new Library();
        assertEquals("37", b1.getId());
        assertEquals("Java", b1.getTitle());
        assertEquals("Alistar Cook", b1.getAuthor());
        assertEquals(2007, b1.getPublicationYear());
        assertTrue(b1.isAvailable());
        assertTrue("Book should be added successfully.", l.addBook(b1));

    }

    @Test
    public void testAddDuplicateBook() {
        Library l = new Library();
        Book book1 = new Book("1234567890", "Title One", "Author One", 2024);
        l.addBook(book1);

        Book book2 = new Book("1234567890", "Title One", "Author One", 2024); // Duplicate

        boolean result = l.addBook(book2);
        assertFalse("Duplicate book should not be added.", result);

        assertEquals("Library should contain only one unique book.", 1, l.getBooks().size());
    }

    private Library library;
    private Book availableBook;
    private Book borrowedBook;

    @Before
    public void setUp() {
        library = new Library();
        availableBook = new Book("1234567890", "Title One", "Author One", 2024);
        borrowedBook = new Book("0987654321", "Title Two", "Author Two", 2025);
        library.addBook(availableBook);
    }

    @Test
    public void testBorrowAvailableBook() {
        boolean result = library.borrowBook(availableBook.getId());
        assertTrue("The book should be successfully borrowed.", result);
        assertFalse("The book should be marked as not available after borrowing.", !availableBook.isAvailable());
    }

    @Test
    public void testBorrowUnavailableBook() {
        library.borrowBook(availableBook.getId());

        boolean result = library.borrowBook(availableBook.getId());
        assertFalse("The book should not be borrowed if it is already unavailable.", result);
    }

    @Test
    public void testReturnBook() {
        boolean returnResult = library.returnBook(availableBook.getId());
        assertTrue("Book should be returned successfully", !returnResult);

        boolean borrowAgainResult = library.borrowBook(availableBook.getId());
        assertTrue("The book should be available for borrowing again after being returned.", borrowAgainResult);

        boolean invalidReturnResult = library.returnBook("8520");
        assertFalse("Return operation should fail for a non-existent or already returned book.", invalidReturnResult);
    }

    private ByteArrayOutputStream outputStreamCaptor;

    @Before
    public void setUp1() {
        library = new Library();
        availableBook = new Book("1234567890", "Title One", "Author One", 2024);
        library.addBook(availableBook);
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(System.out); 
    }

    @Test
    public void testViewAvailableBooks() {
        library.viewAvailableBooks(); 
        String output = outputStreamCaptor.toString().trim();
        assertTrue("Output should contain the book title", output.contains(availableBook.getTitle()));
    }
}