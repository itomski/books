package de.lubowiecki.books;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Dies und das");
        model.addAttribute("content", "Bla bla bla");
        return "standard";
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("title", "Bücher");
        model.addAttribute("content", "Bücherliste...");

        Book book = new Book();
        book.setTitle("Kochen ohne Fett");
        book.setIsbn("123-4567-8910");
        book.setPrice(19.99);
        book.setGenre(Genre.FANTASY);

        model.addAttribute("book", book);

        return "standard";
    }
}
