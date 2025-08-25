package tech.ada.controller;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestPath;
import tech.ada.dto.BookMapper;
import tech.ada.model.Book;
import tech.ada.dto.BookDTO;
import tech.ada.repoitory.BookRepository;
import tech.ada.service.BookService;

import java.util.List;

@Path("/biblioteca")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LibraryController {

  //  @Inject
  //  BookRepository repository;

    private final BookService bookService;

    public LibraryController(BookService bookService){
        this.bookService = bookService;
    }

    @GET
    public Response getBooks(){
        List<BookDTO> books = bookService.getAll();
        return  Response
                .status(Response.Status.OK)
                .entity(books)
                .build();
    }

    @POST
    @Transactional
    public Response addBook(@Valid BookDTO bookDTO){
        Book book = bookService.create(bookDTO);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @Path("/{id}")
    @GET
    public  Response getById(@RestPath Long id){
        return Response
                .status(Response.Status.OK)
                .entity(bookService.getById(id))
                .build();
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response updateBook(@RestPath Long id, BookDTO bookDTO) {
        bookService.update(id, bookDTO);
        return Response.status(Response.Status.OK).build();
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deleteBook(@RestPath Long id) {
        bookService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

//teste Helbert
}

