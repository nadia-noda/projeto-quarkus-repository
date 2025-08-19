package tech.ada.repoitory;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import tech.ada.model.Book;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public Book findByTitle (String title){
        return find("title", title).firstResult();
    }

}
