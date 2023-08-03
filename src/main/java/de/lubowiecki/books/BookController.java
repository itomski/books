package de.lubowiecki.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BookController {

    // GET: Abfrage von Ressourcen
    // POST: Erzeugung einer neuen Ressource (nicht nur)
    // PUT: Ändern oder Erzeugen einer Ressource
    // DELETE: Löschen einer Ressorce

    // CRUD - Create Read Update Delete

    @Autowired
    private BookRepository repository;

    //@Autowired
    //private AuthorList authorList;

    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "READ");
        model.addAttribute("content", "Ressource lesen");
        //model.addAttribute("content", authorList.getAll().toString());

        return "standard";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("title", "Neues Buch");
        model.addAttribute("book", new Book());
        return "form";
    }

    @PostMapping("")
    public String create(Book book, Model model) {
        Author author = new Author("Peter", "Parker");
        // book.setAuthor(author);
        // repository.save(book);
        author.addBook(book);
        authorRepository.save(author);
        return "redirect:/all"; // Umleitung auf /all Mapping
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("books", repository.findAll());
        return "list";
    }

    @PutMapping("")
    public String update(Model model) {
        model.addAttribute("title", "UPDATE");
        model.addAttribute("content", "Ändern der Ressource");
        return "standard";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        repository.deleteById(id);
        return "redirect:/all";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Book> opt = repository.findById(id);
        if(opt.isPresent()) {
            model.addAttribute("book", opt.get());
        }
        return "form";
    }
}
