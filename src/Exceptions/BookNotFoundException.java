package Exceptions;

public class BookNotFoundException extends RuntimeException{

	public BookNotFoundException() {
		super("Libro No Encontrado,Escoge Otro ID");
	}
}
