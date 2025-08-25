package tech.ada.exception;

public class TitleAlreadyExistsException extends RuntimeException{
    public TitleAlreadyExistsException(String mensagem){
        super(mensagem);
    }
}
