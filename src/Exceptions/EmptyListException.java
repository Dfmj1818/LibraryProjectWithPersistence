package Exceptions;

public class EmptyListException extends RuntimeException{

	public EmptyListException() {
		super("No tienes Prestamos Hasta el Momento");
	}
}
