package models;

import java.util.ArrayList;

public record Person(String name, ArrayList<Book> books) {

    public Person(String name) {
        this(name, new ArrayList<>());
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
