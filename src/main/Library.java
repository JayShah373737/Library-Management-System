package com.example.library;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Library {

    private Map<String, Book> books;
    private Map<String, Boolean> borrowedStatus = new HashMap<>();

    public Library() {
        books = new HashMap<>();
    }

    private String generateKey(Book book) {
        return book.getTitle() + "-" + book.getAuthor(); 
    }

    public boolean addBook(Book book) {
        String key = generateKey(book);
        if (!books.containsKey(key)) {
            books.put(key, book);
            borrowedStatus.put(book.getId(), false);
            return true; 
        } else {
            System.out.println("Book already exists in the library.");
            return false; 
        }
    }

    public boolean borrowBook(String id) {
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                boolean isBorrowed = borrowedStatus.getOrDefault(id, false);
                if (!isBorrowed) {
                    borrowedStatus.put(id, true); 
                    return true; 
                } else {
                    System.out.println("Book is already borrowed.");
                    return false; 
                }
            }
        }
        System.out.println("Book not found in the library.");
        return false; 
    }

    public Book getBook(String id) {
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean returnBook(String id) {
        if (borrowedStatus.containsKey(id)) {
            if (borrowedStatus.get(id)) {
                borrowedStatus.put(id, false); 
                return true; 
            } else {
                System.out.println("Book was not borrowed.");
                return false; 
            }
        } else {
            System.out.println("Book not found.");
            return false; 
        }
    }

    public boolean isBorrowed(String id) {
        return borrowedStatus.getOrDefault(id, false);
    }

    
    public Collection<Book> getBooks() {
        return books.values();
    }

    
    public void viewAvailableBooks() {
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            String id = entry.getValue().getId();
            boolean isBorrowed = borrowedStatus.getOrDefault(id, false);
            if (!isBorrowed) {
                System.out.println("Available Book: " + entry.getValue().getTitle()
                        + " by " + entry.getValue().getAuthor());
            }
        }
    }
}