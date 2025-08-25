package tech.ada.exception;

public class NoIdFound extends RuntimeException{
    public NoIdFound(String mensagem){
        super(mensagem);
    }
}
