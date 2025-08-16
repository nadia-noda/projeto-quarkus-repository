package tech.ada.controller;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.ada.dto.BookMapper;
import tech.ada.model.Book;
import tech.ada.dto.BookDTO;
import tech.ada.repoitory.BookRepository;

import java.util.List;

@Path("/biblioteca")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LibraryController {

    @Inject
    BookRepository repository;

    @GET

    public Response getBook(){
        List<Book> books = repository.findAll().list();
        return  Response.status(Response.Status.OK).entity(books)
                .build();
    }

    @POST
    @Transactional
    public Response addBook(BookDTO bookDTO){
        repository.persist(BookMapper.toEntity(bookDTO));
        return Response.status(Response.Status.CREATED).entity(bookDTO).build();
    }



}

