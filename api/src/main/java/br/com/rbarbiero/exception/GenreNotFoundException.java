package br.com.rbarbiero.exception;

public class GenreNotFoundException extends IllegalArgumentException {
    public GenreNotFoundException(final String message){
        super(message);
    }
}
