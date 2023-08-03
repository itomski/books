package de.lubowiecki.books;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AuthorList {

    private final List<Author> authors;

    public AuthorList() {
        this.authors = new ArrayList<>();
        authors.add(new Author("Peter", "Parker"));
        authors.add(new Author("Carol", "Danvers"));
        authors.add(new Author("Tony", "Stark"));
        authors.add(new Author("Natasha", "Romanov"));
    }

    public List<Author> getAll() {
        return Collections.unmodifiableList(authors);
    }
}
