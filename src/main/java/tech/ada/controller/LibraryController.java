package tech.ada.controller;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import tech.ada.dto.BookMapper;
import tech.ada.model.Book;
import tech.ada.dto.BookDTO;
import tech.ada.repoitory.BookRepository;
import tech.ada.service.BookService;

import java.util.List;

@Tag(name = "Biblioteca", description = "Gerenciamento de livros")
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

    @Operation(summary = "Lista todos os livros", description = "Retorna uma lista com todos os livros cadastrados")
    @GET
    public Response getBooks(){
        List<BookDTO> books = bookService.getAll();
        return  Response
                .status(Response.Status.OK)
                .entity(books)
                .build();
    }

    @Operation(summary = "Adiciona livro", description = "Adiciona livro a biblioteca")
    @POST
    @Transactional
    public Response addBook(@Valid BookDTO bookDTO){
        Book book = bookService.create(bookDTO);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @Operation(summary = "Busca por id", description = "Busca livro por id")
    @Path("/{id}")
    @GET
    public  Response getById(@RestPath Long id){
        return Response
                .status(Response.Status.OK)
                .entity(bookService.getById(id))
                .build();
    }

    @Operation(summary = "Altera livro", description = "Altera livro da biblioteca")
    @Path("/{id}")
    @PUT
    @Transactional
    public Response updateBook(@RestPath Long id, @Valid BookDTO bookDTO) {
        bookService.update(id, bookDTO);
        return Response.status(Response.Status.OK).build();
    }

    @Operation(summary = "Exclui livro", description = "Exclui livro da biblioteca")
    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deleteBook(@RestPath Long id) {
        bookService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}

