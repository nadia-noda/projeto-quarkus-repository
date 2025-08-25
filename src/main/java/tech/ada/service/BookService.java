package tech.ada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import tech.ada.dto.BookDTO;
import tech.ada.dto.BookMapper;
import tech.ada.exception.NoIdFound;
import tech.ada.exception.TitleAlreadyExistsException;
import tech.ada.exception.TitleNotExistsException;
import tech.ada.model.Book;
import tech.ada.repoitory.BookRepository;
import java.util.Optional;
import java.util.List;
import java.util.Set;

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
        BookMapper.updateBook(bookDTO, book);
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

}
