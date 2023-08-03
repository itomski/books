package de.lubowiecki.books;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> findAllByGenre(Genre genre);

    public Optional<Book> findByIsbn(String isbn);

    public List<Book> findAllByTitleLikeIgnoreCase(String str);

}
