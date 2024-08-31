package com.example.library;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void testBookCreation() {
        // Create a Book instance with test data
        
        Book b1 = new Book("Java", "Joshua Bloch", 2008);

        // Verify the properties of the book
        assertEquals("Java", b1.getTitle());
        assertEquals("Joshua Bloch", b1.getAuthor());
        assertEquals(2008, b1.getPublicationYear());
        assertTrue(b1.isAvailable());
    }

    @Test
    public void testUniqueIdGeneration() {
        // Create two different Book instances
        Book book1 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book2 = new Book("C++", "Robert C. Martin", 2008);

        // Verify that each book has a unique ID
        assertNotEquals(book1.getId(), book2.getId());
    }
}