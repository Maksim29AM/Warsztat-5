package pl.coderslab.servise;

import org.springframework.stereotype.Service;
import pl.coderslab.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockBookService implements BookService {
    private List<Book> list;
    private static Long nextId = 4L;


    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
        list.add(new Book(2L, "Rusz glowa Java.", "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion", "programming"));

    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        list.add(book);
    }

    @Override
    public Optional<Book> get(Long id) {
        return list.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Long id) {
        if (get(id).isPresent()) {
            list.remove(this.get(id).get());
        }
    }

    @Override
    public void update(Book book) {
        if (this.get(book.getId()).isPresent()) {
            int indexOf = list.indexOf(this.get(book.getId()).get());
            list.set(indexOf, book);
        }
    }
}
