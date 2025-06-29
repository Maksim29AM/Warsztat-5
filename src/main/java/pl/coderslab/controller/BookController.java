package pl.coderslab.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.domain.Book;
import pl.coderslab.servise.MockBookService;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final MockBookService mockBookService;

    public BookController(MockBookService mockBookService) {
        this.mockBookService = mockBookService;
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    @ResponseBody
    public List<Book> getList() {
        return mockBookService.getBooks();
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        mockBookService.add(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.mockBookService.get(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Book not found"
        ));
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        mockBookService.delete(id);
    }

    @PutMapping("")
    @ResponseBody
    public void updateBook(@RequestBody Book book) {
        mockBookService.update(book);
    }
}
