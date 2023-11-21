package Exceptions;

public class EmptyInputException extends RuntimeException{

	public EmptyInputException() {
		super("Error:La Entrada esta vacia");
	}
}
