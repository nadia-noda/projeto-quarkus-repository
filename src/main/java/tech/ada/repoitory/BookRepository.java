package tech.ada.repoitory;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import tech.ada.model.Book;

import java.util.Optional;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public Optional<Book> findByTitle (String title){
        return find("title", title).firstResultOptional();
    }

    public Book findById(Long id){
        return find("id",id).firstResult();
    }

}
