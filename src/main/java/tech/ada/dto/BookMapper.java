package tech.ada.dto;

import tech.ada.model.Book;

public class BookMapper {

    //puxa os dados recebidos de bookDTO para Book
    public static Book toEntity(BookDTO bookDTO){
        if (bookDTO == null){
            return null;
        }

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

    public static Book updateBook(BookDTO bookDTO, Book book) {
        if (bookDTO == null) {
            return null;
        }
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

    //puxa os dados de Book para bookDTO, em caso de consulta
    public static BookDTO toDTO(Book book) {
        if (book == null){
            return null;
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setTittle(book.getTitle());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setPublicationDate(book.getPublicationDate());
        bookDTO.setSynopsis(book.getSynopsis());

        return bookDTO;
    }

}
