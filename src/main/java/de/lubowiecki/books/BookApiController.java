package de.lubowiecki.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books")
public class BookApiController {

    final private BookRepository repository;

    // Alternative zu Autowired
    public BookApiController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Book one(@PathVariable int id) {
        // Wenn ein Objekt gefunden wurde, wird dieses zurück gegeben
        // ansonsten ein leeres neues Objekt
        return repository.findById(id).orElse(new Book());
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book) {
        Optional<Book> opt = repository.findById(id);
        if(opt.isPresent()) {
            book.setId(id);
            return repository.save(book);
        }
        return new Book();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        repository.deleteById(id);
        return "{\"deleted\": true}";
    }
}
