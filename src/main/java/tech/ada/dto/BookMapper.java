package tech.ada.dto;

import tech.ada.model.Book;

public class BookMapper {

    public static Book toEntity(BookDTO bookDTO){
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setAuthor(bookDTO.getAuthor());
        book.setGenre(bookDTO.getGenre());
        book.setPublisher(bookDTO.getPublisher());
        book.setSynopsis(bookDTO.getSynopsis());
        book.setTitle(bookDTO.getTitle());
        book.setPosterPath(bookDTO.getPosterPath());
        book.setPublicationDate(bookDTO.getPublicationDate());
        return book;
    }

}
