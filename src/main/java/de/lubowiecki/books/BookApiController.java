package de.lubowiecki.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/genre/{genre}")
    public List<Book> allByGenre(@PathVariable Genre genre) {
        return repository.findAllByGenre(genre);
    }

    @GetMapping("/title/{str}")
    public List<Book> allByTitle(@PathVariable String str) {
        return repository.findAllByTitleLikeIgnoreCase("%" + str + "%");
    }

    @GetMapping("/q") // /api/v1/books/q?isbn=12345678
    public Book allByIsbn(@RequestParam(required = true) String isbn) {
        return repository.findByIsbn(isbn).orElse(new Book());
    }

    @GetMapping("/q2") // /api/v1/books/q2?genre=DRAMA&page=5
    public String allByQuery(@RequestParam(required = true) Genre genre, @RequestParam(required = true) int page) {
        return new StringBuilder()
                .append("genre=")
                .append(genre)
                .append(",page=")
                .append(page)
                .toString();
    }

    @GetMapping("/q3") // /api/v1/books/q3?val=A&val=B&val=C
    public String allByQuery(@RequestParam List<String> val) {
        return val.toString();
    }

    @GetMapping("/q4") // /api/v1/books/q4?x=100&y=200&z=25
    public String allByQuery(@RequestParam Map<String, Integer> map) {
        return map.toString();
    }
}
