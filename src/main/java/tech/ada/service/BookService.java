package tech.ada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import tech.ada.dto.BookDTO;
import tech.ada.dto.BookMapper;
import tech.ada.dto.BookUpdateDTO;
import tech.ada.exception.IsbnAlreadyExistsException;
import tech.ada.exception.TitleAlreadyExistsException;
import tech.ada.exception.TitleNotExistsException;
import tech.ada.model.Book;
import tech.ada.repository.BookRepository;
import java.util.Optional;
import java.util.List;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.findById;

@ApplicationScoped
public class BookService {

    private final BookRepository repository;
    public BookService(BookRepository repository){
        this.repository = repository;
}

    public Book create(BookDTO bookDTO){
        Optional<Book> optionalBook =
            repository.findByTitle(bookDTO.getTitle());
        if (optionalBook.isPresent()){
            throw new TitleAlreadyExistsException("This title already exists");
        }
        Optional<Book> bookWithSameIsbn = repository.findByIsbn(bookDTO.isbn);
        if (bookWithSameIsbn.isPresent()) {
            throw new IsbnAlreadyExistsException("ISBN already registered");
        }

        Book book = BookMapper.toEntity(bookDTO);
        repository.persist(book);
        return book;
    }


    public BookDTO getById(Long id){
        return BookMapper.toDTO(findById(id));
    }


    public List<BookDTO> getAll(){
        return repository.findAll()
                .list().stream().map(BookMapper::toDTO).toList();
    }

    public void update(Long id, BookDTO bookDTO) {
        Book book = findById(id);

        Optional<Book> bookWithSameTitle = repository.findByTitle(bookDTO.title);
        if (bookWithSameTitle.isPresent() && !bookWithSameTitle.get().getId().equals(id)) {
            throw new TitleAlreadyExistsException("This title already exists for another book");
        }

        Optional<Book> bookWithSameIsbn = repository.findByIsbn(bookDTO.isbn);
        if (bookWithSameIsbn.isPresent() && !bookWithSameIsbn.get().getId().equals(id)) {
            throw new IsbnAlreadyExistsException("This ISBN is already registered to another book");
        }

        BookMapper.updateBook(bookDTO, book);
    }

    public void updateParcial(Long id, BookUpdateDTO bookUpdateDTO) {
        Book book = findById(id);
        BookMapper.updateParcialBook(bookUpdateDTO, book);
    }

    public void delete(Long id) {
        Book book = findById(id);
        repository.delete(book);

    }

    private Book findById(Long id) {
        Book book = repository.findById(id);
        if (book == null){
            throw new TitleNotExistsException("Title wiht id" + id + "not found");
        }
        return book;
    }


    public Book getByIsbn(String isbn) {
        Optional<Book> optionalBook = repository.findByIsbn(isbn);
        if (optionalBook.isEmpty()) {
            throw new TitleNotExistsException("Title wiht isbn" + isbn + "not found");
        }
        return optionalBook.get();
    }

    public void updateParcialIsbn(String isbn, BookUpdateDTO bookUpdateDTO) {
        Optional<Book> optionalBook = repository.findByIsbn(isbn);
        if (optionalBook.isEmpty()) {
            throw new TitleNotExistsException("Title wiht isbn" + isbn + "not found");
        }
        BookMapper.updateParcialBook(bookUpdateDTO,optionalBook.get());
    }
    
    public void deleteByIsbn(String isbn) {
        Optional<Book> optionalBook = repository.findByIsbn(isbn);
        if (optionalBook.isEmpty()){
            throw new TitleNotExistsException("Title wiht isbn" + isbn + "not found");
        }
        repository.delete(optionalBook.get());

    }


    public void updateParcial(String isbn, @Valid BookUpdateDTO bookUpdateDTO) {
    }
}




